package com.sol_low.electrodynamicswiresplus;

import com.mojang.logging.LogUtils;
import com.sol_low.electrodynamicswiresplus.common.Registration;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import com.sol_low.electrodynamicswiresplus.common.item.PlusItems;
import electrodynamics.Electrodynamics;
import electrodynamics.common.block.connect.BlockWire;
import electrodynamics.common.eventbus.RegisterWiresEvent;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.slf4j.Logger;

import java.util.Map;

@Mod(ElectrodynamicsWiresPlus.MODID)
public class ElectrodynamicsWiresPlus {

    public static final String MODID = "electrodynamicswiresplus";
    public static final Logger LOGGER = LogUtils.getLogger();

    //Constructor
    public ElectrodynamicsWiresPlus(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        //NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        PlusItems.register(modEventBus);
        Registration.init(modEventBus);


    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(PlusItems.WIREINSULATECOPPERBURPLE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}






    @EventBusSubscriber(modid = ElectrodynamicsWiresPlus.MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class  WireRegistrationSubscriber {

        @SubscribeEvent
        public static void onRegisterWire(RegisterWiresEvent event) {
            // for every one of your SubtypeWirePlus entries:
            for (Map.Entry<SubtypeWirePlus, DeferredHolder<Block, BlockWire>> entry :
                    Registration.WIRES.entrySet()) {
                BlockWire wire = entry.getValue().get();
                event.registerWire(wire);
            }
        }
    }
}
