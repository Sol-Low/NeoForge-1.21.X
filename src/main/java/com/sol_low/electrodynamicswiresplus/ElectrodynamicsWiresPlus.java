package com.sol_low.electrodynamicswiresplus;

import com.mojang.logging.LogUtils;
import com.sol_low.electrodynamicswiresplus.common.Registration;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import com.sol_low.electrodynamicswiresplus.common.item.PlusItems;
import electrodynamics.common.block.connect.BlockWire;
import electrodynamics.common.eventbus.RegisterWiresEvent;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(ElectrodynamicsWiresPlus.MODID)
public class ElectrodynamicsWiresPlus {

    public static final String MODID = "electrodynamicswiresplus";
    public static final Logger LOGGER = LogUtils.getLogger();

    //Constructor
    public ElectrodynamicsWiresPlus(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
        //NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        PlusItems.register(modEventBus);


    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(PlusItems.WIREINSULATECOPPERBURPLE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}








    @SubscribeEvent
    public void onRegisterWire(RegisterWiresEvent event) {
        for (SubtypeWirePlus subtype : SubtypeWirePlus.values()) {
            Block block = Registration.WIRES.get(subtype).get();
            if (block instanceof BlockWire wire) {
                event.registerWire(wire);
            } else {
                ElectrodynamicsWiresPlus.LOGGER.warn("Expected BlockWire but got {}", block.getClass().getName());
            }
        }
    }

}
