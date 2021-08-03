package xyz.starmun.starpackcore;

import dev.ftb.mods.ftblibrary.config.ui.SelectItemStackScreen;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(StarpackCore.MOD_ID)
public class StarpackCore {
    public static final String MOD_ID = "starpackcore";
    public static final String MOD_NAME = "Starpack Core";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public StarpackCore(){
        try {
            StarpackCore.LOGGER.info(Class.forName(SelectItemStackScreen.class.getCanonicalName()+"$ItemStackButton"));
        }
        catch (Exception ex){

        }
    }

}
