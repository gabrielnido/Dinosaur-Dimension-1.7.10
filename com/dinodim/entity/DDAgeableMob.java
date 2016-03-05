package com.dinodim.entity;

import java.util.List;

import com.dinodim.dimension.DDDimensionRegistry;
import com.dinodim.items.DDItemMonsterPlacer;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class DDAgeableMob extends EntityAgeable
{
	private float attackDamage;
	private boolean isAngry;
	private int numLeather;
	private int numMeat;
	
	public DDAgeableMob(World worldIn)
	{
		super(worldIn);
		this.setAttackDamage(0.0F);
		this.setIsAngry(false);
		this.setDropAmounts(2, 2);
	}

	public abstract EntityAgeable createChild(EntityAgeable entity);

	/**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
	@Override
    protected Entity findPlayerToAttack()
    {
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
    }
	
	/**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
	@Override
    protected void attackEntity(Entity entity, float f1)
    {
        if (this.attackTime <= 0 && f1 < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.faceEntity(entity, 100F, 100F);
            this.attackEntityAsMob(entity);
        }
    }
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);	
	}
	
	/**
     * Called when the entity is attacked.
     */
	@Override
    public boolean attackEntityFrom(DamageSource damageSource, float amnt)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = damageSource.getEntity();

            if (entity instanceof EntityPlayer && !((EntityPlayer)entity).capabilities.isCreativeMode && this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL)
            {
                this.setIsAngry(true);
                this.entityToAttack = entity;
                this.setAttackTarget((EntityLivingBase)entity);
            }
            return super.attackEntityFrom(damageSource, amnt);
        }
    }
	
	/**
	 * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
	 * par2 - Level of Looting used to kill this mob.
	 */
	@Override
	protected void dropFewItems(boolean hasPlayerHit, int lootingLev)
	{
		int j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingLev) + this.getLeatherDrops();
		int k;

		if(this.getLeatherDrops() > 0)
		{
			for (k = 0; k < j; ++k)
			{
				this.dropItem(Items.leather, 1);
			}
		}

		if(this.getMeatDrops() > 0)
		{
			j = this.rand.nextInt(3) + this.rand.nextInt(1 + lootingLev) + this.getMeatDrops();
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
	}
	
	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData idata)
    {
		int[] ages = {-1, 1};
        this.setGrowingAge(ages[rand.nextInt(ages.length)] * 24000);
        return super.onSpawnWithEgg(idata);
    }
	
	/** Sets minimum amounts of items this entity should drop. Args: leatherToDrop, meatToDrop */
	public void setDropAmounts(int minLeatherToDrop, int minMeatToDrop)
	{
		this.numLeather = minLeatherToDrop;
		this.numMeat = minMeatToDrop;
	}
	
	public int getLeatherDrops()
	{
		return this.numLeather;
	}
	
	public int getMeatDrops()
	{
		return this.numMeat;
	}
	
	@Override
	public boolean canDespawn()
	{
		return false;
	}
    
    public void setAttackDamage(float f)
    {
    	this.attackDamage = f;
    }
    
    public float getAttackDamage()
    {
    	return this.attackDamage;
    }
    
    /**
	 * Called by a player entity when they collide with an entity
	 */
	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{	
		if(this.getIsAngry() && !this.isDead)
		{
			player.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackDamage());
		}
		else
		{
			super.onCollideWithPlayer(player);
		}
	}
    
    public boolean getIsAngry() 
    {
		return this.isAngry;
	}
    
    public void setIsAngry(boolean b) 
    {
		this.isAngry = b;
	}

	/**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.provider.dimensionId == DDDimensionRegistry.dimensionID && super.getCanSpawnHere();
    }
}
