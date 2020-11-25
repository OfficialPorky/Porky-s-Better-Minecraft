package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.porkysbetterminecraft.potion.WarmPotion;
import net.mcreator.porkysbetterminecraft.potion.HotPotion;
import net.mcreator.porkysbetterminecraft.potion.FreezePotion;
import net.mcreator.porkysbetterminecraft.potion.ColdPotion;
import net.mcreator.porkysbetterminecraft.item.ClimateControllerItem;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;
import java.util.HashMap;

@PorkysBetterminecraftModElements.ModElement.Tag
public class ClimateControllerInventoryTickProcedure extends PorkysBetterminecraftModElements.ModElement {
	public ClimateControllerInventoryTickProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 418);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ClimateControllerInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ClimateControllerItem.block, (int) (1)))
				: false)) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(ColdPotion.potion);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(FreezePotion.potion);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(WarmPotion.potion);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(HotPotion.potion);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
