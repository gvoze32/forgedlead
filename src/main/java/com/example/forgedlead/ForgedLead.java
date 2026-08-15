package com.example.forgedlead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//? if fabric {
/*import net.fabricmc.api.ModInitializer;
*///?}
//? if forge {
/*import net.minecraftforge.fml.common.Mod;
*///?}
//? if neoforge {
import net.neoforged.fml.common.Mod;
//?}

//? if fabric {
/*public final class ForgedLead implements ModInitializer {
    public static final String MOD_ID = "forgedlead";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("ForgedLead loaded - leads will never break!");
    }
}
*///?} else if neoforge {
@Mod(ForgedLead.MOD_ID)
public final class ForgedLead {
    public static final String MOD_ID = "forgedlead";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public ForgedLead() {
        LOGGER.info("ForgedLead loaded - leads will never break!");
    }
}
//?} else {
/*@Mod(ForgedLead.MOD_ID)
public final class ForgedLead {
    public static final String MOD_ID = "forgedlead";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public ForgedLead() {
        LOGGER.info("ForgedLead loaded - leads will never break!");
    }
}
*///?}
