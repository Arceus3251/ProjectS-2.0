package com.DPN.ProjectS.commands;

import com.DPN.ProjectS.util.Reference;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
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
        float newHeight;
        if(args.length<1){
            sender.sendMessage(new TextComponentString(TextFormatting.BLUE+"Not enough arguments"));
            return;
        }
        try{
            newHeight = Float.parseFloat(args[0]);
        }
        catch(NumberFormatException ex){
            sender.sendMessage(new TextComponentString(TextFormatting.RED+"Fuck off."));
            return;
        }
        sender.sendMessage(new TextComponentString(TextFormatting.GREEN+"All's good here."));
    }
}
