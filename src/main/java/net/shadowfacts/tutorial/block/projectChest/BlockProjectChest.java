package net.shadowfacts.tutorial.block.projectChest;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.shadowfacts.tutorial.TutorialMod;
import net.shadowfacts.tutorial.block.BlockTileEntity;
import net.shadowfacts.tutorial.gui.ModGuiHandler;

import javax.annotation.Nullable;

public class BlockProjectChest extends BlockTileEntity<TileEntityProjectChest> {

    public BlockProjectChest() {
        super(Material.WOOD, "project_chest");
        blockHardness = 3;
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityProjectChest te = getTileEntity(world, pos);

            if (te == null) {
                return false;
            }

            player.openGui(TutorialMod.instance, ModGuiHandler.PROJECT_CHEST, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityProjectChest tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

        for (int i = 0; i < itemHandler.getSlots(); ++i)
        {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                //world.spawnEntityInWorld(item);
                world.spawnEntity(item);
            }
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public Class<TileEntityProjectChest> getTileEntityClass() {
        return TileEntityProjectChest.class;
    }

    @Nullable
    @Override
    public TileEntityProjectChest createTileEntity(World world, IBlockState state) {
        return new TileEntityProjectChest();
    }
}
