package net.shadowfacts.tutorial.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.shadowfacts.tutorial.TutorialMod;
import net.shadowfacts.tutorial.item.ModItems;

public class TutorialTab extends CreativeTabs {

    public TutorialTab() {
        super(TutorialMod.modId);
        setBackgroundImageName("item_search.png");
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.ingotCopper;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
