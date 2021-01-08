package com.DPN.ProjectS.commands;

import com.DPN.ProjectS.capabilities.ISizeCapability;
import com.DPN.ProjectS.capabilities.SizeProvider;
import com.DPN.ProjectS.commands.util.SizeHandler;
import com.DPN.ProjectS.network.MessageSizeChange;
import com.DPN.ProjectS.network.PacketHandler;
import com.DPN.ProjectS.util.EntitySizeUtil;
import com.DPN.ProjectS.util.Reference;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.Objects;

public class CommandChangeHeight extends CommandBase {
    private final List<String> aliases = Lists.newArrayList(Reference.MOD_ID, "setheight");
    @Override
    public String getName() {
        return "setheight";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/setheight <newheight>";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1 || args.length > 2) {
            throw new WrongUsageException(getUsage(sender), new Object[0]);
        } else {
            int i = 0;
            Entity entity;

            if (args.length == 1) {
                entity = getCommandSenderAsPlayer(sender);
            } else {
                entity = getEntity(server, sender, args[i]);
                i = 1;
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
}
