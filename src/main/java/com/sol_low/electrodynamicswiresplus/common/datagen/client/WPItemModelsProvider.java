package com.sol_low.electrodynamicswiresplus.common.datagen.client;

import com.sol_low.electrodynamicswiresplus.ElectrodynamicsWiresPlus;
import com.sol_low.electrodynamicswiresplus.common.block.subtype.SubtypeWirePlus;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import voltaic.datagen.utils.client.BaseItemModelsProvider;

public class WPItemModelsProvider extends BaseItemModelsProvider {
    public WPItemModelsProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, helper, ElectrodynamicsWiresPlus.MODID);
    }

    @Override
    protected void registerModels() {

    }
}
