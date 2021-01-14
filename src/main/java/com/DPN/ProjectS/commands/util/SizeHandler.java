package com.DPN.ProjectS.commands.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class SizeHandler extends Entity {
    private final Entity entity;
    private final float height;
    public SizeHandler(World worldIn, Entity entity, float height) {
        super(worldIn);
        this.entity = entity;
        this.height = height;
        setSize(height, height);
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }
}
