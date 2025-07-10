package com.sol_low.electrodynamicswiresplus.common;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWireAddon;
import electrodynamics.common.block.connect.BlockWire;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, ElectrodynamicsWiresPlus.MODID);

    public static final Map<SubtypeWireAddon, DeferredHolder<Block, BlockWire>> WIRES = new HashMap<>();

    static {
        for (SubtypeWireAddon subtype : SubtypeWireAddon.values()) {
            DeferredHolder<Block, BlockWire> reg = BLOCKS.register(
                    subtype.name().toLowerCase(),
                    () -> new BlockWire(subtype)
            );
            WIRES.put(subtype, reg);
        }
    }
}
