package net.shadowfacts.tutorial.item;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.shadowfacts.tutorial.TutorialMod;
import net.shadowfacts.tutorial.block.ModBlocks;

public class ItemCornSeed extends ItemSeeds implements ItemModelProvider {

    public ItemCornSeed() {
        super(ModBlocks.cropCorn, Blocks.FARMLAND);
        setUnlocalizedName("corn_seed");
        setRegistryName("corn_seed");
        setCreativeTab(TutorialMod.creativeTab);
    }

    @Override
    public void registerItemModel(Item item) {
        TutorialMod.proxy.registerItemRenderer(item, 0, "corn_seed");
    }

}