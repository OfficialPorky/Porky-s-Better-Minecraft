
package net.mcreator.porkysbetterminecraft.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

@PorkysBetterminecraftModElements.ModElement.Tag
public class XostriumPickaxeItem extends PorkysBetterminecraftModElements.ModElement {
	@ObjectHolder("porkys_betterminecraft:xostrium_pickaxe")
	public static final Item block = null;
	public XostriumPickaxeItem(PorkysBetterminecraftModElements instance) {
		super(instance, 432);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 2053;
			}

			public float getEfficiency() {
				return 15f;
			}

			public float getAttackDamage() {
				return 7f;
			}

			public int getHarvestLevel() {
				return 9;
			}

			public int getEnchantability() {
				return 63;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(XostriumItem.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("xostrium_pickaxe"));
	}
}
