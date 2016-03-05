package com.dinodim.entity;

import com.dinodim.dimension.DDDimensionRegistry;
import com.dinodim.entity.ai.DDEntityAIFollowParent;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySauropod extends DDAgeableMob
{	
	public EntitySauropod(World worldIn)
	{
		super(worldIn);
		this.setSize(3.4F, 5.4F);
		this.stepHeight = 1.0F;
		this.setDropAmounts(6, 5);
		this.setAttackDamage(0.0F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityRex.class, 16.0F, 0.29D, 0.29D));
		this.tasks.addTask(2, new DDEntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(3, new EntityAITempt(this, 2.5D, Items.wheat, false));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAILookIdle(this));
	}
	
	/**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
	@Override
    protected Entity findPlayerToAttack()
    {
       return null;
    }

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		this.setIsAngry(false);
		/* try to make sauropods eat leaves
        double d0 = this.posY + (double)this.getEyeHeight();
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_float((float)MathHelper.floor_double(d0));
        int k = MathHelper.floor_double(this.posZ);
        Block block = this.worldObj.getBlock(i, j + 1, k);

        if (block.getMaterial() == Material.leaves)
        {                	
        	this.worldObj.setBlockToAir(i, j + 1, k);
        }*/
	}


	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(78.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.19D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.9D);
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

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) 
	{
		return new EntitySauropod(this.worldObj);
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	@Override
	public int getMaxSpawnedInChunk()
	{
		return 2;
	}

	@Override
	public boolean canDespawn()
	{
		return false;
	}
}
