package com.dinodim.dimension;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.DimensionManager;

public class DDDimensionRegistry 
{
	public static final int dimensionID = -3;
	
	public static void mainRegistry()
	{
		registerDimension();
	}

	private static void registerDimension() 
	{
		DimensionManager.registerProviderType(dimensionID, DDWorldProvider.class, false);
		DimensionManager.registerDimension(dimensionID, dimensionID);
	}
}
