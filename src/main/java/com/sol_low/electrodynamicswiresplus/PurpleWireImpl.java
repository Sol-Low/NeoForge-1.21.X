package com.sol_low.electrodynamicswiresplus;

import electrodynamics.common.block.subtype.SubtypeWire;
import voltaic.api.network.cable.type.*;
import voltaic.prefab.utilities.math.Color;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PurpleWireImpl implements IWire {
    private final SubtypeWire.WireMaterial material;
    private final SubtypeWire.InsulationMaterial insulation;
    private final SubtypeWire.WireClass wireClass;
    private final ModWireColors color;

    public PurpleWireImpl(
            SubtypeWire.WireMaterial material,
            SubtypeWire.InsulationMaterial insulation,
            SubtypeWire.WireClass wireClass,
            ModWireColors color
    ) {
        this.material = material;
        this.insulation = insulation;
        this.wireClass = wireClass;
        this.color = color;
    }

    @Override public double getResistance()               { return material.resistance(); }
    @Override public long   getAmpacity()                 { return material.ampacity(); }
    @Override public IWireClass         getWireClass()    { return wireClass; }
    @Override public IInsulationMaterial getInsulation() { return insulation; }
    @Override public IWireMaterial      getWireMaterial() { return material; }
    @Override public IWireColor         getWireColor()    { return color; }
    @Override public IWireColor         getDefaultColor() { return color; }
    @Override public TagKey<Item>       getItemTag()      { return color.getDyeTag(); }
    @Override public boolean            isDefaultColor()  { return false; }
}
