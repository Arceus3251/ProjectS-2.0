package com.DPN.ProjectS.potions;

import com.DPN.ProjectS.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionProjectS extends Potion {

    public PotionProjectS(String name, boolean isBadEffect, int color, int iconIndexX, int iconIndexY) {
        super(isBadEffect, color);
        setPotionName("effect." + name);
        setIconIndex(iconIndexX, iconIndexY);
        setRegistryName(new ResourceLocation(Reference.MOD_ID+":"+name));
    }
    public boolean hasStatusIcon(){
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID+"textures/gui/potion_icons.png"));
        return true;
    }
}