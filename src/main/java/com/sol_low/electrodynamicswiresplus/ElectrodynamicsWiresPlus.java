package com.sol_low.electrodynamicswiresplus;

import com.mojang.logging.LogUtils;
import com.sol_low.electrodynamicswiresplus.common.Registration;
import com.sol_low.electrodynamicswiresplus.common.item.PlusItems;
import com.sol_low.electrodynamicswiresplus.datagen.BlockStateProviderPlus;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
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
        modEventBus.addListener(this::addCreative);
        PlusItems.register(modEventBus);
        Registration.init(modEventBus);
        // modEventBus.addListener(this::gatherData); // Not needed if we make DateGenRegistrat and gatherData static


    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(PlusItems.WIREINSULATECOPPERBURPLE);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}



    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = ElectrodynamicsWiresPlus.MODID)
    public static class DataGenRegistrar {
        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            if (event.includeClient()) {
                if (!event.includeClient()) return;
                DataGenerator gen = event.getGenerator();
                ExistingFileHelper helper = event.getExistingFileHelper();

                gen.addProvider(
                        event.includeClient(),
                        new BlockStateProviderPlus(gen.getPackOutput(), helper)
                );
            }
        }
    }
}