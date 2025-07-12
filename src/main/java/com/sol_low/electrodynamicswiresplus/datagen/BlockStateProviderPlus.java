package com.sol_low.electrodynamicswiresplus.datagen;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import com.sol_low.electrodynamicswiresplus.common.Registration;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import electrodynamics.Electrodynamics;
import electrodynamics.common.block.connect.BlockWire;
import net.minecraft.ResourceLocationException;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelFile.ExistingModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import voltaic.Voltaic;
import voltaic.datagen.utils.client.BaseBlockstateProvider;

public class BlockStateProviderPlus extends BaseBlockstateProvider {
    private final ExistingFileHelper helper;
    public BlockStateProviderPlus(PackOutput output, ExistingFileHelper helper) {
        super(output, helper, ElectrodynamicsWiresPlus.MODID);

        this.helper = helper;
    }

    @Override
    protected void registerStatesAndModels() {
        // Loop over every wire you registered in Registration.WIRES:
        Registration.WIRES.forEach(( subtype,  holder) -> {
            BlockWire wireBlock = holder.get();
            String name        = subtype.tag(); // e.g. "insulatedcopperpurple"
            ResourceLocation blockNone = ResourceLocation.fromNamespaceAndPath(Electrodynamics.ID, "parent/wire_none");
            ResourceLocation blockSide = ResourceLocation.fromNamespaceAndPath(Electrodynamics.ID, "parent/wire_side");

            // 1) Build a ModelFile that uses Voltaic's cable_none as a parent,
            //    but swaps out its "none" texture for your mod's block/<name>_none.png
            ExistingModelFile wire_none = new ExistingModelFile(blockNone, helper);
            ExistingModelFile wire_side = new ExistingModelFile(blockSide, helper);

            // 3) Tell BaseBlockstateProvider to emit the blockstate (with cutout) and an item model
            wire(wireBlock, wire_none, wire_side, /* registerItem = */ true);
        });
    }
}
