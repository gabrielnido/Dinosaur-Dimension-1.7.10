package com.dinodim.main;

import com.dinodim.blocks.DDBlocks;
import com.dinodim.dimension.DDBiomes;
import com.dinodim.dimension.DDDimensionRegistry;
import com.dinodim.entity.DDEntityRegistry;
import com.dinodim.proxies.ClientProxy;
import com.dinodim.proxies.CommonProxy;
import com.dinodim.world.DDWorldRegistry;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

@Mod(modid = RefStrings.MOD_ID, name = RefStrings.NAME, version = RefStrings.VERSION, acceptedMinecraftVersions = RefStrings.MCVERSION)
public class DDMain 
{
	@SidedProxy(clientSide = RefStrings.CLIENT, serverSide = RefStrings.SERVER)
	public static CommonProxy proxy;
	public static ClientProxy cproxy;
	
	@Instance(RefStrings.MOD_ID)
	public static DDMain instance;
	
	/** Creative tab for this mod */
	public static CreativeTabs tabMain;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent Event) 
	{
		World.MAX_ENTITY_RADIUS = 4.0D;
		tabMain = new DDCreativeTab("tabMain");	
		
		DDBlocks.mainRegistry();
		DDEntityRegistry.mainRegistry();
		DDBiomes.mainRegistry();
		DDDimensionRegistry.mainRegistry();
		DDWorldRegistry.mainRegistry();
		CraftingManager.mainRegistry();
		proxy.registerRenders();
	}
}

