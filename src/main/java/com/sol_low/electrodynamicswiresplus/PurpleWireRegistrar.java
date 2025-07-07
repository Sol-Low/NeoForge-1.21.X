package com.sol_low.electrodynamicswiresplus;

import electrodynamics.common.eventbus.RegisterWiresEvent;
import electrodynamics.common.block.connect.BlockWire;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PurpleWireRegistrar {
    private static final Logger LOGGER = LogManager.getLogger(ElectrodynamicsWiresPlus.MODID);
    public static void onRegisterWires(RegisterWiresEvent ev) {
        // this must be called *before* ED processes the event, so that
        // its dynamic blockstate/model & color–handler code picks yours up.
        LOGGER.info("⤷ [PurpleWireRegistrar] RegisterWiresEvent fired; injecting purple wire");
        ev.registerWire((BlockWire) ModBlocks.WIRE_INSULATED_COPPER_PURPLE.get());
    }
}

