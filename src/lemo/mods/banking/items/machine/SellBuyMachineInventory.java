package lemo.mods.banking.items.machine;

import java.io.ObjectOutputStream.PutField;
import java.util.UUID;

import com.google.common.base.CaseFormat;

import lemo.mods.banking.items.base.PurseInventory;
import lemo.mods.banking.items.base.PurseItem;
import lemo.mods.banking.items.base.ValueDatabase;
import lemo.mods.banking.utils.NBTUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SellBuyMachineInventory implements IInventory {

	private String owner = "";
	private ItemStack slot_purse = null;
	private ItemStack slot_item = null;
	private ItemStack slot_put = null;
	private ItemStack slot_get = null;

	private class SellBuySlot {
		private final static int PURSE = 0;
		private final static int ITEM = 1;
		private final static int PUT = 2;
		private final static int GET = 3;
	}

	public SellBuyMachineInventory() {
	}

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		switch (slot) {
		case SellBuySlot.PURSE:
			return slot_purse;
		case SellBuySlot.ITEM:
			return slot_item;
		case SellBuySlot.PUT:
			return slot_item;
		case SellBuySlot.GET:
			return slot_item;
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		switch (slot) {
		case SellBuySlot.PURSE:
			ItemStack t = slot_purse;
			slot_purse = null;
			return t;

		case SellBuySlot.GET:
			int pursebalance = slot_purse.getTagCompound().getInteger("value");
			int a = amount * ValueDatabase.getValue(slot_item.getItem());
			if (pursebalance >= a) {
				slot_purse.getTagCompound().setInteger("value",
						pursebalance - a);
				return new ItemStack(slot_item.getItem(), amount);
			}
		default:
			break;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		if (itemstack != null && itemstack.getItem() != null) {
			switch (slot) {
			case SellBuySlot.PURSE:
				slot_purse = itemstack;
				break;
			case SellBuySlot.ITEM:
				slot_item = itemstack;
				slot_get = itemstack;
				break;
			case SellBuySlot.PUT:
				if (slot_item.getItem() == itemstack.getItem()) {
					int pursebalance = slot_purse.getTagCompound().getInteger(
							"value");
					pursebalance += itemstack.stackSize
							* ValueDatabase.getValue(itemstack.getItem());
					slot_purse.getTagCompound().setInteger("value",
							pursebalance);
				}
				slot_put = itemstack;
				break;
			}
		}
	}

	@Override
	public String getInvName() {
		return "lemo.mods.banking.sellbuymachine";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public void onInventoryChanged() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		if (owner == "") {
			owner = entityplayer.getUniqueID().toString();
		}
		if (entityplayer.getUniqueID() == UUID.fromString(owner)) {
			return true;
		}
		return false;
	}

	@Override
	public void openChest() {

	}

	@Override
	public void closeChest() {

	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		switch (slot) {
		case SellBuySlot.PURSE:
			if (slot_purse == null && itemstack.getItem() instanceof PurseItem) {
				return true;
			}
			break;
		case SellBuySlot.ITEM:
			return false;
		case SellBuySlot.PUT:
			if (itemstack.getItem() == slot_item.getItem())
				return true;
			break;
		case SellBuySlot.GET:
			return false;
		}
		return false;
	}

	public void readFromNBT(NBTTagCompound par1tagCompound) {
		owner = par1tagCompound.getString("owner");
	}

	public void writeToNBT(NBTTagCompound par1tagCompound) {
		par1tagCompound.setString("owner", owner);
	}

}
