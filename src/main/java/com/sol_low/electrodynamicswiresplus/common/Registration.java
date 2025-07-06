package com.sol_low.electrodynamicswiresplus.common;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import electrodynamics.common.block.connect.BlockWire;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.BuiltInRegistries;

public class Registration {
    public static final DeferredRegister<BlockWire> BLOCKS = DeferredRegister.createBlocks(ElectrodynamicsWiresPlus.MODID);
    public static final DeferredRegister<Item>      ITEMS  = DeferredRegister.createItems(ElectrodynamicsWiresPlus.MODID);

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS .register(bus);

        for (SubtypeWirePlus sub : SubtypeWirePlus.values()) {
            String name = sub.tag(); // e.g. "wireinsulatedcopperpurple"
            // register the BlockWire
            DeferredHolder<BlockWire> wireBlock = BLOCKS.register(name, () -> new BlockWire(sub));
            // register its BlockItem so it appears in Creative
            ITEMS.register(name, () -> new BlockItem(
                    wireBlock.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)
            ));
        }
    }
}
