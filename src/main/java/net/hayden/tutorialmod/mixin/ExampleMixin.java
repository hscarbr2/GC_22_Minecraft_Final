package net.hayden.tutorialmod.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//This code injects our code into the fabric (no pun intended) of the minecraft infrastructure.
//For example, when you create command code for a mod,
//you have to inject the code into the MinecraftServer Code.

//Below you will see that we inject our code in the "loadworld" method in the infrastructure code.
//Using the @At("HEAD") function - this allows us to inject the code at the BEGINNING of the loadworld method.
@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
	}
}