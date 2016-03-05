package com.dinodim.entity;

import com.dinodim.dimension.DDDimensionRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityPtero extends DDEntityFlying implements IMob
{
	private int turnCounter;
	private int attackTimer;
	private boolean shouldTurn;
	Entity entityToAttack;

	public EntityPtero(World worldIn)
	{
		super(worldIn);
		this.setSize(1.25F, 0.5F);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.attackTimer = 0;
		this.turnCounter = 20;
		this.shouldTurn = false;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
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
		this.entityToAttack = this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL ? null : this.findPlayerToAttack();
		double d0;
		double d1;
		double d2;
		
		if(this.entityToAttack != null && --this.attackTimer <= 0) /** ai code to move toward entity */
		{
			this.attackTimer = 0;
			if(rand.nextInt(200) == 0)
			{
				this.resetAttackTimer(10);
			}
			d0 = (this.entityToAttack.posX - this.posX);
            d1 = (this.entityToAttack.posY - this.posY) + 1.5D;
            d2 = (this.entityToAttack.posZ - this.posZ);
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            // face target entity
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d0, d2)) * 180.0F / (float)Math.PI;
            this.motionX += d0 / d3 * 0.25D;
            this.motionY += d1 / d3 * 0.3D; // moves up and down a bit faster
            this.motionZ += d2 / d3 * 0.25D; 
		}
		else /** ai code to wander around */
		{
			this.shouldTurn = rand.nextInt(20) == 0 && --this.turnCounter <= 0;
			
			d0 = (rand.nextDouble() - rand.nextDouble()) * 0.2D;
			d1 = (rand.nextDouble() - rand.nextDouble()) * 0.08D;
			d2 = (rand.nextDouble() - rand.nextDouble()) * 0.2D;
			this.motionX += d0;
			this.motionY += d1 - 0.0002D; // tendency to stay low (so it's not out of reach)
			this.motionZ += d2;
			if(this.shouldTurn)
			{
				this.turnCounter = 40 + rand.nextInt(20);
				this.rotationYaw += MathHelper.wrapAngleTo180_float(rand.nextInt(90) - rand.nextInt(90));
			}
			else
			{
				this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;
			}	
		}
	
	}
	
	private void resetAttackTimer(int baseValue) 
	{
		this.attackTimer = baseValue + rand.nextInt(40);
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{	
		if(player.attackEntityFrom(DamageSource.causeMobDamage(this), 1.5F)) 
		{
			this.resetAttackTimer(40);
		}
	}

	/**
	 * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
	 * (Animals, Spiders at day, peaceful PigZombies).
	 */
	protected Entity findPlayerToAttack()
	{
		return this.worldObj.getClosestVulnerablePlayerToEntity(this, 24.0D);
	}
	
	/**
     * Called when the entity is attacked.
     */
	@Override
    public boolean attackEntityFrom(DamageSource dmgsource, float amnt)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
        	if(dmgsource.getEntity() instanceof EntityLivingBase)
        	{
        		this.resetAttackTimer(40);
        	}
            return super.attackEntityFrom(dmgsource, amnt);
        }
    }

	@Override
	protected Item getDropItem()
	{
		return Items.leather;
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	@Override
	public int getMaxSpawnedInChunk()
	{
		return 5;
	}
	
	@Override
	public boolean canDespawn()
	{
		return false;
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
