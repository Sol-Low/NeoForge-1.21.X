package com.sol_low.electrodynamicswiresplus.common.block.subtype;

import electrodynamics.common.block.subtype.SubtypeWire;                   // for its nested enums
import electrodynamics.common.block.subtype.SubtypeWire.WireMaterial;
import electrodynamics.common.block.subtype.SubtypeWire.InsulationMaterial;
import electrodynamics.common.block.subtype.SubtypeWire.WireClass;
import electrodynamics.common.block.connect.BlockWire;

import voltaic.api.ISubtype;
import voltaic.api.network.cable.type.IWire;
import voltaic.api.network.cable.type.IWire.IWireColor;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/**
 * Adds “purple‐insulated copper” alongside all the existing SubtypeWire entries.
 */
public enum SubtypeWirePlus implements ISubtype, IWire {

    // one example entry—add more if you like
    insulatedcopperpurple(
            WireMaterial.COPPER,
            InsulationMaterial.WOOL,
            WireClass.INSULATED,
            new WireColorPurple(),
            new WireColorPurple()
    );

    /**
     * Exactly the same nested‐map signature as upstream SubtypeWire:
     */
    public static final Map<
            IWire.IWireMaterial,
            Map<
                    IWire.IInsulationMaterial,
                    Map<
                            IWire.IWireClass,
                            Map<IWireColor, BlockWire>
                            >
                    >
            > WIRES = new HashMap<>();

    static {
        for (SubtypeWirePlus sub : values()) {
            BlockWire block = new BlockWire(sub);
            WIRES
                    .computeIfAbsent(sub.getWireMaterial(), m -> new HashMap<>())
                    .computeIfAbsent(sub.getInsulation(), i -> new HashMap<>())
                    .computeIfAbsent(sub.getWireClass(), c -> new HashMap<>())
                    .put(sub.getWireColor(), block);
        }
    }

    // instance fields
    private final IWireMaterial      material;
    private final IWire.IInsulationMaterial insulation;
    private final IWire.IWireClass   wireClass;
    private final IWireColor         color, defaultColor;

    SubtypeWirePlus(
            IWireMaterial material,
            IWire.IInsulationMaterial insulation,
            IWire.IWireClass wireClass,
            IWireColor color,
            IWireColor defaultColor
    ) {
        this.material     = material;
        this.insulation   = insulation;
        this.wireClass    = wireClass;
        this.color        = color;
        this.defaultColor = defaultColor;
    }

    public static BlockWire getWire(IWireMaterial wireMaterial, IInsulationMaterial insulation, IWireClass wireClass, IWireColor color) {
        return WIRES
                .getOrDefault(wireMaterial, Collections.emptyMap())
                .getOrDefault(insulation, Collections.emptyMap())
                .getOrDefault(wireClass, Collections.emptyMap())
                .getOrDefault(color, null);
    }


    // ── IWire methods ─────────────────────────────────────────────────────
    @Override public IWireMaterial      getWireMaterial() { return material; }
    @Override public IWire.IInsulationMaterial getInsulation()   { return insulation; }
    @Override public IWire.IWireClass   getWireClass()    { return wireClass; }
    @Override public IWireColor         getWireColor()    { return color; }
    @Override public IWireColor         getDefaultColor() { return defaultColor; }
    @Override public double getResistance() { return material.resistance(); }
    @Override public long   getAmpacity()  { return material.ampacity(); }
    // ── ISubtype methods ──────────────────────────────────────────────────
    @Override public boolean            isItem()          { return false; }
    @Override public String             tag()             { return "wire" + name(); }
    @Override public String             forgeTag()        { return tag(); }
    @Override public boolean            isDefaultColor()  { return color == defaultColor; }
    @Nullable @Override public TagKey<Item> getItemTag() { return null; }
}
