package net.hayden.tutorialmod;

import net.fabricmc.api.ModInitializer;
import net.hayden.tutorialmod.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//This code in an intializer that allows for the registration of our command code to the overall commands of Minecraft.
//Once the code is injected into the Minecraft infrastructure code, you have to register it as an active participant in the command list.
//This is what this code accomplishes.
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		ModRegistries.registerModStuffs();
	}


}