package com.dinodim.entity;

import com.dinodim.dimension.DDDimensionRegistry;
import com.dinodim.items.DDItemMonsterPlacer;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTrilobite extends EntityAgeable
{		
	public EntityTrilobite(World worldIn)
	{
		super(worldIn);
		this.setSize(0.8F, 0.25F);
		double d0 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
		this.getNavigator().setAvoidsWater(false);	
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, d0, d0));
        this.tasks.addTask(2, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(3, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20D);
    }

	@Override
	public boolean canBreatheUnderwater()
    {
        return true;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
	@Override
    public boolean getCanSpawnHere()
    {
        return this.worldObj.provider.dimensionId == DDDimensionRegistry.dimensionID && this.worldObj.checkNoEntityCollision(this.boundingBox);
    }
	
	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData idata)
    {
		this.setGrowingAge(-24000);
        return super.onSpawnWithEgg(idata);
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
	@Override
    protected boolean canDespawn()
    {
        return true;
    }

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) 
	{
		return new EntityTrilobite(this.worldObj);
	}
	
	/**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
	@Override
    public boolean interact(EntityPlayer player)
    {
        ItemStack itemstack = player.inventory.getCurrentItem();

        if (itemstack != null && itemstack.getItem() instanceof DDItemMonsterPlacer)
        {
            if (!this.worldObj.isRemote)
            {
                Class oclass = EntityList.getClassFromID(itemstack.getItemDamage());

                if (oclass != null && oclass.isAssignableFrom(this.getClass()))
                {
                    EntityAgeable entityageable = this.createChild(this);

                    if (entityageable != null)
                    {
                        entityageable.setGrowingAge(-24000);
                        entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                        this.worldObj.spawnEntityInWorld(entityageable);

                        if (itemstack.hasDisplayName())
                        {
                            entityageable.setCustomNameTag(itemstack.getDisplayName());
                        }

                        if (!player.capabilities.isCreativeMode)
                        {
                            --itemstack.stackSize;

                            if (itemstack.stackSize <= 0)
                            {
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                            }
                        }
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
