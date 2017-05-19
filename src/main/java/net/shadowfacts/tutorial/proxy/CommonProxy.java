package net.shadowfacts.tutorial.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
//import net.minecraft.util.text.translation.I18n;

public class CommonProxy {
    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public String localize(String unlocalized, Object... args) {
        //return I18n.translateToLocalFormatted(unlocalized, args);
        ITextComponent text = new TextComponentTranslation(unlocalized, args);
        return text.getFormattedText();
    }

    public void registerRenderers() {}

}
