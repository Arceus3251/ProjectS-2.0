package com.DPN.ProjectS.init;

import com.DPN.ProjectS.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static final List<Block> BLOCKS = new ArrayList<Block>();
    //Add new blocks below. Follow the place holder example. Don't forget to update in the resources folder in The assets for Lang, Models, and Textures folders.
    public static final Block PLACEHOLDER_BLOCK = new BlockBase("placeholder_block", Material.CLOTH, SoundType.CLOTH);
}
