package net.shadowfacts.tutorial.block.pedestal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ContainerPedestal extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer player) {
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
        }

        return super.transferStackInSlot(player, index);
    }
}
