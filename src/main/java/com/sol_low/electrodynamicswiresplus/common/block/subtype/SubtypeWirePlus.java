package com.sol_low.electrodynamicswiresplus.common.block.subtype;

import electrodynamics.common.block.subtype.SubtypeWire.WireClass;
import electrodynamics.common.block.subtype.SubtypeWire.WireMaterial;
import electrodynamics.common.block.subtype.SubtypeWire.InsulationMaterial;
import voltaic.common.tags.VoltaicTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import voltaic.api.network.cable.type.IWire;
import java.util.Locale;


public enum SubtypeWirePlus implements IWire {

    insulatedcopperpurple(
            WireMaterial.COPPER,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES

    ),
    insulatedsuperconductivepurple(
            WireMaterial.SUPERCONDUCTIVE,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES
    ),
    insulatedsilverpurple(
            WireMaterial.SILVER,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_SILVER_WIRES
    ),
    insulatedtinpurple(
            WireMaterial.TIN,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_TIN_WIRES
    ),
    insulatedironpurple(
            WireMaterial.IRON,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_IRON_WIRES
    ),
    insulatedgoldpurple(
            WireMaterial.GOLD,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_GOLD_WIRES
    ),
    highlyinsulatedcopperpurple(
            WireMaterial.COPPER,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES

    ),
    highlyinsulatedsuperconductivepurple(
            WireMaterial.SUPERCONDUCTIVE,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES
    ),
    highlyinsulatedsilverpurple(
            WireMaterial.SILVER,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_SILVER_WIRES
    ),
    highlyinsulatedtinpurple(
            WireMaterial.TIN,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_TIN_WIRES
    ),
    highlyinsulatedironpurple(
            WireMaterial.IRON,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_IRON_WIRES
    ),
    highlyinsulatedgoldpurple(
            WireMaterial.GOLD,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            ColorsPlus.PURPLE,
            VoltaicTags.Items.INSULATED_GOLD_WIRES
    ),
    zplaceholder(
            WireMaterial.SUPERCONDUCTIVE,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            ColorsPlus.PINK,
            VoltaicTags.Items.INSULATED_COPPER_WIRES
    );

    private final WireMaterial material;
    private final InsulationMaterial insulation;
    private final WireClass wireClass;
    private final IWire.IWireColor color;
    private final TagKey<Item> itemTag;

    SubtypeWirePlus(WireMaterial material, InsulationMaterial insulation, WireClass wireClass,
                    IWire.IWireColor color, TagKey<Item> itemTag) {
        this.material = material;
        this.insulation = insulation;
        this.wireClass = wireClass;
        this.color = color;
        this.itemTag = itemTag;
    }
    public String getSerializedName() {
        // name() is the enum constant name, e.g. INSULATEDPURPLE
        return "wire" + name().toLowerCase(Locale.ROOT);
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
}
