package lemo.mods.banking.items.base;

import lemo.mods.banking.Banking;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class PurseInventory implements IInventory {
	private int value;
	private final ItemStack stack;

	public PurseInventory(ItemStack itemstack) {
		checkForNBT(itemstack);
		value = itemstack.getTagCompound().getInteger("value");
		stack = itemstack;
	}

	@Override
	public int getSizeInventory() {
		return CoinItem.numtypes;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		ItemStack stack = new ItemStack(CoinItem.item);
		stack.setItemDamage(slot);
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setBoolean("virtual", true);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		int raise = 0;
		if (stack != null && stack.getItem() != null
				&& stack.getItem() instanceof CoinItem) {

			switch (stack.getItemDamage()) {
			case CoinItem.GOLD:
				raise = stack.stackSize * PurseItem.divisor * PurseItem.divisor;
				break;
			case CoinItem.SILVER:
				raise = stack.stackSize * PurseItem.divisor;
				break;
			case CoinItem.BRONZE:
				raise = stack.stackSize;
				break;
			}
			boolean isVirtual = false;
			if (stack.hasTagCompound()) {
				isVirtual = stack.getTagCompound().getBoolean("virtual");
			}
			if (!isVirtual)
				value += raise;
			this.onInventoryChanged();
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		int deduct = 0;
		ItemStack ret = new ItemStack(CoinItem.item);

		switch (slot) {
		case CoinItem.GOLD:
			ret.setItemDamage(CoinItem.GOLD);
			deduct = (PurseItem.divisor * PurseItem.divisor * amount);
			break;
		case CoinItem.SILVER:
			ret.setItemDamage(CoinItem.SILVER);
			deduct = (PurseItem.divisor * amount);
			break;
		case CoinItem.BRONZE:
			ret.setItemDamage(CoinItem.BRONZE);
			deduct = amount;
			break;
		}
		if (value - deduct < 0) {
			return null;
		}
		value -= deduct;
		return ret;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public int getInventoryStackLimit() {
		return 100;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	@Override
	public String getInvName() {
		return "lemo.mods.banking.purse";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack itemstack) {
		return (itemstack.getItem() instanceof CoinItem && par1 == itemstack
				.getItemDamage());
	}

	@Override
	public void onInventoryChanged() {
		stack.getTagCompound().setInteger("value", value);
	}

	private void checkForNBT(ItemStack par1) {
		if (!par1.hasTagCompound()) {
			par1.setTagCompound(new NBTTagCompound());
		}
	}
}
