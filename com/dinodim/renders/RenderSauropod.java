package com.dinodim.renders;

import com.dinodim.entity.EntitySauropod;
import com.dinodim.entitymodel.ModelSauropod;
import com.dinodim.main.RefStrings;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderSauropod extends RenderLiving
{
	private static ResourceLocation TEXTURE = new ResourceLocation(RefStrings.MOD_ID + ":textures/entity/sauropod.png");

	public RenderSauropod() 
	{
		super(new ModelSauropod(), 0.6F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return this.TEXTURE;
	}
	
	@Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        preRenderCallbackCustom((EntitySauropod)entity, f);
    }
  
    protected void preRenderCallbackCustom(EntitySauropod entity, float f)
    {
        // some people do some G11 transformations or blends here, like you can do
        // GL11.glScalef(2F, 2F, 2F); to scale up the entity 
    }

}
