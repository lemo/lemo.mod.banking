package lemo.mods.banking.items.base;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import lemo.mods.banking.Banking;
import lemo.mods.banking.BankingTab;
import lemo.mods.banking.IDGenerator;
import lemo.mods.banking.utils.NBTUtils;

public class PurseItem extends Item {

	public static final Item item = new PurseItem(IDGenerator.getID());

	public static void load() {

		GameRegistry.registerItem(PurseItem.item, Banking.modid + ":"
				+ PurseItem.item.getUnlocalizedName());
		LanguageRegistry.addName(PurseItem.item, "Purse");
		GameRegistry.addShapedRecipe(new ItemStack(PurseItem.item), "LLL",
				"SDS", "LLL", 'L', Item.leather, 'S', Item.silk, 'D',
				Item.diamond);
	}

	// constants
	public static int DIVISOR = 100;

	public PurseItem(int id) {
		super(id);
		setMaxStackSize(1);
		setUnlocalizedName("purse");

		setCreativeTab(BankingTab.instance());
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
			EntityPlayer player) {
		if (!world.isRemote) {
			player.openGui(Banking.instance, Banking.PurseGuiIndex, world,
					(int) player.posX, (int) player.posY, (int) player.posZ);
		}
		return itemstack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(Banking.modid + ":"
				+ this.getUnlocalizedName().substring(5));
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);

		NBTUtils.checkForNBT(par1ItemStack);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

		NBTUtils.checkForNBT(par1ItemStack);

		int value = par1ItemStack.stackTagCompound.getInteger("value");
		int gold = value / (DIVISOR * DIVISOR);
		int silver = value / DIVISOR % DIVISOR;
		int bronze = value % DIVISOR;

		par3List.add(EnumChatFormatting.YELLOW + "Gold:   "
				+ String.valueOf(gold));
		par3List.add(EnumChatFormatting.GRAY + "Silver: "
				+ String.valueOf(silver));
		par3List.add(EnumChatFormatting.RED + "Bronze: "
				+ String.valueOf(bronze));
	}

}
