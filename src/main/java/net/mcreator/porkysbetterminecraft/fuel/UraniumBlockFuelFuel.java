
package net.mcreator.porkysbetterminecraft.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.porkysbetterminecraft.block.UraniumOreBlockBlock;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

@PorkysBetterminecraftModElements.ModElement.Tag
public class UraniumBlockFuelFuel extends PorkysBetterminecraftModElements.ModElement {
	public UraniumBlockFuelFuel(PorkysBetterminecraftModElements instance) {
		super(instance, 97);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(UraniumOreBlockBlock.block, (int) (1)).getItem())
			event.setBurnTime(16000000);
	}
}
