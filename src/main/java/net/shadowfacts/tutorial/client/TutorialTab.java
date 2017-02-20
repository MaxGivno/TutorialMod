package net.shadowfacts.tutorial.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.shadowfacts.tutorial.item.ModItems;
import net.shadowfacts.tutorial.ModInfo;

public class TutorialTab extends CreativeTabs {

    public TutorialTab() {
        super(ModInfo.MOD_ID);
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
