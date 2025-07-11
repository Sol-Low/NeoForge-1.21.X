package com.sol_low.electrodynamicswiresplus.common.datagen.client;

import electrodynamics.common.block.connect.BlockWire;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import voltaic.datagen.client.VoltaicBlockStateProvider;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import com.sol_low.electrodynamicswiresplus.common.Registration;
import com.sol_low.electrodynamicswiresplus.common.datagen.client.model.WireModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;

import net.neoforged.neoforge.client.model.generators.ModelFile.ExistingModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.ObjModelBuilder;

public class WPBlockStateProvider extends VoltaicBlockStateProvider {

    public WPBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        // 1) first let vanilla/voltaic do their thing:
        super.registerStatesAndModels();

        // 2) now register each of your SubtypeWirePlus blocks:
        for (SubtypeWirePlus subtype : SubtypeWirePlus.values()) {
            var holder = Registration.WIRES.get(subtype);
            BlockWire wireBlock = holder.get();

            String id = subtype.getSerializedName();
            ResourceLocation parentLoc = modLoc("block/wire/" + id);
            ModelFile wireModel = models()
                    .withExistingParent("electrodynamicswiresplus:wire/" + id, parentLoc)
                    .customLoader(WireModelBuilder::begin)
                    .models(
                            parentLoc+"_none",     // none texture
                            parentLoc+"_side",     // side  texture
                            parentLoc+"_side"      // side  texture (again)
                    )
                    .end()
                    .renderType("cutout");
        }
    }

    /**
     * exactly your helper from voltaic with the WireModelBuilder loader:
     */
    public void wire(Block block, ModelFile none, ModelFile side, boolean registerItem) {
        ModelFile model = models()
                .withExistingParent(name(block), "minecraft:block/cube")
                .customLoader(WireModelBuilder::begin)
                .models(none, side, side)
                .end()
                .renderType("cutout");

        getVariantBuilder(block)
                .partialState()
                .addModels(new ConfiguredModel(model));

        if (registerItem) {
            simpleBlockItem(block, none);
        }
    }
}
