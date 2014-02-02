package lemo.mods.banking;

import lemo.mods.banking.client.PurseGui;
import lemo.mods.banking.client.SellBuyMachineGui;
import lemo.mods.banking.items.base.PurseContainer;
import lemo.mods.banking.items.base.PurseInventory;
import lemo.mods.banking.items.machine.SellBuyMachineContainer;
import lemo.mods.banking.items.machine.SellBuyMachineInventory;
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
		if (ID == Banking.PurseGuiIndex) {
			// Use the player's held item to create the inventory
			return new PurseContainer(player, player.inventory,
					new PurseInventory(player.getHeldItem()));
		} else if (ID == Banking.SellBuyMachineGuiIndex) {
			return new SellBuyMachineContainer(player, player.inventory,
					new SellBuyMachineInventory());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == Banking.PurseGuiIndex) {
			// We have to cast the new container as our custom class
			// and pass in currently held item for the inventory
			return new PurseGui(new PurseContainer(player, player.inventory,
					new PurseInventory(player.getHeldItem())));
		} else if (ID == Banking.SellBuyMachineGuiIndex) {
			return new SellBuyMachineGui(new SellBuyMachineContainer(player,
					player.inventory, new SellBuyMachineInventory()));
		}
		return null;
	}
}