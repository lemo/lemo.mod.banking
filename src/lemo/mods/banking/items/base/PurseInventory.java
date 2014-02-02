package lemo.mods.banking.items.base;

import lemo.mods.banking.Banking;
import lemo.mods.banking.utils.NBTUtils;
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

	private static final int PUT_SLOT = 3;

	public PurseInventory(ItemStack itemstack) {
		NBTUtils.checkForNBT(itemstack);
		value = itemstack.getTagCompound().getInteger("value");
		stack = itemstack;
	}

	@Override
	public int getSizeInventory() {
		return CoinItem.numtypes + 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		if (slot < CoinItem.numtypes) {
			ItemStack stack = new ItemStack(CoinItem.item);
			stack.setItemDamage(slot);
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setBoolean("virtual", true);
			return stack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (slot == PUT_SLOT) {
			int raise = 0;
			if (stack != null && stack.getItem() != null
					&& stack.getItem() instanceof CoinItem) {
				switch (stack.getItemDamage()) {
				case CoinItem.GOLD:
					raise = stack.stackSize * PurseItem.DIVISOR
							* PurseItem.DIVISOR;
					break;
				case CoinItem.SILVER:
					raise = stack.stackSize * PurseItem.DIVISOR;
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
					raiseValue(raise);
				this.onInventoryChanged();
			}
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		int deduct = 0;
		ItemStack ret = new ItemStack(CoinItem.item);

		switch (slot) {
		case CoinItem.GOLD:
			ret.setItemDamage(CoinItem.GOLD);
			deduct = (PurseItem.DIVISOR * PurseItem.DIVISOR * amount);
			break;
		case CoinItem.SILVER:
			ret.setItemDamage(CoinItem.SILVER);
			deduct = (PurseItem.DIVISOR * amount);
			break;
		case CoinItem.BRONZE:
			ret.setItemDamage(CoinItem.BRONZE);
			deduct = amount;
			break;
		}
		if (!deductValue(deduct)) {
			return null;
		}
		this.onInventoryChanged();
		return ret;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		if (slot != PUT_SLOT) {
			return false;
		} else {
			return itemstack.getItem() instanceof CoinItem;
		}

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
	public void onInventoryChanged() {
		stack.getTagCompound().setInteger("value", value);
	}

	/**
	 * raise the amount of money in the purse
	 * 
	 * @param amount
	 */
	void raiseValue(int amount) {
		value += amount;
	}

	/**
	 * deduct the amount of money of the purse
	 * 
	 * @param amount
	 * @return true if deduction was possible otherwise false
	 */
	boolean deductValue(int amount) {
		if (amount > value) {
			return false;
		}
		value -= amount;
		return true;
	}
}
