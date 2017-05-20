package net.shadowfacts.tutorial.block.projectChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.tutorial.TutorialMod;
import net.shadowfacts.tutorial.block.ModBlocks;

public class GuiProjectChest extends GuiContainer {
    private InventoryPlayer playerInv;
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(TutorialMod.modId, "textures/gui/container_project_chest.png");
    private static final int texWidth = 176;
    private static final int texHeight = 222;


    public GuiProjectChest(Container container, InventoryPlayer playerInv) {
        super(container);
        this.playerInv = playerInv;

        xSize = texWidth;
        ySize = texHeight;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1,1,1,1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        int x = (width - texWidth) / 2;
        int y = (height - texHeight) / 2;
        drawTexturedModalRect(x, y, 0, 0, texWidth, texHeight);
    }

    @Override
    public void initGui() {
        int x = (width - texWidth) / 2;
        int y = (height - texHeight) / 2;
        drawRect(x, y, x + texWidth, y + texHeight, 3);
        super.initGui();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(ModBlocks.projectChest.getUnlocalizedName() + ".name");
        fontRendererObj.drawString(name, texWidth / 2 - fontRendererObj.getStringWidth(name) / 2, 5, 0x404040);
        fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8, texHeight - 94, 0x404040);
    }
}