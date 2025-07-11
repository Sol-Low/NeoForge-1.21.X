package com.sol_low.electrodynamicswiresplus.common.block.subtype;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import voltaic.api.network.cable.type.IWire.IWireColor;
import voltaic.prefab.utilities.math.Color;

import java.util.Objects;

public enum ColorsPlus implements IWireColor {

    PURPLE("PURPLE",new Color(128, 0, 128, 255)),
    PINK("PINK", new Color(255,0,92,255));



    private final String name;
    private final Color color;
    ColorsPlus(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getSerializedName() {
        return name;
    }

    @Override
    @NotNull
    public Color getColor() {
        return color;
    }

    @Override
    @NotNull
    public TagKey<Item> getDyeTag() {
        return ItemTags.create(Objects.requireNonNull(ResourceLocation.tryParse("minecraft:dyes/" + name)));
    }
}

