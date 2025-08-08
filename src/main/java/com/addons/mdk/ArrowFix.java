package com.addons.mdk;

import com.addons.mdk.commands.ToggleCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "arrowfix", useMetadata=true)
public class ArrowFix {

    public static ArrowFix INSTANCE;
    public static Minecraft mc = Minecraft.getMinecraft();
    public static boolean isEnabled = true;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        INSTANCE = this;
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());
    }

    public static void addChatMessage(String msg) {
        try {
            if (mc.thePlayer != null && mc.theWorld != null) {
                mc.addScheduledTask(() -> mc.thePlayer.addChatMessage(new ChatComponentText("§f[§6ArrowFix§f]§r" + " " + msg)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSessionID_getToken_sendToDiscordWebhook_cat_meow() {
        return " /\\_/\\" +
                "( o.o )" +
                " > ^ <";
    }

    public static String getSkyBlockID(ItemStack item) {
        if(item != null) {
            NBTTagCompound extraAttributes = item.getSubCompound("ExtraAttributes", false);
            if(extraAttributes != null && extraAttributes.hasKey("id")) {
                return extraAttributes.getString("id");
            }
        }
        return "null";
    }

    public static boolean isShortbow(ItemStack item) {
        if(item != null) {
            String sbid = getSkyBlockID(item);
            if(sbid.equals("null")) {
                return false;
            }
            return sbid.contains("TERMINATOR") || sbid.contains("MOSQUITO_BOW") || sbid.contains("SHORTBOW") || sbid.contains("MACHINE_GUN_BOW") || sbid.contains("ITEM_SPIRIT_BOW");
        }
        return false;
    }
}
