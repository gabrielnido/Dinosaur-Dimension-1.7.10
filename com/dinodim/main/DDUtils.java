package com.dinodim.main;

import java.util.Random;

import com.dinodim.items.DDItemMonsterPlacer;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class DDUtils 
{
	public static Random rand = new Random();

	/*
	 * Entity Utils
	 */

	/** registers the entity
	 */
	public static int registerEntity(Class entityClass, String name)
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, DDMain.instance, 64, 1, true);
		return entityID;
	}

	/** Registers an entity and associates it with a colored spawn egg
	 * @param entityClass
	 * @param name entity name
	 */
	public static int registerEntityAndEgg(Class entityClass, String name, int eggColor, int eggSpots)
	{
		int entityID = registerEntity(entityClass, name);
		Item itemSpawnEgg = new DDItemMonsterPlacer(name, eggColor, eggSpots).setUnlocalizedName("spawn_egg_" + name.toLowerCase()).setCreativeTab(DDMain.tabMain);
		GameRegistry.registerItem(itemSpawnEgg, "spawnEgg" + name);
		    
		return entityID;
	}

	/*
	 * World Utils
	 */

	/**
	 * Given x,y,z coordinates of center block, checks all blocks in square with length (2 * radius) + 1.
	 * @param x x-coordinate
	 * @param y layer 0 y-coordinate
	 * @param z z-coordinate
	 * @param keyBlock block to search for
	 * @param radius distance outward to check, centering on (x,y,z)
	 * @param layers number of times to check a square area, then increment y (min 1)
	 * @return True if block is found, else false
	 */
	public static boolean isBlockNearby(World world, int x, int y, int z, Block keyBlock, int radius, int layers) 
	{
		Block curBlock;
		int rows = (radius * 2) + 1;
		int cols = rows;
		int xPos;
		int yPos;
		int zPos;
		if(radius < 1) radius = 1;
		if(layers < 1) layers = 1;

		for(int i = 0; i < layers; i++)	//check layers
		{
			for(int j = 0; j < cols; j++)	//check each column (Cartesian plane)
			{
				for(int k = 0; k < rows; k++)	//check each row
				{
					xPos = x + radius - k;
					yPos = y + i;
					zPos = z + radius - j;
					curBlock = world.getBlock(xPos,yPos,zPos);
					if(curBlock == keyBlock)
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks the six blocks this block is touching, looking for Block keyBlock
	 * Args: this blocks world,x,y,z and the block to check for
	 * @return true if keyBlock is found, else false
	 */
	public static boolean isTouchingBlock(World worldIn, int x, int y, int z, Block keyBlock)
	{
		return 	(worldIn.getBlock(x+1, y, z) == keyBlock) || (worldIn.getBlock(x-1, y, z) == keyBlock) || 
				(worldIn.getBlock(x, y+1, z) == keyBlock) || (worldIn.getBlock(x, y-1, z) == keyBlock) || 
				(worldIn.getBlock(x, y, z+1) == keyBlock) || (worldIn.getBlock(x, y, z-1) == keyBlock);
	}
	
	public static boolean isBlockTouchingAir(World world, int x, int y, int z)
	{
		return isTouchingBlock(world, x, y, z, Blocks.air);
	}
	
	public static boolean setBlockIfAir(World worldIn, int x, int y, int z, Block newBlock)
	{
		return worldIn.getBlock(x, y, z).getMaterial() == Material.air && worldIn.setBlock(x, y, z, newBlock);
	}

	/**
	 * Starts at y == 1 and checks each block above it until it detects air.
	 * @return y-value of the first air block it detects; if none, returns 0.
	 */
	public static int getFirstAirBlockFromBelow(World worldIn, int x, int z) 
	{
		int y = 1;
		
		while(!worldIn.isAirBlock(x, y, z) && y < 256)
		{
			++y;		
		}
		
		return y < 256 ? y : 0;
	}

	/*
	 * Model Utils
	 */

	private static float spinD = 0.02F;

	/** This is really useful for converting the source from a Techne model export
	 * which will have absolute rotation points that need to be converted before
	 * creating the addChild() relationship
	 * @author Jabelar
	 */
	public static void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
	{
		// move child rotation point to be relative to parent
		parChild.rotationPointX -= parParent.rotationPointX;
		parChild.rotationPointY -= parParent.rotationPointY;
		parChild.rotationPointZ -= parParent.rotationPointZ;
		// make rotations relative to parent
		parChild.rotateAngleX -= parParent.rotateAngleX;
		parChild.rotateAngleY -= parParent.rotateAngleY;
		parChild.rotateAngleZ -= parParent.rotateAngleZ;
		// create relationship
		parParent.addChild(parChild);
	}

	public static void spinX(ModelRenderer model)
	{
		model.rotateAngleX += spinD;
	}

	public static void spinY(ModelRenderer model)
	{
		model.rotateAngleY += spinD;
	}

	public static void spinZ(ModelRenderer model)
	{
		model.rotateAngleZ += spinD;
	}

	public static void setRotationDegrees(ModelRenderer model, float degreesX, float degreesY, float degreesZ)
	{
		setRotation(model, degToRad(degreesX), degToRad(degreesY), degToRad(degreesZ));
	}

	public static void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public static void setSpikeRotation(ModelRenderer model)
	{
		setRotation(model, 0F, DDUtils.degToRad(90F), 0F);
	}

	public static float degToRad(float degrees)
	{
		return degrees * (float)Math.PI / 180 ;
	}

	public static float radToDeg(float radians)
	{
		return radians * 180 / (float)Math.PI;
	}
}
