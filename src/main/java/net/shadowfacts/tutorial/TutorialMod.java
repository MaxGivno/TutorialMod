package net.shadowfacts.tutorial;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.shadowfacts.tutorial.block.ModBlocks;
import net.shadowfacts.tutorial.client.TutorialTab;
import net.shadowfacts.tutorial.gui.ModGUIHandler;
import net.shadowfacts.tutorial.item.ModItems;
import net.shadowfacts.tutorial.proxy.CommonProxy;
import net.shadowfacts.tutorial.recipe.ModRecipes;
import net.shadowfacts.tutorial.reference.ModInfo;
import net.shadowfacts.tutorial.world.ModWorldGen;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, acceptedMinecraftVersions = ModInfo.MC_VERSION)
public class TutorialMod {

    public static final TutorialTab creativeTab = new TutorialTab();
    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6, 2, 14);
    public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", ModInfo.MOD_ID + ":copper", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    @Mod.Instance(ModInfo.MOD_ID)
    public static TutorialMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(ModInfo.MOD_NAME + " is loading!");
        ModBlocks.init();
        ModItems.init();
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(TutorialMod.instance, new ModGUIHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SidedProxy(serverSide = ModInfo.SERVER_SIDE_CLASS, clientSide = ModInfo.CLIENT_SIDE_CLASS)
    public static CommonProxy proxy;

}