package com.sol_low.electrodynamicswiresplus.common.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;

import com.sol_low.electrodynamicswiresplus.common.datagen.client.WPBlockStateProvider;
import com.sol_low.electrodynamicswiresplus.common.datagen.client.WPItemModelsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import voltaic.datagen.client.VoltaicLangKeyProvider;
import voltaic.datagen.client.VoltaicSoundProvider;
import voltaic.datagen.server.VoltaicRadiationShieldingProvider;
import voltaic.datagen.server.tags.VoltaicTagsProvider;
import voltaic.datagen.server.recipe.VoltaicRecipeProvider;
import voltaic.datagen.server.VoltaicAdvancementProvider;
import voltaic.datagen.utils.client.BaseLangKeyProvider.Locale;
import voltaic.registers.VoltaicDamageTypes;
import voltaic.Voltaic;

@EventBusSubscriber(modid = ElectrodynamicsWiresPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneratorPlus {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator     = event.getGenerator();
        PackOutput     output        = generator.getPackOutput();
        ExistingFileHelper helper    = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // ─── server side ────────────────────────────────────────────────────────
        if (event.includeServer()) {
            var datapacks = new DatapackBuiltinEntriesProvider(
                    output,
                    lookupProvider,
                    new RegistrySetBuilder()
                            .add(Registries.DAMAGE_TYPE, VoltaicDamageTypes::registerTypes),
                    Set.of(Voltaic.ID)
            );

            generator.addProvider(true, datapacks);
            VoltaicTagsProvider.addTagProviders(generator, output, datapacks.getRegistryProvider(), helper);
            generator.addProvider(true, new VoltaicRecipeProvider(output, lookupProvider));
            generator.addProvider(true, new VoltaicAdvancementProvider(output, datapacks.getRegistryProvider()));
            generator.addProvider(true, new VoltaicRadiationShieldingProvider(output));
        }

        // ─── client side ────────────────────────────────────────────────────────
        if (event.includeClient()) {
            // this will emit blockstates + block models for every SubtypeWirePlus
            generator.addProvider(true, new WPBlockStateProvider(output, helper));
            // this will emit item models (including your BlockItem wires)
            generator.addProvider(true, new WPItemModelsProvider(output, helper));
            // and the usual lang & sound
            generator.addProvider(true, new VoltaicLangKeyProvider(output, Locale.EN_US));
            generator.addProvider(true, new VoltaicSoundProvider(output, helper));
        }
    }
}
