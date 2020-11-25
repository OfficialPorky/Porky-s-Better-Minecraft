
package net.mcreator.porkysbetterminecraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

@PorkysBetterminecraftModElements.ModElement.Tag
public class PiggiumSwordItem extends PorkysBetterminecraftModElements.ModElement {
	@ObjectHolder("porkys_betterminecraft:piggium_sword")
	public static final Item block = null;
	public PiggiumSwordItem(PorkysBetterminecraftModElements instance) {
		super(instance, 344);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 1444;
			}

			public float getEfficiency() {
				return 13f;
			}

			public float getAttackDamage() {
				return 19f;
			}

			public int getHarvestLevel() {
				return 7;
			}

			public int getEnchantability() {
				return 49;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(PiggiumItem.block, (int) (1)));
			}
		}, 3, -3f, new Item.Properties().group(ItemGroup.COMBAT)) {
		}.setRegistryName("piggium_sword"));
	}
}
