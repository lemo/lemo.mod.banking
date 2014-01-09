package lemo.mods.banking;

import com.jcraft.jorbis.Block;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import lemo.mods.banking.items.base.CoinItem;
import lemo.mods.banking.items.base.PurseItem;
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

	/** This is the starting index for all of our mod's item IDs */
	private static int modItemIndex = 7000;

	/** Set our custom inventory Gui index to the next available Gui index */
	public static final int ItemInventoryGuiIndex = modGuiIndex++;

	public static final PurseItem item_purse = new PurseItem(modItemIndex++);
	public static final CoinItem item_coin = new CoinItem(modItemIndex++);

	public static final ItemStack item_stack_purse = new ItemStack(item_purse);

	public static CreativeTabs bankingtab = new CreativeTabs("tabBanking") {
		public ItemStack getIconItemStack() {
			return new ItemStack(item_purse, 1, 0);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		NetworkRegistry.instance().registerGuiHandler(this, new CommonProxy());

		LanguageRegistry.addName(item_purse, "Purse");
		LanguageRegistry.addName(item_coin, "Coin");
		
		GameRegistry.registerItem(item_purse, "purse");
		GameRegistry.registerItem(item_coin, "coin");
		
		GameRegistry.addShapedRecipe(item_stack_purse, "LLL", "SDS", "LLL",
				'L', Item.leather, 'S', Item.silk, 'D', Item.diamond);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}