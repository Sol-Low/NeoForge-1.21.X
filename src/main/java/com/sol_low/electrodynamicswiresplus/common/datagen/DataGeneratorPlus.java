package com.sol_low.electrodynamicswiresplus.common.datagen;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import com.sol_low.electrodynamicswiresplus.common.datagen.client.model.SlaveNodeModelBuilder;
import com.sol_low.electrodynamicswiresplus.common.datagen.client.model.WireModelBuilder;
import electrodynamics.Electrodynamics;
//import electrodynamics.datagen.client.*;
import com.sol_low.electrodynamicswiresplus.common.datagen.client.*;
import electrodynamics.datagen.server.recipe.ElectrodynamicsRecipeProvider;
import electrodynamics.datagen.server.tags.ElectrodynamicsTagsProvider;
import electrodynamics.registers.ElectrodynamicsDamageTypes;
import electrodynamics.registers.ElectrodynamicsFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.Nullable;
import voltaic.api.network.cable.type.IWire;
import voltaic.datagen.utils.client.BaseLangKeyProvider;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(
        modid = ElectrodynamicsWiresPlus.MODID,
        bus   = EventBusSubscriber.Bus.MOD
)
public class DataGeneratorPlus {

    public static final HashMap<IWire.IWireClass, HashSet<SubtypeWirePlus>> WIRES = new HashMap<>();

    static {
        for (SubtypeWirePlus wire : SubtypeWirePlus.values()) {
            HashSet<SubtypeWirePlus> wireSet = WIRES.getOrDefault(wire.getWireClass(), new HashSet<>());
            wireSet.add(wire);
            WIRES.put(wire.getWireClass(), wireSet);
        }
    }

    @Nullable
    public static SubtypeWirePlus getWire(
            SubtypeWirePlus.IWireMaterial conductor,
            SubtypeWirePlus.IInsulationMaterial insulation,
            SubtypeWirePlus.IWireClass wireClass,
            SubtypeWirePlus.IWireColor color)
    {

        for (SubtypeWirePlus wire : WIRES.getOrDefault(wireClass, new HashSet<>())) {
            if (wire.getWireMaterial() == conductor && wire.getInsulation() == insulation && wire.getWireClass() == wireClass && wire.getWireColor() == color) {
                return wire;
            }
        }
        return null;
    }

    public static SubtypeWirePlus[] getWires(IWire.IWireMaterial[] conductors, SubtypeWirePlus.IInsulationMaterial insulation, SubtypeWirePlus.IWireClass wireClass, SubtypeWirePlus.IWireColor... colors) {

        List<SubtypeWirePlus> list = new ArrayList<>();

        SubtypeWirePlus wire;
        for (IWire.IWireMaterial conductor : conductors) {
            for (SubtypeWirePlus.IWireColor color : colors) {
                wire = getWire(conductor, insulation, wireClass, color);
                if (wire != null) {
                    list.add(wire);
                }
            }
        }

        return list.toArray(new SubtypeWirePlus[0]);
    }
    
    
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        System.out.println("⭑⭑⭑ DataGeneratorPlus fired! ⭑⭑⭑");
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        // server‐side (blockstates / loot / recipes / tags)…
        if (event.includeServer()) {

            DatapackBuiltinEntriesProvider datapacks = new DatapackBuiltinEntriesProvider(output, lookupProvider, new RegistrySetBuilder()
                    //
                    .add(Registries.DAMAGE_TYPE, ElectrodynamicsDamageTypes::registerTypes)
                    //
                    .add(Registries.CONFIGURED_FEATURE, context -> ElectrodynamicsFeatures.registerConfigured(context))
                    //
                    .add(Registries.PLACED_FEATURE, ElectrodynamicsFeatures::registerPlaced)
                    //
                    .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ElectrodynamicsFeatures::registerModifiers)
                    //
                    , Set.of(Electrodynamics.ID));

            generator.addProvider(true, datapacks);
            generator.addProvider(true, new ElectrodynamicsRecipeProvider(output, lookupProvider));
            ElectrodynamicsTagsProvider.addTagProviders(generator, output, datapacks.getRegistryProvider(), helper);
        }

        // client‐only (blockstate JSONs, lang files, etc.)…
        if (event.includeClient()) {
            generator.addProvider(true, new ElectrodynamicsWPBlockStateProvider(output, helper));
            generator.addProvider(true, new ElectrodynamicsWPBlockModelsProviderPlus(output, helper));
            generator.addProvider(true, new ElectrodynamicsWPItemModelsProvider(output, helper));
            generator.addProvider(true, new ElectrodynamicsWPLangKeyProvider(output, BaseLangKeyProvider.Locale.EN_US));
        }
    }
}

