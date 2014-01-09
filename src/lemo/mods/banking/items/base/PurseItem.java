package lemo.mods.banking.items.base;

import java.util.List;

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

public class PurseItem extends Item {

	public static int divisor = 100;

	public PurseItem(int id) {
		super(id);
		setMaxStackSize(1);
		setUnlocalizedName("purse");
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world,
			EntityPlayer player) {
		if (!world.isRemote) {
			player.openGui(Banking.instance, Banking.ItemInventoryGuiIndex,
					world, (int) player.posX, (int) player.posY,
					(int) player.posZ);
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

		checkForNBT(par1ItemStack);
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

		checkForNBT(par1ItemStack);

		int value = par1ItemStack.stackTagCompound.getInteger("value");
		int gold = value / (divisor * divisor);
		int silver = value / divisor % divisor;
		int bronze = value % divisor;

		par3List.add(EnumChatFormatting.YELLOW + "Gold:   "
				+ String.valueOf(gold));
		par3List.add(EnumChatFormatting.GRAY + "Silver: "
				+ String.valueOf(silver));
		par3List.add(EnumChatFormatting.RED + "Bronze: "
				+ String.valueOf(bronze));
	}

	private void checkForNBT(ItemStack par1) {
		if (!par1.hasTagCompound()) {
			par1.setTagCompound(new NBTTagCompound());
			par1.getTagCompound().setInteger("value", 0);
		}
	}
}
