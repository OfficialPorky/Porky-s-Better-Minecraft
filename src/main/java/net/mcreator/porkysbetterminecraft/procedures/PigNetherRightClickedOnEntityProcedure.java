package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.porkysbetterminecraft.enchantment.FlakJacketEnchantment;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Map;

@PorkysBetterminecraftModElements.ModElement.Tag
public class PigNetherRightClickedOnEntityProcedure extends PorkysBetterminecraftModElements.ModElement {
	public PigNetherRightClickedOnEntityProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 475);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure PigNetherRightClickedOnEntity!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure PigNetherRightClickedOnEntity!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		ItemStack pickaxe = ItemStack.EMPTY;
		double EnchtSize = 0;
		double i = 0;
		double j = 0;
		double k = 0;
		double generator_distance = 0;
		if ((((EnchantmentHelper.getEnchantmentLevel(FlakJacketEnchantment.enchantment,
				((sourceentity instanceof PlayerEntity)
						? ((PlayerEntity) sourceentity).inventory.armorInventory.get((int) 2)
						: ItemStack.EMPTY))) == 0)
				&& (((sourceentity instanceof PlayerEntity)
						? ((PlayerEntity) sourceentity).inventory.hasItemStack(new ItemStack(Items.GOLDEN_CARROT, (int) (1)))
						: false)
						|| ((sourceentity instanceof PlayerEntity)
								? ((PlayerEntity) sourceentity).inventory.hasItemStack(new ItemStack(Items.GOLD_INGOT, (int) (1)))
								: false)))) {
			(((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).inventory.armorInventory.get((int) 2) : ItemStack.EMPTY))
					.addEnchantment(FlakJacketEnchantment.enchantment, (int) 1);
			if (((sourceentity instanceof PlayerEntity)
					? ((PlayerEntity) sourceentity).inventory.hasItemStack(new ItemStack(Items.GOLDEN_CARROT, (int) (1)))
					: false)) {
				if (sourceentity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.GOLDEN_CARROT, (int) (1));
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
				}
			} else if (((sourceentity instanceof PlayerEntity)
					? ((PlayerEntity) sourceentity).inventory.hasItemStack(new ItemStack(Items.GOLD_INGOT, (int) (1)))
					: false)) {
				if (sourceentity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.GOLD_INGOT, (int) (1));
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
				}
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("You need a gold ingot or a golden carrot!"), (true));
			}
		}
	}
}
