package com.dinodim.entitymodel;

import org.lwjgl.opengl.GL11;

import com.dinodim.main.DDUtils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelRex extends ModelBase
{
	//fields
	ModelRenderer body1;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer head;
	ModelRenderer arm1;
	ModelRenderer arm2;
	ModelRenderer leg1;
	ModelRenderer leg1b;
	ModelRenderer leg1c;
	ModelRenderer leg2;
	ModelRenderer leg2b;
	ModelRenderer leg2c;
	ModelRenderer tail1;
	ModelRenderer tail2;
	ModelRenderer tail3;
	ModelRenderer tail4;
	private static float rad90deg = DDUtils.degToRad(90F);
	private static float rad20deg = DDUtils.degToRad(20F);

	public ModelRex()
	{
		textureWidth = 64;
		textureHeight = 64;

		body1 = new ModelRenderer(this, 28, 46);
		body1.addBox(-4F, -2F, -2F, 8, 8, 10);
		body1.setRotationPoint(0F, 10F, 0F);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		DDUtils.setRotation(body1, rad90deg, 0F, 0F);
		body2 = new ModelRenderer(this, 30, 30);
		body2.addBox(-4F, -8F, 0F, 8, 6, 9);
		body2.setRotationPoint(0F, 10F, 0F);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		DDUtils.setRotation(body2, rad90deg, 0F, 0F);
		body3 = new ModelRenderer(this, 36, 15);
		body3.addBox(-3F, -14F, 2F, 6, 6, 8);
		body3.setRotationPoint(0F, 10F, 0F);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		DDUtils.setRotation(body3, rad90deg, 0F, 0F);
		head = new ModelRenderer(this, 16, 0);
		head.addBox(-2F, 0F, -3F, 4, 10, 6);
		head.setRotationPoint(0F, 2F, -14F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		DDUtils.setRotation(head, -rad90deg, 0F, 0F);
		arm1 = new ModelRenderer(this, 20, 40);
		arm1.addBox(-4F, 0F, -1F, 1, 5, 1);
		arm1.setRotationPoint(0F, 6F, -10F);
		arm1.setTextureSize(64, 64);
		arm1.mirror = true;
		DDUtils.setRotation(arm1, -0.6981317F, 0F, 0F);
		arm2 = new ModelRenderer(this, 20, 40);
		arm2.addBox(3F, 0F, -1F, 1, 5, 1);
		arm2.setRotationPoint(0F, 6F, -10F);
		arm2.setTextureSize(64, 64);
		arm2.mirror = true;
		DDUtils.setRotation(arm2, -0.6981317F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(-2F, 0F, -3F, 2, 7, 5);
	    leg1.setRotationPoint(-4F, 6F, 3F);
		leg1.setTextureSize(64, 64);
		leg1.mirror = false;
		DDUtils.setRotation(leg1, 0F, 0F, 0F);
		leg1b = new ModelRenderer(this, 0, 13);
		leg1b.addBox(-2F, 6F, -4F, 2, 6, 4);
		leg1b.setRotationPoint(-4F, 6F, 3F);
		leg1b.setTextureSize(64, 64);
		leg1b.mirror = false;
		DDUtils.setRotation(leg1b, DDUtils.degToRad(20F), 0F, 0F);
		leg1c = new ModelRenderer(this, 52, 0);
		leg1c.addBox(-2F, 9F, 3F, 2, 9, 4);
		leg1c.setRotationPoint(-4F, 6F, 3F);
		leg1c.setTextureSize(64, 64);
		leg1c.mirror = false;
		DDUtils.setRotation(leg1c, DDUtils.degToRad(-20F), 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 0);
	    leg2.addBox(0F, 0F, -3F, 2, 7, 5);
		leg2.setRotationPoint(4F, 6F, 3F);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		DDUtils.setRotation(leg2, 0F, 0F, 0F);
		leg2b = new ModelRenderer(this, 0, 13);
		leg2b.addBox(0F, 6F, -4F, 2, 6, 4);
		leg2b.setRotationPoint(4F, 6F, 3F);
		leg2b.setTextureSize(64, 64);
		leg2b.mirror = true;
		DDUtils.setRotation(leg2b, DDUtils.degToRad(20F), 0F, 0F);
		leg2c = new ModelRenderer(this, 52, 0);
		leg2c.addBox(0F, 9F, 3F, 2, 9, 4);
		leg2c.setRotationPoint(4F, 6F, 3F);
		leg2c.setTextureSize(64, 64);
		leg2c.mirror = true;
		DDUtils.setRotation(leg2c, DDUtils.degToRad(-20F), 0F, 0F);
		tail1 = new ModelRenderer(this, 0, 24);
		tail1.addBox(-3F, 0F, -2F, 6, 6, 7);
		tail1.setRotationPoint(0F, 8F, 6F);
		tail1.setTextureSize(64, 64);
		tail1.mirror = true;
		DDUtils.setRotation(tail1, rad90deg, 0F, 0F);
		tail2 = new ModelRenderer(this, 0, 38);
		tail2.addBox(-2F, 0F, -1F, 4, 8, 5);
		tail2.setRotationPoint(0F, 14F, 6F); //arg1 was 0, arg3 was 12F
		tail2.setTextureSize(64, 64);
		tail2.mirror = true;
		DDUtils.setRotation(tail2, rad90deg, 0F, 0F);
		tail3 = new ModelRenderer(this, 0, 52);
		tail3.addBox(-1.5F, 0F, -1F, 3, 8, 3);
		tail3.setRotationPoint(0F, 21F, 6F); //arg1 was 0, arg3 was 20F
		tail3.setTextureSize(64, 64);
		tail3.mirror = true;
		DDUtils.setRotation(tail3, rad90deg, 0F, 0F);
		tail4 = new ModelRenderer(this, 13, 52);
		tail4.addBox(-1F, 0F, -0.5F, 2, 6, 2);
		tail4.setRotationPoint(0F, 29F, 6F); //arg1 was 0, arg3 was 28F
		tail4.setTextureSize(64, 64);
		tail4.mirror = true;
		DDUtils.setRotation(tail4, rad90deg, 0F, 0F);
		
		// childs
		DDUtils.convertToChild(tail3, tail4);
		DDUtils.convertToChild(tail2, tail3);
		DDUtils.convertToChild(tail1, tail2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		float f6 = this.isChild ? 2.0F : 4.0F;
		float translateY = this.isChild ? -12.0F : -17.0F;
		GL11.glPushMatrix();
		GL11.glScalef(f6, f6, f6);
		GL11.glTranslatef(0.0F, translateY * f5, 0.0F);
		body1.render(f5);
		body2.render(f5);
		body3.render(f5);
		head.render(f5);
		arm1.render(f5);
		arm2.render(f5);
		leg1.render(f5);
		leg1b.render(f5);
		leg1c.render(f5);
		leg2.render(f5);
		leg2b.render(f5);
		leg2c.render(f5);
		tail1.render(f5); // parent to tail2, tail3, tail4
		GL11.glPopMatrix();
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float tailY = (DDUtils.degToRad(MathHelper.cos((entity.ticksExisted + entity.getEntityId()) * 0.1F) * 5F)); //inner float controls speed, outer float controls angle
		float legX1 = (MathHelper.cos(f * 0.5662F) * 1.4F * f1); // inner float was 0.6662F
		float legX2 = (MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1);
		this.tail1.rotateAngleY = tailY * 1.5F; 
		this.tail2.rotateAngleY = this.tail3.rotateAngleY = this.tail4.rotateAngleY = tailY;
		this.leg1.rotateAngleX = legX1;
		this.leg1b.rotateAngleX = legX1 + rad20deg;
		this.leg1c.rotateAngleX = legX1 - rad20deg;
		this.leg2.rotateAngleX = legX2;
		this.leg2b.rotateAngleX = legX2 + rad20deg;
		this.leg2c.rotateAngleX = legX2 - rad20deg;
	}
}
