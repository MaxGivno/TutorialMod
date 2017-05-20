package net.shadowfacts.tutorial.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.shadowfacts.tutorial.block.projectChest.TileEntityProjectChest;

public class PacketRequestUpdateProjectChest implements IMessage {

    private BlockPos pos;
    private int dimension;

    public PacketRequestUpdateProjectChest(BlockPos pos, int dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    public PacketRequestUpdateProjectChest(TileEntityProjectChest te) {
        this(te.getPos(), te.getWorld().provider.getDimension());
    }

    public PacketRequestUpdateProjectChest() {}

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketRequestUpdateProjectChest, PacketUpdateProjectChest> {

        @Override
        public PacketUpdateProjectChest onMessage(PacketRequestUpdateProjectChest message, MessageContext ctx) {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(message.dimension);
            TileEntityProjectChest te = (TileEntityProjectChest)world.getTileEntity(message.pos);
            if (te != null) {
                return new PacketUpdateProjectChest(te);
            } else {
                return null;
            }
        }
    }
}
