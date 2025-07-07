package com.sol_low.electrodynamicswiresplus;

import electrodynamics.common.eventbus.RegisterWiresEvent;
import electrodynamics.common.block.connect.BlockWire;

public class PurpleWireRegistrar {
    public static void onRegisterWires(RegisterWiresEvent event) {
        event.registerWire((BlockWire) ModBlocks.WIRE_COPPER_BARE_PURPLE.get());
    }
}
