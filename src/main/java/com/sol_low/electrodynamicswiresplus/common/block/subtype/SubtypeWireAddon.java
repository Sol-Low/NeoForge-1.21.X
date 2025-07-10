package com.sol_low.electrodynamicswiresplus.common.block.subtype;

import electrodynamics.common.block.subtype.SubtypeWire.WireClass;
import electrodynamics.common.block.subtype.SubtypeWire.WireMaterial;
import electrodynamics.common.block.subtype.SubtypeWire.InsulationMaterial;
import voltaic.common.tags.VoltaicTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import voltaic.api.network.cable.type.IWire;
import voltaic.prefab.utilities.math.Color;

public enum SubtypeWireAddon implements IWire {

    INSULATED_COPPER_PURPLE(
            WireMaterial.COPPER,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            new SimpleWireColor("purple", new Color(128, 0, 128, 255)),
            VoltaicTags.Items.INSULATED_COPPER_WIRES

    );

    private final WireMaterial material;
    private final InsulationMaterial insulation;
    private final WireClass wireClass;
    private final IWire.IWireColor color;
    private final TagKey<Item> itemTag;

    SubtypeWireAddon(WireMaterial material, InsulationMaterial insulation, WireClass wireClass,
                     IWire.IWireColor color, TagKey<Item> itemTag) {
        this.material = material;
        this.insulation = insulation;
        this.wireClass = wireClass;
        this.color = color;
        this.itemTag = itemTag;
    }

    @Override public WireMaterial getWireMaterial() { return material; }
    @Override public InsulationMaterial getInsulation() { return insulation; }
    @Override public WireClass getWireClass() { return wireClass; }
    @Override public IWire.IWireColor getWireColor() { return color; }
    @Override public IWire.IWireColor getDefaultColor() { return color; }
    @Override public boolean isDefaultColor() { return true; }
    @Override public TagKey<Item> getItemTag() { return itemTag; }
    @Override public double getResistance() { return material.resistance(); }
    @Override public long getAmpacity() { return material.ampacity(); }

    private static class SimpleWireColor implements IWire.IWireColor {
        private final String name;
        private final Color color;

        public SimpleWireColor(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        public String getSerializedName() {
            return name;
        }

        @Override
        public Color getColor() {
            return color;
        }

        @Override
        public TagKey<Item> getDyeTag() {
            return ItemTags.create(ResourceLocation.tryParse("minecraft:dyes/" + name));


        }
    }
}
