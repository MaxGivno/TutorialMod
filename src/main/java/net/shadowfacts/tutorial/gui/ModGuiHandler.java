package net.shadowfacts.tutorial.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.shadowfacts.tutorial.block.pedestal.ContainerPedestal;
import net.shadowfacts.tutorial.block.pedestal.GuiPedestal;
import net.shadowfacts.tutorial.block.pedestal.TileEntityPedestal;
import net.shadowfacts.tutorial.block.projectChest.ContainerProjectChest;
import net.shadowfacts.tutorial.block.projectChest.GuiProjectChest;
import net.shadowfacts.tutorial.block.projectChest.TileEntityProjectChest;

public class ModGuiHandler implements IGuiHandler {
    public static final int PEDESTAL = 0;
    public static final int PROJECT_CHEST = 1;

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case PEDESTAL:
                return new ContainerPedestal(player.inventory, (TileEntityPedestal)world.getTileEntity(new BlockPos(x, y, z)));
            case PROJECT_CHEST:
                return new ContainerProjectChest(player.inventory, (TileEntityProjectChest)world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case PEDESTAL:
                return new GuiPedestal(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            case PROJECT_CHEST:
                return new GuiProjectChest(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            default:
                return null;
        }
    }
}
