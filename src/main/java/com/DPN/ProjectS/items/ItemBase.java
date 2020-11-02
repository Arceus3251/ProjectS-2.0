package com.DPN.ProjectS.items;

import com.DPN.ProjectS.Main;
import com.DPN.ProjectS.init.ModItems;
import com.DPN.ProjectS.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

    public ItemBase(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.clientProxy.registerItemRenderer(this, 0, "inventory");
    }
}
