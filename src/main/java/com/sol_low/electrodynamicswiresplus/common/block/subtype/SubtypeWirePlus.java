package com.sol_low.electrodynamicswiresplus.common.block.subtype;

import electrodynamics.common.block.subtype.SubtypeWire.WireClass;
import electrodynamics.common.block.subtype.SubtypeWire.WireMaterial;
import electrodynamics.common.block.subtype.SubtypeWire.InsulationMaterial;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import voltaic.common.tags.VoltaicTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import voltaic.api.network.cable.type.IWire;
import voltaic.prefab.utilities.math.Color;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;


public enum SubtypeWirePlus implements IWire {

    insulatedcopperpurple(
            WireMaterial.COPPER,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES

    ),
    insulatedsuperconductivepurple(
            WireMaterial.SUPERCONDUCTIVE,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES
    ),
    insulatedsilverpurple(
            WireMaterial.SILVER,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_SILVER_WIRES
    ),
    insulatedtinpurple(
            WireMaterial.TIN,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_TIN_WIRES
    ),
    insulatedironpurple(
            WireMaterial.IRON,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_IRON_WIRES
    ),
    insulatedgoldpurple(
            WireMaterial.GOLD,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_GOLD_WIRES
    ),
    highlyinsulatedcopperpurple(
            WireMaterial.COPPER,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES

    ),
    highlyinsulatedsuperconductivepurple(
            WireMaterial.SUPERCONDUCTIVE,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_COPPER_WIRES
    ),
    highlyinsulatedsilverpurple(
            WireMaterial.SILVER,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_SILVER_WIRES
    ),
    highlyinsulatedtinpurple(
            WireMaterial.TIN,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_TIN_WIRES
    ),
    highlyinsulatedironpurple(
            WireMaterial.IRON,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_IRON_WIRES
    ),
    highlyinsulatedgoldpurple(
            WireMaterial.GOLD,
            InsulationMaterial.THICK_WOOL,
            WireClass.INSULATED,
            WireColorPlus.PURPLE,
            VoltaicTags.Items.INSULATED_GOLD_WIRES
    );

    private final WireMaterial material;
    private final InsulationMaterial insulation;
    private final WireClass wireClass;
    private final IWire.IWireColor color;
    @Nullable
    private final TagKey<Item> itemTag;

    SubtypeWirePlus(WireMaterial material, InsulationMaterial insulation, WireClass wireClass,
                    IWire.IWireColor color, TagKey<Item> itemTag) {
        this.material = material;
        this.insulation = insulation;
        this.wireClass = wireClass;
        this.color = color;
        this.itemTag = itemTag;
    }

    @Override public WireMaterial       getWireMaterial() { return material; }
    @Override public InsulationMaterial getInsulation() { return insulation; }
    @Override public WireClass          getWireClass() { return wireClass; }
    @Override public IWire.IWireColor   getWireColor() { return color; }
    @Override public IWire.IWireColor   getDefaultColor() { return color; }
    @Override public boolean            isDefaultColor() { return true; }
    @Override public TagKey<Item>       getItemTag() { return itemTag; }
    @Override public double             getResistance() { return material.resistance(); }
    @Override public long               getAmpacity() { return material.ampacity(); }
    public String                       tag() { return "wire" + name().toLowerCase(Locale.ROOT); }

    public enum WireColorPlus implements IWire.IWireColor {

        NONE(255, 255, 255, 255, null),
        PURPLE(174, 114, 241, 255, Tags.Items.DYES_PURPLE);

    public final Color color;
        @Nullable
        public final TagKey<Item> dyeTag;

        public static final HashSet<WireColorPlus> WIRE_COLORS = new HashSet<>();

        private WireColorPlus(int r, int g, int b, int a, TagKey<Item> dyeTag) {
            color = new Color(r, g, b, a);
            this.dyeTag = dyeTag;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase(Locale.ROOT);
        }

        @Nullable
        public static IWire.IWireColor getColorFromDye(ItemStack item) {
            for (WireColorPlus color : WIRE_COLORS) {
                if (color.getDyeTag() != null && item.is(color.getDyeTag())) {
                    return color;
                }
            }
            return null;
        }

        @NotNull
        @Override
        public Color getColor() {
            return color;
        }

        @NotNull
        @Override
        public TagKey<Item> getDyeTag() {
            return dyeTag;
        }
    }
}