package lemo.mods.banking;

import com.jcraft.jorbis.Block;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import lemo.mods.banking.items.base.CoinItem;
import lemo.mods.banking.items.base.PurseItem;
import lemo.mods.banking.items.machine.SellBuyMachineBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "lemomodbanking", name = "Banking Mod", version = "0.0.0")
@NetworkMod(clientSideRequired = true)
public class Banking {

	public static final String modid = "lemomodbanking";

	@Instance(value = modid)
	public static Banking instance;

	@SidedProxy(clientSide = "lemo.mods.banking.client.ClientProxy", serverSide = "lemo.mods.banking.CommonProxy")
	public static CommonProxy proxy;

	/** This is used to keep track of GUIs that we make */
	private static int modGuiIndex = 0;

	/** Set our custom inventory Gui index to the next available Gui index */
	public static final int ItemInventoryGuiIndex = modGuiIndex++;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		NetworkRegistry.instance().registerGuiHandler(this, new CommonProxy());

		CoinItem.load();
		PurseItem.load();
		SellBuyMachineBlock.load();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}