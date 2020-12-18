package net.mcreator.porkysbetterminecraft.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.projectile.EvokerFangsEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import net.mcreator.porkysbetterminecraft.PorkysBetterminecraftModElements;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@PorkysBetterminecraftModElements.ModElement.Tag
public class FasleonyxMageEntityIsHurtProcedure extends PorkysBetterminecraftModElements.ModElement {
	public FasleonyxMageEntityIsHurtProcedure(PorkysBetterminecraftModElements instance) {
		super(instance, 623);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FasleonyxMageEntityIsHurt!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure FasleonyxMageEntityIsHurt!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure FasleonyxMageEntityIsHurt!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure FasleonyxMageEntityIsHurt!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure FasleonyxMageEntityIsHurt!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double PlusMinus = 0;
		if ((Math.random() < (3 / y))) {
			{
				List<Entity> _entfound = world
						.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (10 / 2d), y - (10 / 2d), z - (10 / 2d), x + (10 / 2d), y + (10 / 2d), z + (10 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, (int) (20 * (y / 10)), (int) 1));
				}
			}
		} else {
			if ((Math.random() < 0.3)) {
				for (int index0 = 0; index0 < (int) (8); index0++) {
					if ((Math.random() < 0.5)) {
						PlusMinus = (double) 10;
					} else {
						PlusMinus = (double) (-10);
					}
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new EvokerFangsEntity(EntityType.EVOKER_FANGS, world.getWorld());
						entityToSpawn.setLocationAndAngles((x + (Math.random() * (PlusMinus))), y, (z + (Math.random() * (PlusMinus))), (float) 0,
								(float) 0);
						entityToSpawn.setRenderYawOffset((float) 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
				}
			} else {
				if ((Math.random() < 0.2)) {
					for (int index1 = 0; index1 < (int) (Math.ceil((Math.random() * 5))); index1++) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new EndermiteEntity(EntityType.ENDERMITE, world.getWorld());
							entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					}
				}
			}
		}
	}
}
