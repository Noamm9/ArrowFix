package com.addons.mdk.commands;

import com.addons.mdk.ArrowFix;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class ToggleCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "arrowfix";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        try {
            ArrowFix.isEnabled = !ArrowFix.isEnabled;
            ArrowFix.addChatMessage(ArrowFix.isEnabled ? "Enabled!" : "Disabled!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
