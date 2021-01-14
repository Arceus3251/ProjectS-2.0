package com.DPN.ProjectS.init;

import com.DPN.ProjectS.Main;
import com.DPN.ProjectS.potions.PotionProjectS;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModPotions {

    public static final Potion SHRINKING = new PotionProjectS("shrinking", false, 0xff0000,0 ,0);

    public static final PotionType SHRINKING_POTION = new PotionType("shrinking", new PotionEffect[] {new PotionEffect(SHRINKING, 2400)}).setRegistryName("shrinking");
    public static final PotionType LONG_SHRINKING_POTION = new PotionType("shrinking", new PotionEffect[] {new PotionEffect(SHRINKING, 4800)}).setRegistryName("long_shrinking");

    public static final Potion GROWING_POTION = new PotionProjectS("growing", false, 0x00ff00, 1, 0);

    public static void registerPotions(){
        registerPotion(SHRINKING_POTION, LONG_SHRINKING_POTION, SHRINKING);
        registerPotionMixes();
    }
    private static void registerPotion(PotionType defaultPotion, PotionType longPotion, Potion effect){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }
    private static void registerPotionMixes(){
        PotionHelper.addMix(SHRINKING_POTION, Items.REDSTONE, LONG_SHRINKING_POTION);
        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.PLACEHOLDER_BANANA, SHRINKING_POTION);
    }
}
