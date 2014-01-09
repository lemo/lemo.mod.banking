package lemo.mods.banking.items.base;

import java.util.List;

import lemo.mods.banking.Banking;
import lemo.mods.banking.BankingTab;
import lemo.mods.banking.IDGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class CoinItem extends Item {

	public static final Item item = new CoinItem(IDGenerator.getID());

	public static void load() {

		GameRegistry.registerItem(CoinItem.item, Banking.modid + ":"
				+ CoinItem.item.getUnlocalizedName());
		LanguageRegistry.addName(CoinItem.item, "Coin");

	}

	// constants
	public static int numtypes = 3;

	public static final int GOLD = 0;
	public static final int SILVER = 1;
	public static final int BRONZE = 2;

	public CoinItem(int id) {
		super(id);
		setUnlocalizedName("coin");
		setHasSubtypes(true);
		setMaxDamage(0);

		setCreativeTab(BankingTab.instance());
	}

	@SideOnly(Side.CLIENT)
	private Icon icons[];

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List par3List) {
		par3List.add(new ItemStack(itemID, 1, GOLD));
		par3List.add(new ItemStack(itemID, 1, SILVER));
		par3List.add(new ItemStack(itemID, 1, BRONZE));
	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		String ret = "";
		switch (par1ItemStack.getItem().getDamage(par1ItemStack)) {
		case GOLD:
			ret = "Gold Coin";
			break;
		case SILVER:
			ret = "Silver Coin";
			break;
		case BRONZE:
			ret = "Bronze Coin";
			break;
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icons = new Icon[numtypes];

		icons[GOLD] = par1IconRegister.registerIcon(Banking.modid + ":"
				+ (this.getUnlocalizedName().substring(5)) + "Gold");

		icons[SILVER] = par1IconRegister.registerIcon(Banking.modid + ":"
				+ (this.getUnlocalizedName().substring(5)) + "Silver");

		icons[BRONZE] = par1IconRegister.registerIcon(Banking.modid + ":"
				+ (this.getUnlocalizedName().substring(5)) + "Bronze");
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icons[par1];
	}
}
