package com.dinodim.dimension;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

public class DDWorldProvider extends WorldProvider
{
	public static final String dimensionName = "Prehistory";
	
	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new DDWorldChunkManager(worldObj.getSeed(), terrainType);
		this.dimensionId = DDDimensionRegistry.dimensionID;
		this.hasNoSky = false;
	}
	
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new DDChunkProvider(worldObj, dimensionId, false);
				//super.createChunkGenerator();
	}
	
	/** Get Provider for dimension **/
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(DDDimensionRegistry.dimensionID);
	}
	
	@Override
	public String getDimensionName() 
	{
		return dimensionName;
	}
	
	/** Can player re-spawn here **/
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	/** Determines the dimension the player will be respawned in **/
	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return 0;
	}
	
	/** @return the dimension join message */
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Leaving your own time";
	}

	/** @return the dimension leave message */
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Returning to your own time";
	}

}
