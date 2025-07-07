package com.sol_low.electrodynamicswiresplus;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import voltaic.api.network.cable.type.IWire;
import voltaic.prefab.utilities.math.Color;

/**
 * Defines custom wire colors for the ElectrodynamicsWiresPlus mod.
 */
public enum ModWireColors implements IWire.IWireColor {
    /**
     * A vivid purple color (RGB: 174,114,241).
     */
    PURPLE(
            new Color(174 / 255f, 114 / 255f, 241 / 255f, 1f),
            TagKey.create(
                    BuiltInRegistries.ITEM.key(),
                    ResourceLocation.fromNamespaceAndPath("forge", "dyes/purple")
            )
    );

    private final Color color;
    private final TagKey<Item> dyeTag;

    ModWireColors(Color color, TagKey<Item> dyeTag) {
        this.color = color;
        this.dyeTag = dyeTag;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public TagKey<Item> getDyeTag() {
        return dyeTag;
    }
}
