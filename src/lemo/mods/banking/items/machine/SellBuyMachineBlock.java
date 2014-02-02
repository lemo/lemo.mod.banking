package lemo.mods.banking.items.machine;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import lemo.mods.banking.Banking;
import lemo.mods.banking.BankingTab;
import lemo.mods.banking.IDGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SellBuyMachineBlock extends BlockContainer {

	public static Block block = new SellBuyMachineBlock(IDGenerator.getID(),
			Material.rock);

	public static void load() {
		GameRegistry.registerBlock(SellBuyMachineBlock.block, Banking.modid
				+ ":" + SellBuyMachineBlock.block.getUnlocalizedName());
		GameRegistry.registerTileEntity(SellBuyMachineTileEntity.class,
				Banking.modid + ":" + SellBuyMachineTileEntity.id);

		LanguageRegistry.addName(SellBuyMachineBlock.block,
				"Sell and Buy Machine");
	}

	public SellBuyMachineBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setUnlocalizedName("sellbuymachine");
		setHardness(50F);

		setCreativeTab(BankingTab.instance());
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (!par1World.isRemote) {
			par5EntityPlayer
					.openGui(Banking.instance, Banking.SellBuyMachineGuiIndex,
							par1World, par2, par3, par4);
			return false;
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new SellBuyMachineTileEntity();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

}
