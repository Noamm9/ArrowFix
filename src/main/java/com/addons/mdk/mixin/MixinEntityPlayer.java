package com.addons.mdk.mixin;

import com.addons.mdk.ArrowFix;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.addons.mdk.ArrowFix.mc;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer {

    @Shadow
    private ItemStack itemInUse;
    @Shadow
    public InventoryPlayer inventory;
    @Final
    @Shadow
    private GameProfile gameProfile;
    @Shadow
    private int itemInUseCount;

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void fixPullBack(CallbackInfo ci){
        try {
            if(!ArrowFix.isEnabled || mc.thePlayer == null || mc.theWorld == null) {
                return;
            }
            if (itemInUse != null && inventory != null && gameProfile != null){
                if (gameProfile == mc.thePlayer.getGameProfile()) {
                    ItemStack itemStack = inventory.getCurrentItem();
                    if (itemStack != null && itemStack.getItem() instanceof ItemBow) {
                        if(ArrowFix.isShortbow(itemStack)) {
                            itemInUse = null;
                            itemInUseCount = 0;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}