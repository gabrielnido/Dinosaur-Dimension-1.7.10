package com.dinodim.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class DDBlocks 
{
	public static Block caveBlock;
	public static Block tar;
	public static Block plantFlat1;
	public static Block plantFlat2;
	public static Block plantFlat3;
	public static Block flowerPurple;
	public static Block flowerBulb;
	public static Block flowerBlue;
	public static Block flowerWhite;
	public static Block grassShort;
	public static Block grassTall;
	public static Block log1;
	public static Block plank1;
	public static Block dinoPortal;

	public static void mainRegistry()
	{
		init();
		register();
	}
	
	private static void init() 
	{	
		caveBlock = new BlockCavePainting();
		tar = new BlockTar();
		plantFlat1 = new BlockFlatDecorPlant("planttop1");
		plantFlat2 = new BlockFlatDecorPlant("planttop2");
		plantFlat3 = new BlockFlatDecorPlant("planttop3");
		flowerPurple = new BlockFullDecorPlant("plant1");
		flowerBulb = new BlockFullDecorPlant("plant2");
		flowerBlue = new BlockFullDecorPlant("plant3");
		flowerWhite = new BlockFullDecorPlant("plant4");
		grassShort = new BlockFullDecorPlant("grass1").setRenderType(6);
		grassTall = new BlockStackableGrass("grass2");
		log1 = new DDBlockWood("mirabilis_log", false);
		plank1 = new DDBlockWood("mirabilis_planks", true);
		
		dinoPortal = new BlockDDPortal();
	}
	
	
	private static void register() 
	{
		reg(caveBlock);
		reg(tar);
		reg(plantFlat1);
		reg(plantFlat2);
		reg(plantFlat3);
		reg(flowerPurple);
		reg(flowerBulb);
		reg(flowerBlue);
		reg(flowerWhite);
		reg(grassShort);
		reg(grassTall);
		reg(log1);
		reg(plank1);
		reg(dinoPortal);
	}
	
	private static void reg(Block b)
	{
		GameRegistry.registerBlock(b, b.getUnlocalizedName());
	}
}
