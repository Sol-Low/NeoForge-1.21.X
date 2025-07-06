package com.sol_low.electrodynamicswiresplus.common.block.subtype;

import voltaic.api.network.cable.type.IWire.IWireColor;
import voltaic.prefab.utilities.math.Color;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;

public class WireColorPurple implements IWireColor {
    private static final Color PURPLE = new Color(128, 0, 128, 255);

    @Override public Color getColor()            { return PURPLE; }
    @Override public String toString()           { return "purple"; }
    @Nullable @Override public TagKey<Item> getDyeTag() { return null; }
}
