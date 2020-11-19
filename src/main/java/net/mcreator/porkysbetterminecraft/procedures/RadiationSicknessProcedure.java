package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.porkysbetterminecraft.potion.RadiationPotion;
import net.mcreator.porkysbetterminecraft.item.UraniumGemItem;
import net.mcreator.porkysbetterminecraft.block.UraniumOreBlockBlock;
import net.mcreator.porkysbetterminecraft.block.UraniumOreBlock;
import net.mcreator.porkysbetterminecraft.block.NuclearWasteBlock;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;
import java.util.HashMap;

@PorkysBetterminecraftModElements.ModElement.Tag
public class RadiationSicknessProcedure extends PorkysBetterminecraftModElements.ModElement {
	public RadiationSicknessProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 217);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RadiationSickness!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(UraniumGemItem.block, (int) (1)))
				: false)
				|| ((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(UraniumOreBlock.block, (int) (1)))
						: false))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(RadiationPotion.potion, (int) 200, (int) 0));
		}
		if ((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(NuclearWasteBlock.block, (int) (1)))
				: false)
				|| ((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(UraniumOreBlockBlock.block, (int) (1)))
						: false))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(RadiationPotion.potion, (int) 1200, (int) 1));
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
