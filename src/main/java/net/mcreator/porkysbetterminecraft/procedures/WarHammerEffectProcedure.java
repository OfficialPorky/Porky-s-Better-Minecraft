package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Block;

import net.mcreator.porkysbetterminecraft.enchantment.WarHammerEnchantmentEnchantment;
import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@PorkysBetterminecraftModElements.ModElement.Tag
public class WarHammerEffectProcedure extends PorkysBetterminecraftModElements.ModElement {
	public WarHammerEffectProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 484);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WarHammerEffect!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure WarHammerEffect!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure WarHammerEffect!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure WarHammerEffect!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure WarHammerEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack pickaxe = ItemStack.EMPTY;
		double EnchtSize = 0;
		double i = 0;
		double j = 0;
		double k = 0;
		if (((EnchantmentHelper.getEnchantmentLevel(WarHammerEnchantmentEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))) > 0)) {
			if ((!(entity.isSneaking()))) {
				pickaxe = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
				EnchtSize = (double) (EnchantmentHelper.getEnchantmentLevel(WarHammerEnchantmentEnchantment.enchantment, (pickaxe)));
				i = (double) (x - (EnchtSize));
				for (int index0 = 0; index0 < (int) ((((EnchtSize) * 2) + 1)); index0++) {
					j = (double) (y - (EnchtSize));
					for (int index1 = 0; index1 < (int) ((((EnchtSize) * 2) + 1)); index1++) {
						k = (double) (z - (EnchtSize));
						for (int index2 = 0; index2 < (int) ((((EnchtSize) * 2) + 1)); index2++) {
							if ((((world.getBlockState(new BlockPos((int) (i), (int) (j), (int) (k))).isSolid())
									&& ((pickaxe).getItem().canHarvestBlock((world.getBlockState(new BlockPos((int) (i), (int) (j), (int) (k)))))))
									&& ((world.getBlockState(new BlockPos((int) (i), (int) (j), (int) (k))).getBlockHardness(world,
											new BlockPos((int) (i), (int) (j), (int) (k)))) >= 0))) {
								if ((new Object() {
									public boolean checkGamemode(Entity _ent) {
										if (_ent instanceof ServerPlayerEntity) {
											return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
										} else if (_ent instanceof PlayerEntity && _ent.world.isRemote) {
											NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
													.getPlayerInfo(((ClientPlayerEntity) _ent).getGameProfile().getId());
											return _npi != null && _npi.getGameType() == GameType.CREATIVE;
										}
										return false;
									}
								}.checkGamemode(entity))) {
									world.destroyBlock(new BlockPos((int) (i), (int) (j), (int) (k)), false);
								} else {
									Block.spawnDrops(world.getBlockState(new BlockPos((int) (i), (int) (j), (int) (k))), world.getWorld(),
											new BlockPos((int) (i), (int) (j), (int) (k)));
									world.destroyBlock(new BlockPos((int) (i), (int) (j), (int) (k)), false);
									{
										ItemStack _ist = (pickaxe);
										if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
											_ist.shrink(1);
											_ist.setDamage(0);
										}
									}
								}
							}
							k = (double) ((k) + 1);
						}
						j = (double) ((j) + 1);
					}
					i = (double) ((i) + 1);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
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
