package lemo.mods.banking.items.base;

import java.util.List;

import lemo.mods.banking.Banking;
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

	public static int numtypes = 3;

	@SideOnly(Side.CLIENT)
	private Icon icons[] = new Icon[numtypes];

	public static final int GOLD = 0;
	public static final int SILVER = 1;
	public static final int BRONZE = 2;

	public CoinItem(int id) {
		super(id);
		setMaxStackSize(99);
		setUnlocalizedName("coin");
		setCreativeTab(CreativeTabs.tabMisc);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

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
			ret = "Gold";
			break;
		case SILVER:
			ret = "Silver";
			break;
		case BRONZE:
			ret = "Bronze";
			break;
		}
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
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
