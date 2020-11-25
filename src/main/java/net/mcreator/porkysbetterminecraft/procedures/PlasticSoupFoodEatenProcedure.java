package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;

@PorkysBetterminecraftModElements.ModElement.Tag
public class PlasticSoupFoodEatenProcedure extends PorkysBetterminecraftModElements.ModElement {
	public PlasticSoupFoodEatenProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 396);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure PlasticSoupFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.POISON, (int) 900, (int) 1));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HUNGER, (int) 1800, (int) 1));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 450, (int) 0));
	}
}
