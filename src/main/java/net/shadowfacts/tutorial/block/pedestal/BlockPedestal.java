package net.shadowfacts.tutorial.block.pedestal;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.shadowfacts.tutorial.TutorialMod;
import net.shadowfacts.tutorial.block.BlockTileEntity;

import javax.annotation.Nullable;

public class BlockPedestal extends BlockTileEntity<TileEntityPedestal> {

    private static final AxisAlignedBB PEDESTAL_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.8125D, 0.9375D);

    public BlockPedestal() {
        super(Material.ROCK, "pedestal");
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return PEDESTAL_AABB;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) { // On Server
            TileEntityPedestal tile = getTileEntity(world, pos); // Get TE
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side); // Attaching handler for inventory support

            if (!player.isSneaking()) { // If not sneaking
                if (heldItem == null) { // with empty hand
                    player.setHeldItem(hand, itemHandler.extractItem(0, 64, false)); // Get items from the slot
                } else { // Hand's not empty
                    player.setHeldItem(hand, itemHandler.insertItem(0, heldItem, false));  // Put items into the slot
                }
                tile.markDirty(); // Update TE
            } else { // If sneaking
                ItemStack stack = itemHandler.getStackInSlot(0); // Check what's in slot 0

                if (stack != null) { // If smth in the slot
                    String localized = TutorialMod.proxy.localize(stack.getUnlocalizedName() + ".name"); // Get name of item in the slot
                    player.addChatMessage(new TextComponentString(stack.stackSize + "x " + localized)); // Write to chat number of items in the slot
                } else { // If slot is empty
                    player.addChatMessage(new TextComponentString("Empty")); // Write to chat that slot is empty
                }
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntityPedestal tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (stack != null) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntityInWorld(item);
        }
        super.breakBlock(world, pos, state);
    }

    @Override
    public Class<TileEntityPedestal> getTileEntityClass() {
        return TileEntityPedestal.class;
    }

    @Nullable
    @Override
    public TileEntityPedestal createTileEntity(World world, IBlockState state) {
        return new TileEntityPedestal();
    }
}
