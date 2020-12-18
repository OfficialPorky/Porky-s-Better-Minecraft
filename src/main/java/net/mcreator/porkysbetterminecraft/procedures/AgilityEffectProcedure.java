package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.porkysbetterminecraft.enchantment.AgilityEnchantment;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

@PorkysBetterminecraftModElements.ModElement.Tag
public class AgilityEffectProcedure extends PorkysBetterminecraftModElements.ModElement {
	public AgilityEffectProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 524);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure AgilityEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double EnchantmentLevel = 0;
		EnchantmentLevel = (double) (EnchantmentHelper.getEnchantmentLevel(AgilityEnchantment.enchantment,
				((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 0) : ItemStack.EMPTY)));
		if (((EnchantmentLevel) == 0)) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).removePotionEffect(Effects.SPEED);
			}
		} else if ((((EnchantmentLevel) > 0) && (!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == Effects.SPEED)
							return true;
					}
				}
				return false;
			}
		}.check(entity))))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity)
						.addPotionEffect(new EffectInstance(Effects.SPEED, (int) 144000, (int) ((EnchantmentLevel) - 1), (false), (false)));
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
