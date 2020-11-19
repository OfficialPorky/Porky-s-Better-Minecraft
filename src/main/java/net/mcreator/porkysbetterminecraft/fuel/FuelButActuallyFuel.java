
package net.mcreator.porkysbetterminecraft.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.porkysbetterminecraft.item.FuelItem;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

@PorkysBetterminecraftModElements.ModElement.Tag
public class FuelButActuallyFuel extends PorkysBetterminecraftModElements.ModElement {
	public FuelButActuallyFuel(PorkysBetterminecraftModElements instance) {
		super(instance, 205);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(FuelItem.block, (int) (1)).getItem())
			event.setBurnTime(10000);
	}
}
