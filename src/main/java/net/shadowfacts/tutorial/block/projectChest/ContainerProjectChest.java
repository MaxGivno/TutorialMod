package net.shadowfacts.tutorial.block.projectChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class ContainerProjectChest extends Container {

    public ContainerProjectChest(InventoryPlayer playerInv, final TileEntityProjectChest projectChest) {
        IItemHandler inventory = projectChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

        int slotIndex = 0;

        //Main slot
        addSlotToContainer(new SlotItemHandler(inventory, 0, 80, 18) {
            @Override
            public void onSlotChanged() {
                projectChest.markDirty();
            }
        });

        slotIndex++;

        //Chest inventory slots
        for (int row =0; row < 3; ++row) {
            for (int col =0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 54;
                addSlotToContainer(new SlotItemHandler(inventory, slotIndex, x, y) {
                    @Override
                    public void onSlotChanged() {
                        projectChest.markDirty();
                    }
                });
                slotIndex++;
            }
        }

        // Player inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
            }
        }

        //Player hotbar
        for (int k = 0; k < 9; k++) {
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 198));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Nullable
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!this.mergeItemStack(itemStack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack1, 0, containerSlots,false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemStack1);
        }

        return itemStack;
    }


}
