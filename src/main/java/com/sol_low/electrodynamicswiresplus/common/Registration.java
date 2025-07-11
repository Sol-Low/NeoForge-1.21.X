package com.sol_low.electrodynamicswiresplus.common;

import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import electrodynamics.common.block.connect.BlockWire;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.Map;

import static com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus.MODID;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister<Item>  ITEMS  =
            DeferredRegister.createItems(MODID);

    public static final Map<SubtypeWirePlus, DeferredHolder<Block, BlockWire>> WIRES =
            new EnumMap<>(SubtypeWirePlus.class);

    public static void init(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);

        for (SubtypeWirePlus subtype : SubtypeWirePlus.values()) {
            String name = subtype.tag();
            // 1) register the block itself
            DeferredHolder<Block, BlockWire> wireBlock =
                    BLOCKS.register(name, () -> new BlockWire(subtype));
            // 2) register the block‐item
            ITEMS.register(name, () -> new BlockItem(
                    wireBlock.get(),
                    new Item.Properties() //.Properties.tab(CreativeModeTabs.REDSTONE)
            ));
        }
    }
}
