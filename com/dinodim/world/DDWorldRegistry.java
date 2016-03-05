package com.dinodim.world;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class DDWorldRegistry 
{
	public static void mainRegistry() 
	{
		initWorldGen();
	}
	
	public static void initWorldGen() 
	{
		registerWorldGen(new DDWorldGen(), 100);	
	}
	
	public static void registerWorldGen(IWorldGenerator worldGenClass, int weightedProbability) 
	{
		GameRegistry.registerWorldGenerator(worldGenClass, weightedProbability);
	}
}