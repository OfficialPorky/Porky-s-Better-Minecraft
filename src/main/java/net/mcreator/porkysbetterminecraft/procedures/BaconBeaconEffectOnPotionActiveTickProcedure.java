package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;

@PorkysBetterminecraftModElements.ModElement.Tag
public class BaconBeaconEffectOnPotionActiveTickProcedure extends PorkysBetterminecraftModElements.ModElement {
	public BaconBeaconEffectOnPotionActiveTickProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 478);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BaconBeaconEffectOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 60, (int) 0, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 60, (int) 0, (false), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HASTE, (int) 60, (int) 0, (false), (false)));
	}
}
