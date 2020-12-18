package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.porkysbetterminecraft.enchantment.HydrohealerEnchantment;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;
import java.util.HashMap;

@PorkysBetterminecraftModElements.ModElement.Tag
public class HydrohealerEffectProcedure extends PorkysBetterminecraftModElements.ModElement {
	public HydrohealerEffectProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 543);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure HydrohealerEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double EnchantmentLevel = 0;
		EnchantmentLevel = (double) (EnchantmentHelper.getEnchantmentLevel(HydrohealerEnchantment.enchantment,
				((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 1) : ItemStack.EMPTY)));
		if (((EnchantmentLevel) > 0)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).setHealth(
						(float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) + (3 * (EnchantmentLevel))));
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) > ((entity instanceof LivingEntity)
					? ((LivingEntity) entity).getMaxHealth()
					: -1))) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).setHealth((float) ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerFishItem(ItemFishedEvent event) {
		PlayerEntity entity = event.getPlayer();
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		World world = entity.world;
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
