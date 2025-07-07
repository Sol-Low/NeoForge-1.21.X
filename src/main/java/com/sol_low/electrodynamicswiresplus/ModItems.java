package com.sol_low.electrodynamicswiresplus;

import java.util.function.Supplier;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ElectrodynamicsWiresPlus.MODID);

    public static final Supplier<BlockItem> WIRE_COPPER_BARE_PURPLE_ITEM =
            ITEMS.registerSimpleBlockItem(
                    "wire_copper_bare_purple",
                    ModBlocks.WIRE_COPPER_BARE_PURPLE,
                    new BlockItem.Properties()
            );
}
