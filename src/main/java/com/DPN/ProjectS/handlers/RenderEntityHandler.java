package com.DPN.ProjectS.handlers;

import com.DPN.ProjectS.Main;
import com.DPN.ProjectS.capabilities.ISizeCapability;
import com.DPN.ProjectS.capabilities.SizeProvider;
import com.DPN.ProjectS.util.EntitySizeUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(value = Side.CLIENT, modid = Main.MODID)
public class RenderEntityHandler {
    public static ArrayList<Multiplier> cache = new ArrayList<>();


    @SubscribeEvent
    public static void playSoundAtEntity(PlaySoundAtEntityEvent event) {
        Entity entity = Minecraft.getMinecraft().player;
        if (entity != null) {
            float entitySize = EntitySizeUtil.getEntityScale(entity);
            event.setVolume(event.getVolume() * MathHelper.sqrt(entitySize));
            event.setPitch(event.getPitch() * entitySize);
        }
    }


    @SubscribeEvent
    public static void renderEntityPre(RenderLivingEvent.Pre event) {
        float scale = EntitySizeUtil.getEntityScale(event.getEntity());
        GlStateManager.pushMatrix();

        GlStateManager.scale(scale, scale, scale);
        GlStateManager.translate((event.getX() / scale) - event.getX(), (event.getY() / scale) - event.getY(),
                (event.getZ() / scale) - event.getZ());
        if (event.getEntity().isSneaking()) {
            GlStateManager.translate(0, 0.125F / scale, 0);
            GlStateManager.translate(0, -0.125F, 0);
        }

        if (event.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) event.getEntity();
            if (entity.hasCapability(SizeProvider.sizeCapability, null)) {
                ISizeCapability size = entity.getCapability(SizeProvider.sizeCapability, null);
                double d5 = entity.posX - entity.prevPosX;
                double d7 = entity.posZ - entity.prevPosZ;
                double d9 = entity instanceof net.minecraft.entity.passive.EntityFlying ? entity.posY - entity.prevPosY : 0.0D;
                float f10 = MathHelper.sqrt(d5 * d5 + d9 * d9 + d7 * d7) * 4.0F * 1 / size.getActualSize();

                if (f10 > 1.0F) {
                    f10 = 1.0F;
                }
                entity.prevLimbSwingAmount = size.getLimbSwingAmount();
                size.setLimbSwingAmount(size.getLimbSwingAmount() + (f10 - size.getLimbSwingAmount()) * 0.4F);
                if (entity.hurtTime == entity.maxHurtTime && entity.maxHurtTime > 0) {
                    size.setLimbSwingAmount(1.5F);
                }
                entity.limbSwingAmount = size.getLimbSwingAmount();
            }
        }
    }

    @SubscribeEvent
    public static void renderEntityPost(RenderLivingEvent.Post event) {
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public static void renderEntityNamePre(RenderLivingEvent.Specials.Pre event) {
        float scale = EntitySizeUtil.getEntityScale(event.getEntity());

        GlStateManager.pushMatrix();

        boolean flag = event.getEntity().isSneaking();
        float vanillaOffset = event.getEntity().height + 0.5F - (flag ? 0.25F : 0.0F);

        GlStateManager.translate(0, -vanillaOffset, 0);

        float adjustedOffset = (event.getEntity().height / scale) + (0.5F) - (flag ? 0.25F : 0F);

        GlStateManager.translate(0, adjustedOffset, 0);
    }

    @SubscribeEvent
    public static void renderEntityNamePost(RenderLivingEvent.Specials.Post event) {
        GlStateManager.popMatrix();
    }

    @SubscribeEvent
    public static void setupCamera(EntityViewRenderEvent.CameraSetup event) {
        Entity entity = event.getEntity();
        float scale = EntitySizeUtil.getEntityScale(entity);
        if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)Minecraft.getMinecraft().getRenderViewEntity();
            float distanceWalked = entityplayer.distanceWalkedModified *  30;
            float distanceWalkedPrev = entityplayer.prevDistanceWalkedModified * 30;
            float f = distanceWalked - distanceWalkedPrev;
            float f1 = -(distanceWalked + f * (float)event.getRenderPartialTicks());
            float f2 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * (float)event.getRenderPartialTicks();
            float f3 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * (float)event.getRenderPartialTicks();
            GlStateManager.translate(MathHelper.sin(f1 * (float)Math.PI) * f2 * 0.5F, -Math.abs(MathHelper.cos(f1 * (float)Math.PI) * f2), 0.0F);
            GlStateManager.rotate(MathHelper.sin(f1 * (float)Math.PI) * f2 * 3.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(Math.abs(MathHelper.cos(f1 * (float)Math.PI - 0.2F) * f2) * 5.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
        }
        if (!(event.getEntity() instanceof EntityLivingBase && ((EntityLivingBase) event.getEntity()).isPlayerSleeping())) {
            GlStateManager.translate(0, 0, -0.05F + (scale * 0.05F));
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void renderGui(GuiScreenEvent.DrawScreenEvent.Post event) {
        GlStateManager.disableBlend();
        for (Multiplier m : cache) {
            int mouseX = event.getMouseX();
            int mouseY = event.getMouseY();

            if (event.getGui() instanceof GuiInventory && m.x - mouseX <= 12 && mouseX - m.x <= 12 && m.y - mouseY >= 0 && m.y - mouseY <= 56) {
                event.getGui().drawHoveringText(m.string, mouseX, mouseY);
            } else if (event.getGui() instanceof GuiContainerCreative && m.x - mouseX <= 9 && mouseX - m.x <= 9 && m.y - mouseY >= 0 && m.y - mouseY <= 38) {
                event.getGui().drawHoveringText(m.string, mouseX, mouseY);
            }
        }
        cache.clear();
    }

    @SideOnly(Side.CLIENT)
    public static void registerMultiplier(int x, int y, String string) {
        cache.add(new Multiplier(x, y, string));
    }

    private static class Multiplier {
        public int x, y;
        public String string;

        public Multiplier(int x, int y, String string) {
            this.x = x;
            this.y = y;
            this.string = string;
        }
    }

}