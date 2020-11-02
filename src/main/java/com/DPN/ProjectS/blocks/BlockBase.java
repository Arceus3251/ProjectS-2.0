package com.DPN.ProjectS.blocks;

import com.DPN.ProjectS.Main;
import com.DPN.ProjectS.init.ModBlocks;
import com.DPN.ProjectS.init.ModItems;
import com.DPN.ProjectS.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
    public BlockBase(String name, Material material, SoundType sound){
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        setSoundType(sound);
    }

    @Override
    public void registerModels() {
        Main.clientProxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
