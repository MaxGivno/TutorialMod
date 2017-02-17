package net.shadowfacts.tutorial.block.projectChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.tutorial.reference.ModInfo;

public class GuiProjectChest extends GuiContainer {

    public static final int WIDTH = 176;
    public static final int HEIGHT = 222;

    private static final ResourceLocation background = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/containerProjectChest.png");

    public GuiProjectChest(TileEntityProjectChest tileEntity, ContainerProjectChest container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
