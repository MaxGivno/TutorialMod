package net.shadowfacts.tutorial.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.shadowfacts.tutorial.block.projectChest.ContainerProjectChest;
import net.shadowfacts.tutorial.block.projectChest.GuiProjectChest;
import net.shadowfacts.tutorial.block.projectChest.TileEntityProjectChest;

public class ModGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityProjectChest) {
            return new ContainerProjectChest(player.inventory, (TileEntityProjectChest) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityProjectChest) {
            TileEntityProjectChest containerTileEntity = (TileEntityProjectChest) te;
            return new GuiProjectChest(containerTileEntity, new ContainerProjectChest(player.inventory, containerTileEntity));
        }
        return null;
    }
}
