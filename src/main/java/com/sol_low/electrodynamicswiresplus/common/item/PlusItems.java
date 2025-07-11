package com.sol_low.electrodynamicswiresplus.common.item;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PlusItems {
    // Register these items to my mod
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ElectrodynamicsWiresPlus.MODID);

    public static final DeferredItem<Item> WIREINSULATECOPPERBURPLE = ITEMS.register("wireinsulatedcopperburple",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
