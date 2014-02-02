package lemo.mods.banking.items.machine;

import lemo.mods.banking.client.SellBuyMachineGui;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class SellBuyMachineTileEntity extends TileEntity {

	public final static String id = "sellbuymachinetileentity";

	public SellBuyMachineTileEntity() {

	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		NBTTagCompound tag = par1nbtTagCompound.getCompoundTag("purse");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		par1nbtTagCompound.setCompoundTag("purse", par1nbtTagCompound);
	}

//	@Override
//	public Packet getDescriptionPacket() {
//		NBTTagCompound tagCompound = new NBTTagCompound();
//		writeToNBT(tagCompound);
//		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1,
//				tagCompound);
//	}
//
//	@Override
//	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
//		readFromNBT(pkt.data);
//		GuiScreen gui = FMLClientHandler.instance().getClient().currentScreen;
//		if (gui != null && gui instanceof SellBuyMachineGui) {
//	//		((SellBuyMachineGui) gui).updateFromTileEntityData();
//		}
//	}

}
