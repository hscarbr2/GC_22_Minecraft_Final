package net.hayden.tutorialmod.command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import java.util.Objects;
//This code below creates the commands using the fabric API.
//Using the CommandManager package to register the commands to the larger list of Minecraft commands.
//CommandManager.literal("modrequest") gives you the main command to where the player can use "/modrequest" to call the
//Minecraft Mod.

//The "then(CommandManager.literal("x") code creates the subcommands to give the individual ticket functionality.
//The Player will user the "create" subcommand to create the ticket.
//The Admin team will use the "claim" and "close" subcommand to update.
//In the .executes(x) code, shows which private static code is executed when the player or admin team executes the subcommand.
public class ticketminecraft {

    public static int register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("modrequest").then(CommandManager.literal("create").executes(ticketminecraft::run)));
        dispatcher.register(CommandManager.literal("modrequest").then(CommandManager.literal("claim").executes(ticketminecraft::adminclaim)));
        dispatcher.register(CommandManager.literal("modrequest").then(CommandManager.literal("close").executes(ticketminecraft::adminclose)));

        return 1;
    }
//These private methods below give the instructions on the actions that occur when you execute the Minecraft Mod and subcommands.
//The run script is executed when the player types /modrequest create in the chat.
    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
//The below code shows the information that is pulled for the database to store as periphery information when a player creates a ticket.
//String input = String.valueOf(context.getSource().getPlayer()); gets the player ID or UUID from the Minecraft Server to save
        String input = String.valueOf(context.getSource().getPlayer());
 //The following three lines get the X,Y,Z position of the player at the time of their creation of a ticket.
        int playerposX = Objects.requireNonNull(context.getSource().getPlayer()).getBlockPos().getX();
        int playerposY = Objects.requireNonNull(context.getSource().getPlayer()).getBlockPos().getY();
        int playerposZ = Objects.requireNonNull(context.getSource().getPlayer()).getBlockPos().getZ();
//The variable world uses the fabric API to get the string value of the current world the player is currently located and submitting their ticket.
        String world = String.valueOf(context.getSource().getPlayer().getWorld());
//These next block of code takes the data above that is stored in their local variables and then sends it back to the chat for the user's
//piece of mind that they have submitted their ticket and they can review the information that was submitted.
        context.getSource().sendMessage(Text.of("Ticket successfully reported - Help is on the way!"));
        context.getSource().sendMessage(Text.of("Minecraft World: "+world));
        context.getSource().sendMessage(Text.of("X Coordinate: " +playerposX));
        context.getSource().sendMessage(Text.of("Y Coordinate: " +playerposY));
        context.getSource().sendMessage(Text.of("Z Coordinate: " +playerposZ));
        return 1;
    }
//The adminclaim code is executed when the admin team types "/modrequest claim'
//The status of the ticket is set as claim is false at the beginning
    private static boolean ticketClaimed = false;
    private static int adminclaim(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
//This will get the name of the admin attempting to use the claim ticket by using the fabric API
        String name = String.valueOf(context.getSource().getName());
//Once the claimed ticket subcommand is executed then there is success message that is sent to the chat for the admin team to see that they have claimed the ticket.
        context.getSource().sendMessage(Text.of("Ticket successfully claimed by: "+name));
        return 1;
    }
//This adminclose script allows for the admin team to close the ticket that is claimed.
    private static int adminclose(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
//Once the script is executed then a message is sent back to the chat so the admin will be able to see the success of closing the ticket.
        String name = String.valueOf(context.getSource().getName());
        context.getSource().sendMessage(Text.of("Ticket successfully closed by: " +name));
        return 1;
    }


}
