package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.porkysbetterminecraft.item.AlienGemItem;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;
import java.util.Iterator;

@PorkysBetterminecraftModElements.ModElement.Tag
public class ObtainAlienMaterialCompletionProcedure extends PorkysBetterminecraftModElements.ModElement {
	public ObtainAlienMaterialCompletionProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 278);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ObtainAlienMaterialCompletion!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(AlienGemItem.block, (int) (1)))
				: false)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("porkys_betterminecraft:obtain_alien_material"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		}
	}
}
