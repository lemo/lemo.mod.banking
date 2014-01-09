package lemo.mods.banking;

import lemo.mods.banking.items.base.PurseItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BankingTab extends CreativeTabs {
	// static
	public static BankingTab instance() {
		if (instance == null) {
			instance = new BankingTab("tabBanking");
		}
		return instance;
	}

	private static BankingTab instance = null;

	// instance

	public BankingTab(String par2Str) {
		super(getNextID(), par2Str);
	}

	public ItemStack getIconItemStack() {
		return new ItemStack(PurseItem.item, 1, 0);
	}

}
