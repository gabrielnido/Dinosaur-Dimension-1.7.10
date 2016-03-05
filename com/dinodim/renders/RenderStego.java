package com.dinodim.renders;

import com.dinodim.entity.EntityStego;
import com.dinodim.entitymodel.ModelStego;
import com.dinodim.main.RefStrings;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderStego extends RenderLiving
{
	private static ResourceLocation TEXTURE = new ResourceLocation(RefStrings.MOD_ID + ":textures/entity/stego.png");

	public RenderStego() 
	{
		super(new ModelStego(), 0.6F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) 
	{
		return this.TEXTURE;
	}
	
	@Override
    protected void preRenderCallback(EntityLivingBase entity, float f)
    {
        preRenderCallbackCustom((EntityStego)entity, f);
    }
  
    protected void preRenderCallbackCustom(EntityStego entity, float f)
    {
        // some people do some G11 transformations or blends here, like you can do
        // GL11.glScalef(2F, 2F, 2F); to scale up the entity 
    }

}
