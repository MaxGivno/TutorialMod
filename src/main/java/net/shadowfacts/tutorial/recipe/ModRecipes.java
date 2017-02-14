package net.shadowfacts.tutorial.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.shadowfacts.tutorial.block.ModBlocks;
import net.shadowfacts.tutorial.item.ModItems;

public class ModRecipes {

    public static void init() {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.cornSeed), ModItems.corn);
        GameRegistry.addShapedRecipe(new ItemStack(Items.RABBIT_STEW), " R ", "CPM", " B ", 'R', Items.COOKED_RABBIT, 'C', ModItems.corn, 'P', Items.BAKED_POTATO, 'M', Blocks.BROWN_MUSHROOM, 'B', Items.BOWL);
        GameRegistry.addSmelting(ModBlocks.oreCopper, new ItemStack(ModItems.ingotCopper), 0.7f);
        GameRegistry.addRecipe(new ShapedOreRecipe(Items.BUCKET, "I I", " I ", 'I', "ingotCopper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperSword), " I ", " I ", " S ", 'I', "ingotCopper", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperPickaxe), "III", " S ", " S ", 'I', "ingotCopper", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperShovel), " I ", " S ", " S ", 'I', "ingotCopper", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperAxe), "II ", "IS ", " S ", 'I', "ingotCopper", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperAxe), " II", " SI", " S ", 'I', "ingotCopper", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperHoe), "II ", " S ", " S ", 'I', "ingotCopper", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperHoe), " II", " S ", " S ", 'I', "ingotCopper", 'S', Items.STICK));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperHelmet), "III", "I I", 'I', "ingotCopper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperChestplate), "I I", "III", "III", 'I', "ingotCopper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperLeggings), "III", "I I", "I I", 'I', "ingotCopper"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.copperBoots), "I I", "I I", 'I', "ingotCopper"));
    }
}
