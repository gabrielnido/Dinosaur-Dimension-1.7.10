package com.dinodim.entity;

import com.dinodim.entity.ai.DDEntityAIFollowParent;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenRiver;

public class EntityDimetrodon extends DDAgeableMob
{
	public EntityDimetrodon(World worldIn)
	{
		super(worldIn);
		this.setSize(1.0F, 0.8F);
		this.setIsAngry(true);
		this.setDropAmounts(0, 2);
		this.setAttackDamage(1.0F);
		this.getNavigator().setAvoidsWater(false);
        this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new DDEntityAIFollowParent(this, 1.25D));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAILookIdle(this));
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.17D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.1D);
    }
	
	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) 
	{
		return new EntityDimetrodon(this.worldObj);
	}
	
	/**
     * Will return how many at most can spawn in a chunk at once.
     */
	@Override
    public int getMaxSpawnedInChunk()
    {
		return this.worldObj.getBiomeGenForCoords((int)this.posX, (int)this.posZ) instanceof BiomeGenRiver ? 8 : 4;
    }
	
	/**
	 * Called by a player entity when they collide with an entity
	 */
	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{	
		player.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackDamage());
	}
}
