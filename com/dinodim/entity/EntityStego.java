package com.dinodim.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityStego extends DDEntityHerbivoreMob
{	
	public EntityStego(World worldIn)
	{
		super(worldIn);
		this.setSize(2.5F, 3.0F);
		this.setAttackDamage(2.5F);
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable entity) 
	{
		return new EntityStego(this.worldObj);
	}
}
