package com.example.forgedlead;

import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ForgedLead.MOD_ID)
public class ForgedLead {
    public static final String MOD_ID = "forgedlead";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public ForgedLead() {
        LOGGER.info("ForgedLead loaded — leads will never break!");
    }
}
