package lemo.mods.banking;

import lemo.mods.banking.client.GuiPurse;
import lemo.mods.banking.items.base.PurseContainer;
import lemo.mods.banking.items.base.PurseInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	// Client stuff
	public void registerRenderers() {
		// Nothing here as the server doesn't render graphics or entities!
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// Hooray, no 'magic' numbers - we know exactly which Gui this refers to
		if (ID == Banking.ItemInventoryGuiIndex) {
			// Use the player's held item to create the inventory
			return new PurseContainer(player, player.inventory,
					new PurseInventory(player.getHeldItem()));
		}
		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == Banking.ItemInventoryGuiIndex) {
			// We have to cast the new container as our custom class
			// and pass in currently held item for the inventory
			return new GuiPurse((PurseContainer) new PurseContainer(player,
					player.inventory, new PurseInventory(player.getHeldItem())));
		}
		return null;
	}
}