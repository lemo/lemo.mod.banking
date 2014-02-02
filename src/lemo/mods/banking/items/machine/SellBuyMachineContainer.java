package lemo.mods.banking.items.machine;

import lemo.mods.banking.items.base.CoinItem;
import lemo.mods.banking.items.base.PurseInventory;
import lemo.mods.banking.items.base.VoidSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SellBuyMachineContainer extends Container {
	public SellBuyMachineInventory inventory;

	public SellBuyMachineContainer(EntityPlayer par1Player,
			InventoryPlayer inventoryPlayer,
			SellBuyMachineInventory sellbuymachineinventory) {
		this.inventory = sellbuymachineinventory;

		int i = 0;

		this.addSlotToContainer(new PurseSlot(this.inventory, i++, 80 + 18 * i,
				8 * i));
		this.addSlotToContainer(new Slot(this.inventory, i++, 80 + 18 * i,
				8 * i));
		this.addSlotToContainer(new Slot(this.inventory, i++, 80 + 18 * i,
				8 * i));
		this.addSlotToContainer(new VoidSlot(this.inventory, i++, 80 + 18 * i,
				8 * i));

		// PLAYER INVENTORY - uses default locations for standard inventory
		// texture file
		for (i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventoryPlayer,
						j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		// PLAYER ACTION BAR - uses default locations for standard action bar
		// texture file
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18,
					142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return super.transferStackInSlot(par1EntityPlayer, par2);
	}

}
