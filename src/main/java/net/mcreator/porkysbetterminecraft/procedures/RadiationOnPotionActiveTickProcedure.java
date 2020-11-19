package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.porkysbetterminecraft.potion.RadiationPotion;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;
import java.util.Collection;

@PorkysBetterminecraftModElements.ModElement.Tag
public class RadiationOnPotionActiveTickProcedure extends PorkysBetterminecraftModElements.ModElement {
	public RadiationOnPotionActiveTickProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 219);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RadiationOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) ((new Object() {
				int check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == RadiationPotion.potion)
								return effect.getDuration();
						}
					}
					return 0;
				}
			}.check(entity)) * 0.5), (int) (new Object() {
				int check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == RadiationPotion.potion)
								return effect.getAmplifier();
						}
					}
					return 0;
				}
			}.check(entity)), (true), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) ((new Object() {
				int check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == RadiationPotion.potion)
								return effect.getDuration();
						}
					}
					return 0;
				}
			}.check(entity)) * 0.75), (int) (new Object() {
				int check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == RadiationPotion.potion)
								return effect.getAmplifier();
						}
					}
					return 0;
				}
			}.check(entity)), (true), (false)));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.GLOWING, (int) (new Object() {
				int check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == RadiationPotion.potion)
								return effect.getDuration();
						}
					}
					return 0;
				}
			}.check(entity)), (int) (new Object() {
				int check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == RadiationPotion.potion)
								return effect.getAmplifier();
						}
					}
					return 0;
				}
			}.check(entity)), (true), (false)));
	}
}
