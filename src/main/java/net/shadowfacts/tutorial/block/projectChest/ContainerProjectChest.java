package net.shadowfacts.tutorial.block.projectChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class ContainerProjectChest extends Container {

    private TileEntityProjectChest te;

    public ContainerProjectChest(IInventory playerInv, TileEntityProjectChest te) {
        this.te = te;

        addOwnSlots();
        addPlayerSlots(playerInv);
    }

    private void addPlayerSlots(IInventory playerInventory) {
        int deltaX = 8;
        int deltaY = 140;

        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = deltaX + col * 18;
                int y = row * 18 + deltaY;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = deltaX + row * 18;
            int y = 58 + deltaY;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        int x = 8;
        int y = 18;

        // Add our own slots
        int slotIndex = 0;
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, x, y));
            slotIndex++;
            x += 18;
        }
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.te.SIZE) {
                if (!this.mergeItemStack(itemstack1, this.te.SIZE, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, this.te.SIZE, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }
}
