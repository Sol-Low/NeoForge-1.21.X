package com.sol_low.electrodynamicswiresplus;

import com.mojang.logging.LogUtils;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWireAddon;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import electrodynamics.common.eventbus.RegisterWiresEvent;
import electrodynamics.common.block.connect.BlockWire;
import org.slf4j.Logger;

@Mod(ElectrodynamicsWiresPlus.MODID)
public class ElectrodynamicsWiresPlus {

    public static final String MODID = "electrodynamicswiresplus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ElectrodynamicsWiresPlus(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    @EventBusSubscriber(modid = MODID, bus = Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void onRegisterWire(RegisterWiresEvent event) {
            for (SubtypeWireAddon wire : SubtypeWireAddon.values()) {
                event.registerWire(new BlockWire(wire));
            }
        }
    }
}
