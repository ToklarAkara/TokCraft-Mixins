package com.toklar.tokcraftmixins.helpers;

import net.minecraft.item.Item;

public class ItemDummy extends Item {
  public static final ItemDummy INSTANCE = new ItemDummy();

  public ItemDummy() {
    this.setTranslationKey("tokcraft_dummy_part");
    this.setRegistryName("tokcraftmixins", "dummy_part");
  }
}