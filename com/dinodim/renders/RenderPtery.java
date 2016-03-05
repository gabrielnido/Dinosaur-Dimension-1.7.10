package com.dinodim.renders;

import com.dinodim.entity.EntityPtero;
import com.dinodim.entitymodel.ModelPtero;
import com.dinodim.main.RefStrings;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderPtery extends RenderLiving
{
	private static ResourceLocation TEXTURE = new ResourceLocation(RefStrings.MOD_ID + ":textures/entity/ptero.png");

	public RenderPtery() 
	{
		super(new ModelPtero(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return this.TEXTURE;
	}
	
	@Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        preRenderCallbackCustom((EntityPtero)entity, f);
    }
  
    protected void preRenderCallbackCustom(EntityPtero entity, float f)
    {
        // some people do some G11 transformations or blends here, like you can do
        // GL11.glScalef(2F, 2F, 2F); to scale up the entity 
    }

}
