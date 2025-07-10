package com.sol_low.electrodynamicswiresplus;

import com.mojang.logging.LogUtils;
import com.sol_low.electrodynamicswiresplus.common.Registration;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWireAddon;
import electrodynamics.common.block.connect.BlockWire;
import electrodynamics.common.eventbus.RegisterWiresEvent;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.fml.event.IModBusEvent;
import org.slf4j.Logger;

@Mod(ElectrodynamicsWiresPlus.MODID)
public class ElectrodynamicsWiresPlus {

    public static final String MODID = "electrodynamicswiresplus";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ElectrodynamicsWiresPlus(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onRegisterWire);
        Registration.BLOCKS.register(modEventBus);
        modEventBus.register(this); // for mod lifecycle events like RegisterWiresEvent
       // NeoForge.EVENT_BUS.register(this); // for common events like ServerStartingEvent
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

   // @SubscribeEvent
   // public void onServerStarting(ServerStartingEvent event) {}

    @SubscribeEvent
    public void onRegisterWire(RegisterWiresEvent event) {
        for (SubtypeWireAddon subtype : SubtypeWireAddon.values()) {
            Block block = Registration.WIRES.get(subtype).get();
            if (block instanceof BlockWire wire) {
                event.registerWire(wire);
            } else {
                ElectrodynamicsWiresPlus.LOGGER.warn("Expected BlockWire but got {}", block.getClass().getName());
            }
        }
    }

}
