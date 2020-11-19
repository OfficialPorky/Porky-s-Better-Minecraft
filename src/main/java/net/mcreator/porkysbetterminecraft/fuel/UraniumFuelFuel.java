
package net.mcreator.porkysbetterminecraft.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.porkysbetterminecraft.item.UraniumGemItem;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

@PorkysBetterminecraftModElements.ModElement.Tag
public class UraniumFuelFuel extends PorkysBetterminecraftModElements.ModElement {
	public UraniumFuelFuel(PorkysBetterminecraftModElements instance) {
		super(instance, 64);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(UraniumGemItem.block, (int) (1)).getItem())
			event.setBurnTime(1600000);
	}
}
