package lemo.mods.banking.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtils {
	public static void checkForNBT(ItemStack par1) {
		if (!par1.hasTagCompound()) {
			par1.setTagCompound(new NBTTagCompound());
		}
	}
}
