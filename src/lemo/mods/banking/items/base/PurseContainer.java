package lemo.mods.banking.items.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class PurseContainer extends Container {
	/** The Item Inventory for this Container */
	public final PurseInventory inventory;

	/**
	 * Using these will make transferStackInSlot easier to understand and
	 * implement INV_START is the index of the first slot in the Player's
	 * Inventory, so our PurseItem's number of slots (e.g. 5 slots is array
	 * indices 0-4, so start at 5) Notice how we don't have to remember how many
	 * slots we made? We can just use PurseItem.INV_SIZE and if we ever change
	 * it, the Container updates automatically.
	 */
	// private static final int INV_START = CoinItem.numtypes,
	// INV_END = INV_START + 26, HOTBAR_START = INV_END + 1,
	// HOTBAR_END = HOTBAR_START + 8;

	// If you're planning to add armor slots, put those first like this:
	// ARMOR_START = PurseItem.INV_SIZE, ARMOR_END = ARMOR_START+3,
	// INV_START = ARMOR_END+1, and then carry on like above.

	public PurseContainer(EntityPlayer par1Player,
			InventoryPlayer inventoryPlayer, PurseInventory purseInventory) {
		this.inventory = purseInventory;

		int i = 0;

		// ITEM INVENTORY - you'll need to adjust the slot locations to match
		// your texture file
		// I have them set vertically in columns of 4 to the right of the player
		// model
		// for (i = 0; i < CoinItem.numtypes; ++i) {
		// You can make a custom Slot if you need different behavior,
		// such as only certain item types can be put into this slot
		// We made a custom slot to prevent our inventory-storing item
		// from being stored within itself, but if you want to allow that
		// and
		// you followed my advice at the end of the above step, then you
		// could get away with using the vanilla Slot class
		// this.addSlotToContainer(new PurseSlot(this.inventory, i,
		// 80 + (18 * (int) (i / 4)), 8 + (18 * (i % 4))));
		// }
		this.addSlotToContainer(new VoidSlot(this.inventory, i++, 80, 8));
		this.addSlotToContainer(new VoidSlot(this.inventory, i++, 80 + 18, 8));
		this.addSlotToContainer(new VoidSlot(this.inventory, i++, 80 + 18 * 2,
				8));
		this.addSlotToContainer(new Slot(this.inventory, i++, 80 + 18 * 3, 8));

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

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return super.transferStackInSlot(par1EntityPlayer, par2);
	}

}
