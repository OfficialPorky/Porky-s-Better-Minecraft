package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.porkysbetterminecraft.enchantment.DeadeyeEnchantment;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;
import java.util.HashMap;

@PorkysBetterminecraftModElements.ModElement.Tag
public class DeadeyeEffectProcedure extends PorkysBetterminecraftModElements.ModElement {
	public DeadeyeEffectProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 539);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure DeadeyeEffect!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double EnchantmentLevel = 0;
		EnchantmentLevel = (double) (EnchantmentHelper.getEnchantmentLevel(DeadeyeEnchantment.enchantment,
				((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)));
		if (((EnchantmentLevel) > 0)) {
			if (sourceentity instanceof LivingEntity)
				((LivingEntity) sourceentity)
						.addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) (20 * (EnchantmentLevel)), (int) ((EnchantmentLevel) - 1)));
		}
	}

	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
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
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
