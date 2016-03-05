package com.dinodim.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityVRaptor extends DDAgeableMob
{
	private boolean isAngry;
	
	public EntityVRaptor(World worldIn)
	{
		super(worldIn);
		this.setSize(0.8F, 1.1F);
		this.setAttackDamage(1.0F);
		this.getNavigator().setAvoidsWater(true);
	    this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, true));
	    this.tasks.addTask(3, new EntityAITempt(this, 1.25D, Items.beef, false));
        this.tasks.addTask(4, new EntityAIWander(this, 0.9D));	    
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.41D);
    }
	
	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) 
	{
		return new EntityVRaptor(this.worldObj);
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
	public void updateAITick()
	{
		super.updateAITick();
	}

	/**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
	@Override
	protected Entity findPlayerToAttack()
    {
		boolean isDark = this.getBrightness(1.0F) < 0.5F;
        if(isDark || this.getIsAngry())
        {
            double d0 = 16.0D;
            EntityPlayer player = this.worldObj.getClosestVulnerablePlayerToEntity(this, d0);  
            this.setIsAngry(true);
            this.setAttackTarget((EntityLivingBase)player);
            return player;
        }
        else
        {
            return null;
        }
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
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(16.0D, 16.0D, 16.0D));

                // for each entity in the list
                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity1 = (Entity)list.get(i);
                    // if it's an EntityVRaptor, set its attack target & allow it to attack player
                    if (entity1 instanceof EntityVRaptor)
                    {
                        EntityVRaptor entityraptor = (EntityVRaptor)entity1;
                        entityraptor.setIsAngry(true);
                        entityraptor.entityToAttack = entity;
                        entityraptor.setAttackTarget((EntityLivingBase)entity);
                    }
                }

                this.setIsAngry(true);
                this.entityToAttack = entity;
                this.setAttackTarget((EntityLivingBase)entity);
            }
            return super.attackEntityFrom(damageSource, amnt);
        }
    }
	
	/**
     * Will return how many at most can spawn in a chunk at once.
     */
	@Override
    public int getMaxSpawnedInChunk()
    {
        return 6;
    }
}
