package net.shadowfacts.tutorial.block.projectChest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.ForgeHooksClient;
import net.shadowfacts.tutorial.block.ModBlocks;
import org.lwjgl.opengl.GL11;

public class TESRProjectChest extends TileEntitySpecialRenderer <TileEntityProjectChest> {
    @Override
    public void renderTileEntityAt(TileEntityProjectChest projectChest, double x, double y, double z, float partialTicks, int destroyStage) {
        if (!projectChest.getWorld().isBlockLoaded(projectChest.getPos()) || projectChest.getWorld().getBlockState(projectChest.getPos()).getBlock() != ModBlocks.projectChest) {
            return;
        }

        ItemStack stack = projectChest.inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
            GlStateManager.enableBlend();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
//            double offset = Math.sin((te.getWorld().getTotalWorldTime() - te.lastChangeTime + partialTicks) / 8) / 4.0;
//            GlStateManager.translate(x + 0.5, y + 1.25 + offset, z + 0.5);
            GlStateManager.translate(x + 0.5, y + 1.15, z + 0.5);
//            GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4, 0, 1, 0);

            IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, projectChest.getWorld(), null);
            model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

            GlStateManager.color(1,0,0,0);
//            GlStateManager.resetColor();

            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);

            GlStateManager.popMatrix();
            GlStateManager.disableBlend();
            GlStateManager.disableRescaleNormal();
        }
    }
}
