package com.DPN.ProjectS.commands;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.DPN.ProjectS.Main;
import com.DPN.ProjectS.capabilities.ISizeCapability;
import com.DPN.ProjectS.capabilities.SizeProvider;
import com.DPN.ProjectS.network.MessageSizeChange;
import com.DPN.ProjectS.network.PacketHandler;
import com.DPN.ProjectS.util.EntitySizeUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandChangeHeight extends CommandBase {

    @Override
    public String getName() {
        return "setheight";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return Main.MODID + ".commands.setbasesize.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1 || args.length > 2) {
            throw new WrongUsageException(getUsage(sender), new Object[0]);
        } else {
            int i = 0;
            Entity entity = null;
            if (getCommandSenderAsPlayer(sender).getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.STICK || getCommandSenderAsPlayer(sender).getHeldItem(EnumHand.OFF_HAND).getItem() == Items.STICK) {
                int dist = 10;
                Vec3d vec3d = getCommandSenderAsPlayer(sender).getPositionEyes(1.0F);
                Vec3d vec3d1 = getCommandSenderAsPlayer(sender).getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * dist, vec3d1.y * dist, vec3d1.z * dist);
                double d1 = dist;
                Entity pointedEntity = null;
                List<Entity> list = getCommandSenderAsPlayer(sender).world.getEntitiesInAABBexcluding(getCommandSenderAsPlayer(sender), getCommandSenderAsPlayer(sender).getEntityBoundingBox().expand(vec3d1.x * dist, vec3d1.y * dist, vec3d1.z * dist).grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>() {
                    public boolean apply(@Nullable Entity entity) {
                        return entity instanceof EntityLivingBase;
                    }
                }));
                double d2 = d1;
                for (int j = 0; j < list.size(); ++j) {
                    Entity entity1 = (Entity) list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double) entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);
                    if (axisalignedbb.contains(vec3d)) {
                        if (d2 >= 0.0D) {
                            pointedEntity = entity1;
                            d2 = 0.0D;
                        }
                    } else if (raytraceresult != null) {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);

                        if (d3 < d2 || d2 == 0.0D) {
                            if (entity1.getLowestRidingEntity() == getCommandSenderAsPlayer(sender).getLowestRidingEntity() && !getCommandSenderAsPlayer(sender).canRiderInteract()) {
                                if (d2 == 0.0D) {
                                    pointedEntity = entity1;
                                }
                            } else {
                                pointedEntity = entity1;
                                d2 = d3;
                            }
                        }
                    }
                }
                entity = pointedEntity;
            }
            if(entity == null) {
                if (args.length == 1) {
                    entity = getCommandSenderAsPlayer(sender);
                } else {
                    entity = getEntity(server, sender, args[i]);
                    i = 1;
                }
            }

            if (entity.hasCapability(SizeProvider.sizeCapability, null)) {
                ISizeCapability cap = entity.getCapability(SizeProvider.sizeCapability, null);

                if (args.length != i + 1) {
                    throw new WrongUsageException(getUsage(sender), new Object[0]);
                }

                float newBaseSize = (float) parseDouble(args[i], EntitySizeUtil.HARD_MIN, EntitySizeUtil.HARD_MAX);
                if (!entity.world.isRemote) {
                    cap.setBaseSize(newBaseSize);
                    PacketHandler.INSTANCE.sendToAll(new MessageSizeChange(cap.getBaseSize(), cap.getScale(), entity.getEntityId()));
                }
            } else {
                // notifyCommandListener(sender, this, Elastic.MODID + ".commands.setbasesize.failure.capability");
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return args.length != 1 ? Collections.emptyList() : getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

}