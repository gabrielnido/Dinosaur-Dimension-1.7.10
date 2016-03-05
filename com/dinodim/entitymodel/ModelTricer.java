package com.dinodim.entitymodel;

import org.lwjgl.opengl.GL11;

import com.dinodim.main.DDUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTricer extends ModelBase
{
	//fields
	ModelRenderer body;
	ModelRenderer head;
	ModelRenderer shield;
	ModelRenderer hornsLong;
	ModelRenderer hornShort;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer body2;
	private static float rad90deg = DDUtils.degToRad(90F);

	public ModelTricer()
	{
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this, 0, 14);
		body.addBox(-8F, -5F, -7F, 16, 13, 10);
		body.addBox(-8F, -11F, -7F, 16, 6, 9);
		body.setRotationPoint(0F, 7F, 0F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		DDUtils.setRotation(body, rad90deg, 0F, 0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3F, -8F, -2F, 6, 8, 5);
		head.setRotationPoint(0F, 11F, -11F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		DDUtils.setRotation(head, rad90deg, 0F, 0F);
		shield = new ModelRenderer(this, 0, 50);
		shield.addBox(-9F, -12F, 0F, 18, 12, 2);
		shield.setRotationPoint(0F, 8F, -14F);
		shield.setTextureSize(64, 64);
		shield.mirror = true;
		DDUtils.setRotation(shield, DDUtils.degToRad(-40F), 0F, 0F);
		hornsLong = new ModelRenderer(this, 58, 0);
		hornsLong.addBox(3F, 0F, 0F, 1, 14, 1);
		hornsLong.addBox(-4F, 0F, 0F, 1, 14, 1);
		hornsLong.setRotationPoint(0F, 7F, -11F);
		hornsLong.setTextureSize(64, 64);
		hornsLong.mirror = true;
		DDUtils.setRotation(hornsLong, DDUtils.degToRad(-130F), 0F, 0F);
		hornShort = new ModelRenderer(this, 56, 18);
		hornShort.addBox(-1F, 0F, 0F, 2, 5, 1);
		hornShort.setRotationPoint(0F, 9F, -17F);
		hornShort.setTextureSize(64, 64);
		hornShort.mirror = true;
		DDUtils.setRotation(hornShort, DDUtils.degToRad(-130F), 0F, 0F);
		leg1 = new ModelRenderer(this, 48, 50);
		leg1.addBox(-2F, 0F, -2F, 4, 10, 4);
		leg1.setRotationPoint(-6F, 14F, 6F);
		leg1.setTextureSize(64, 64);
		leg1.mirror = false;
		DDUtils.setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 48, 50);
		leg2.addBox(-2F, 0F, -2F, 4, 10, 4);
		leg2.setRotationPoint(6F, 14F, 6F);
		leg2.setTextureSize(64, 64);
		leg2.mirror = false;
		DDUtils.setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 48, 50);
		leg3.addBox(-3F, 0F, -3F, 4, 10, 4);
		leg3.setRotationPoint(-5F, 14F, -8F);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		DDUtils.setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 48, 50);
		leg4.addBox(-1F, 0F, -3F, 4, 10, 4);
		leg4.setRotationPoint(5F, 14F, -8F);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		DDUtils.setRotation(leg4, 0F, 0F, 0F);
		tail1 = new ModelRenderer(this, 23, 0);
		tail1.addBox(-5F, 0F, -3F, 10, 7, 7);
		tail1.setRotationPoint(0F, 11F, 8F);
		tail1.setTextureSize(64, 64);
		tail1.mirror = true;
		DDUtils.setRotation(tail1, rad90deg, 0F, 0F);
		tail2 = new ModelRenderer(this, 44, 37);
		tail2.addBox(-3F, 0F, -3F, 5, 7, 5);
		tail2.setRotationPoint(0.5F, 18F, 8F); //arg2 was 11F, arg3 was 15 (up-down)
		tail2.setTextureSize(64, 64);
		tail2.mirror = true;
		DDUtils.setRotation(tail2, rad90deg, 0F, 0F);
		tail3 = new ModelRenderer(this, 53, 29);
		tail3.addBox(-1F, 0F, -1F, 2, 5, 3);
		tail3.setRotationPoint(0F, 25F, 7F); //arg3 was 22
		tail3.setTextureSize(64, 64);
		tail3.mirror = true;
		DDUtils.setRotation(tail3, rad90deg, 0F, 0F);
		
		//childs
		DDUtils.convertToChild(tail2, tail3);
		DDUtils.convertToChild(tail1, tail2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if(this.isChild)
		{
			body.render(f5);
			head.render(f5);
			shield.render(f5);
			hornsLong.render(f5);
			hornShort.render(f5);
			leg1.render(f5);
			leg2.render(f5);
			leg3.render(f5);
			leg4.render(f5);
			tail1.render(f5);  // parent to tail2, tail3
		}
		else
		{
			float f6 = 2.0F;
	        GL11.glPushMatrix();
	        GL11.glScalef(f6, f6, f6);
	        GL11.glTranslatef(0.0F, -12.0F * f5, 0.0F);
		    body.render(f5);
			head.render(f5);
			shield.render(f5);
			hornsLong.render(f5);
			hornShort.render(f5);
			leg1.render(f5);
			leg2.render(f5);
			leg3.render(f5);
			leg4.render(f5);
			tail1.render(f5);  // parent to tail2, tail3
	        GL11.glPopMatrix();
		}
		
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float legX1 = MathHelper.cos(f * 0.5662F) * 1.4F * f1;;
		float legX2 = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;;
		this.leg1.rotateAngleX = this.leg4.rotateAngleX = legX1;
		this.leg2.rotateAngleX = this.leg3.rotateAngleX = legX2;  
	}

}
