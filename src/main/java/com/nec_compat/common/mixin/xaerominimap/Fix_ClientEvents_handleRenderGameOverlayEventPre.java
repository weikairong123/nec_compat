package com.nec_compat.common.mixin.xaerominimap;



 import net.minecraft.client.Minecraft;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.Inject;
 import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



 // remap=false：xaero是未映射knot混淆类，不能开启自动重映射
 @Mixin(value = xaero.common.events.ClientEvents.class, remap = false)
 public abstract class MixinXaeroClientEvents {
     // 在handleRenderGameOverlayEventPre整个方法最开头注入
     @Inject(method = "handleRenderGameOverlayEventPre", at = @At("HEAD"), cancellable = true)
     private void fixNecLoopCrash(CallbackInfo ci) {
         Minecraft mc = Minecraft.getInstance();
         // NEC捕获崩溃后 mc.level 为null，直接跳过整个渲染方法，阻止NPE
         if (mc.level == null) {
             ci.cancel();
         }
     }
 }
 
 
 
