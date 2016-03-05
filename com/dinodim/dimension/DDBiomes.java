package com.dinodim.dimension;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeManager;

public class DDBiomes 
{
	public static BiomeGenBase DDPlainsLow;
	public static BiomeGenBase DDPlainsHills;
	public static BiomeGenBase DDMountains;
	public static BiomeGenBase DDOcean;
	public static BiomeGenBase DDJungle;
	public static BiomeGenBase DDRiver;
	public static BiomeGenBase DDDesert;
	public static final int idDDPlainsLow = 141;
	public static final int idDDPlainsHills = 142;
	public static final int idDDMountains = 143;
	public static final int idDDOcean = 144;
	public static final int idDDJungle = 145;
	public static final int idDDRiver = 146;
	public static final int idDDDesert = 147;
	
	public static void mainRegistry()
	{
		init();
		register();
	}

	private static void init() 
	{			
		DDPlainsLow = new BiomeGenDDPlains(idDDPlainsLow).setBiomeName("Prehistoric Plains").setHeight(new Height(0.18F, 0.05F));
		DDPlainsHills = new BiomeGenDDPlains(idDDPlainsHills).setBiomeName("Prehistoric Hills").setHeight(new Height(0.2F, 0.2F));
		DDMountains = new BiomeGenDDPlains(idDDMountains).setBiomeName("Prehistoric Mountains").setHeight(new Height(0.9F, 0.5F));
	    DDOcean = (new BiomeGenDDOcean(idDDOcean)).setColor(112).setBiomeName("Prehistoric Ocean").setHeight(new Height(-1.0F, 0.1F));
	    DDJungle = new BiomeGenDDJungle(idDDJungle, false).setBiomeName("Prehistoric Jungle").setHeight(new Height(0.2F, 0.15F));
	    DDRiver = new BiomeGenDDRiver(idDDRiver).setBiomeName("Prehistoric River").setHeight(new Height(-0.5F, 0.0F));
	    DDDesert = new BiomeGenDDDesert(idDDDesert).setBiomeName("Prehistoric Desert").setHeight(new Height(0.2F, 0.1F));
	}

	private static void register() 
	{
		/*BiomeDictionary.registerBiomeType(DDPlainsLow, Type.PLAINS);
		BiomeDictionary.registerBiomeType(DDPlainsHills, Type.PLAINS);
		BiomeDictionary.registerBiomeType(DDMountains, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(DDOcean, Type.OCEAN);
		BiomeDictionary.registerBiomeType(DDJungle, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(DDRiver, Type.RIVER);
		
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(DDPlainsLow, idDDPlainsLow));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(DDPlainsHills, idDDPlainsHills));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(DDMountains, idDDMountains));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(DDOcean, idDDOcean));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(DDJungle, idDDJungle));
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(DDRiver, idDDRiver));*/
		
		/*
		BiomeManager.addSpawnBiome(DDPlainsLow);
		BiomeManager.addSpawnBiome(DDPlainsHills);
		BiomeManager.addSpawnBiome(DDMountains);
		BiomeManager.addSpawnBiome(DDOcean);
		BiomeManager.addSpawnBiome(DDJungle);
		*/
		
		BiomeManager.oceanBiomes.add(DDOcean);
	}
}
