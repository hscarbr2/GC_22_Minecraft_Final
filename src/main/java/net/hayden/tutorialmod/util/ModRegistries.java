package net.hayden.tutorialmod.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hayden.tutorialmod.command.ticketminecraft;

public class ModRegistries {
    public static void registerModStuffs() {
        registerCommands();
    }

    private static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(ticketminecraft::register);
    }
}
