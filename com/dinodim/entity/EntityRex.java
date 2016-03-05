package com.dinodim.entity;

import com.dinodim.dimension.DDDimensionRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityRex extends DDAgeableMob
{
	public EntityRex(World worldIn)
	{
		super(worldIn);
		this.setSize(3.4F, 5.4F);
		this.stepHeight = 1.5F;
		this.setAttackDamage(4.0F);
		this.setIsAngry(true);
		this.setDropAmounts(0, 6);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySauropod.class, 200, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityStego.class, 100, false));
		this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityTricer.class, 100, false));
		this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 50, false));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(65.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.55D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.8D);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) 
	{
		return new EntityRex(this.worldObj);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.provider.dimensionId == DDDimensionRegistry.dimensionID)
		{
			int x = MathHelper.floor_double(this.posX);
			int y = MathHelper.floor_double(this.boundingBox.minY);
			int z = MathHelper.floor_double(this.posZ);
			int[][] spawnSpace = { {x-1,y,z-1},{x+0,y,z-1},{x+1,y,z-1},{x-1,y,z+0},{x+0,y,z+0},{x+1,y,z+0},{x-1,y,z+1},{x+0,y,z+1},{x+1,y,z+1} };
			Material curBlockMaterial;
			for(int i = 0; i < 4; ++i)
			{
				for(int j = 0, k = spawnSpace.length; j < k; ++j)
				{
					curBlockMaterial = this.worldObj.getBlock(spawnSpace[j][0], spawnSpace[j][1] + i, spawnSpace[j][2]).getMaterial();
					if(curBlockMaterial != Material.air && curBlockMaterial != Material.plants)
					{
						return false;
					}
				}
			}
			return true;
		}
		return false;	
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{	
		player.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackDamage());
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	@Override
	public int getMaxSpawnedInChunk()
	{
		return 2;
	}
}
