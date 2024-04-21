package net.hayden.tutorialmod.mixin;


import net.hayden.tutorialmod.util.IEntityDataSaver;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.swing.text.html.parser.Entity;


//This code injects our code into the fabric (no pun intended) of the minecraft infrastructure.
//For example, when you create command code for a mod,
//you have to inject the code into the MinecraftServer Code.

//Below you will see that we inject our code in the "writenbt" and "readnbt" method in the infrastructure code.
//Using the @At("HEAD") function - this allows us to inject the code at the BEGINNING of the writenbt and readnbt methods.

@Mixin(Entity.class)
public abstract class ModEntityDataSaver implements IEntityDataSaver {
    private NbtCompound persistentData;

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
        if(persistentData != null) {
            nbt.put("tutorialmod.hayden.data", persistentData);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if(nbt.contains("tutorialmod.hayden.data"));
    }

    @Override
    public NbtCompound getPersistentData() {
        if(this.persistentData == null) {
            this.persistentData = new NbtCompound();

        }
        return persistentData;
    }
}
