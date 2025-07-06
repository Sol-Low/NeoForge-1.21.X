package com.sol_low.electrodynamicswiresplus.common;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import electrodynamics.common.block.connect.BlockWire;
import electrodynamics.common.eventbus.RegisterWiresEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = ElectrodynamicsWiresPlus.MODID)
public class ElectrodynamicsWiresPlusEvents {

    @SubscribeEvent
    public static void onRegisterWires(RegisterWiresEvent ev) {
        for (SubtypeWirePlus sub : SubtypeWirePlus.values()) {
            BlockWire wire = SubtypeWirePlus.getWire(
                    sub.getWireMaterial(),
                    sub.getInsulation(),
                    sub.getWireClass(),
                    sub.getWireColor()
            );
            if (wire != null) {
                ev.registerWire(wire);
            }
        }
    }
}
