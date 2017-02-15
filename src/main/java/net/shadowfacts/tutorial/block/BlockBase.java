package net.shadowfacts.tutorial.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.shadowfacts.tutorial.TutorialMod;
import net.shadowfacts.tutorial.item.ItemModelProvider;
import net.shadowfacts.tutorial.reference.ModInfo;

public class BlockBase extends Block implements ItemModelProvider {

    protected String name;

    public BlockBase(Material material, String name) {
        super(material);

        this.name = name;

        setUnlocalizedName(name);
        setRegistryName(ModInfo.MOD_ID, name);
        setCreativeTab(TutorialMod.creativeTab);
    }

    @Override
    public void registerItemModel(Item itemBlock) {
        TutorialMod.proxy.registerItemRenderer(itemBlock,0, name);
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}
