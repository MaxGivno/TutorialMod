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
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.shadowfacts.tutorial.block.ModBlocks;
import net.shadowfacts.tutorial.client.TutorialTab;
import net.shadowfacts.tutorial.gui.ModGuiHandler;
import net.shadowfacts.tutorial.item.ModItems;
import net.shadowfacts.tutorial.network.PacketRequestUpdatePedestal;
import net.shadowfacts.tutorial.network.PacketRequestUpdateProjectChest;
import net.shadowfacts.tutorial.network.PacketUpdatePedestal;
import net.shadowfacts.tutorial.network.PacketUpdateProjectChest;
import net.shadowfacts.tutorial.proxy.CommonProxy;
import net.shadowfacts.tutorial.recipe.ModRecipes;
import net.shadowfacts.tutorial.world.ModWorldGen;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.VERSION, acceptedMinecraftVersions = ModInfo.MINECRAFT_VERSION)
public class TutorialMod {

    public static final String modId = ModInfo.MOD_ID;
    public static final String name = ModInfo.MOD_NAME;
    public static final String version = ModInfo.VERSION;

    @Mod.Instance(modId)
    public static TutorialMod instance;

    @SidedProxy(serverSide = ModInfo.SERVER_SIDE_CLASS, clientSide = ModInfo.CLIENT_SIDE_CLASS)
    public static CommonProxy proxy;

    public static final TutorialTab creativeTab = new TutorialTab();
    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6, 2, 14);
    public static final ItemArmor.ArmorMaterial copperArmorMaterial = EnumHelper.addArmorMaterial("COPPER", modId + ":copper", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");

        ModBlocks.init();
        ModItems.init();
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        proxy.registerRenderers();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(modId);
        network.registerMessage(new PacketUpdatePedestal.Handler(), PacketUpdatePedestal.class, 0, Side.CLIENT);
        network.registerMessage(new PacketRequestUpdatePedestal.Handler(), PacketRequestUpdatePedestal.class, 1, Side.SERVER);
        network.registerMessage(new PacketUpdateProjectChest.Handler(), PacketUpdateProjectChest.class, 2, Side.CLIENT);
        network.registerMessage(new PacketRequestUpdateProjectChest.Handler(), PacketRequestUpdateProjectChest.class, 3, Side.SERVER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}