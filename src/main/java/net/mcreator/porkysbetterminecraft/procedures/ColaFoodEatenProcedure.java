package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;

@PorkysBetterminecraftModElements.ModElement.Tag
public class ColaFoodEatenProcedure extends PorkysBetterminecraftModElements.ModElement {
	public ColaFoodEatenProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 312);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ColaFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 30, (int) 0, (false), (false)));
	}
}
