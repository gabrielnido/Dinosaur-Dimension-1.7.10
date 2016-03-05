package com.dinodim.entity;

import com.dinodim.entity.ai.DDEntityAIFollowParent;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class DDEntityHerbivoreMob extends DDAgeableMob
{		
	public DDEntityHerbivoreMob(World worldIn)
	{
		super(worldIn);
		this.setSize(1.5F, 1.5F);
		this.stepHeight = 1.0F;
		this.setIsAngry(false);
		this.setDropAmounts(4, 4);
		this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAITempt(this, 2.5D, Items.wheat, false));
		this.tasks.addTask(2, new DDEntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIEatGrass(this));
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.6D);
    }
	
	/**
     * Returns true if the newer Entity AI code should be run
     */
	@Override
    protected boolean isAIEnabled()
    {
        return true;
    }
	
	@Override
	protected void updateAITick()
	{
		super.updateAITick();
		if(this.getIsAngry() && this.entityToAttack != null)
		{
			this.faceEntity(entityToAttack, 100.0F, 100.0F);
		}
	}
	
	@Override
	public void onUpdate()
    {
        super.onUpdate();
    }

	
	@Override
	protected Item getDropItem()
    {
        return Items.leather;
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
	@Override
    protected void dropFewItems(boolean hasPlayerHit, int lootingLev)
    {
        int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingLev) + 1;
        int k;

        for (k = 0; k < j; ++k)
        {
            this.dropItem(Items.leather, 1);
        }

        j = this.rand.nextInt(3) + 3 + this.rand.nextInt(1 + lootingLev);

        for (k = 0; k < j; ++k)
        {
            if (this.isBurning())
            {
                this.dropItem(Items.cooked_beef, 1);
            }
            else
            {
                this.dropItem(Items.beef, 1);
            }
        }
    }
	
	/**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
	@Override
	protected Entity findPlayerToAttack()
    {
		return this.getIsAngry() ? super.findPlayerToAttack() : null;
    }
	
	/**
     * Called when the entity is attacked.
     */
	@Override
    public boolean attackEntityFrom(DamageSource damagesource, float amnt)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = damagesource.getEntity();

            if (entity instanceof EntityPlayer)
            {
            	this.setIsAngry(true);
                this.setAttackTarget((EntityLivingBase)entity);
                this.entityToAttack = entity;
            }
            return super.attackEntityFrom(damagesource, amnt);
        }
    }
	
	@Override
	public boolean getCanSpawnHere()
	{
		int x = MathHelper.floor_double(this.posX);
		int y = MathHelper.floor_double(this.boundingBox.minY + 1);
		int z = MathHelper.floor_double(this.posZ);
		int[][] spawnSpace = { {x-1,y,z-1},{x+0,y,z-1},{x+1,y,z-1},{x-1,y,z+0},{x+0,y,z+0},{x+1,y,z+0},{x-1,y,z+1},{x+0,y,z+1},{x+1,y,z+1} };
		Material curBlockMaterial;
		for(int i = 0; i < 3; ++i)
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
	
	/**
     * Will return how many at most can spawn in a chunk at once.
     */
	@Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }
}
