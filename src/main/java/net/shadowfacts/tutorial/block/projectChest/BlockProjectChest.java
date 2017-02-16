package net.shadowfacts.tutorial.block.projectChest;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.shadowfacts.tutorial.block.BlockTileEntity;

import javax.annotation.Nullable;

public class BlockProjectChest extends BlockTileEntity<TileEntityProjectChest> {

    public BlockProjectChest() {
        super(Material.WOOD, "projectChest");
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
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
