package com.dinodim.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityTricer extends DDEntityHerbivoreMob
{	
	public EntityTricer(World worldIn)
	{
		super(worldIn);
		this.setSize(2.5F, 2.5F);
		this.setAttackDamage(3.0F);
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20D);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entity) 
	{
		return new EntityTricer(this.worldObj);
	}
}
