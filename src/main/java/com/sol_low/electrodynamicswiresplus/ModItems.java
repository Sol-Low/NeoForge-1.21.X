package com.sol_low.electrodynamicswiresplus;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ElectrodynamicsWiresPlus.MODID);

    public static final Supplier<BlockItem> WIRE_INSULATED_COPPER_PURPLE_ITEM =
            ITEMS.registerSimpleBlockItem(
                    "wireinsulatedcopperpurple",
                    ModBlocks.WIRE_INSULATED_COPPER_PURPLE,
                    new BlockItem.Properties()
            );
}
