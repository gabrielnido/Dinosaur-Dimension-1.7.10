package com.dinodim.dimension;

import com.dinodim.entity.EntityDimetrodon;
import com.dinodim.entity.EntityPtero;
import com.dinodim.entity.EntityRex;
import com.dinodim.entity.EntitySauropod;
import com.dinodim.entity.EntityStego;
import com.dinodim.entity.EntityTricer;
import com.dinodim.entity.EntityTrilobite;
import com.dinodim.entity.EntityVRaptor;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenDDPlains extends BiomeGenBase
{
	public BiomeGenDDPlains(int biomeId)
	{
		super(biomeId);
		this.setTemperatureRainfall(1.2F, 0.9F);
		this.setColor(2900485);
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.enableRain = true;
		this.theBiomeDecorator.generateLakes = true;
		
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityVRaptor.class, 9, 3, 6));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityDimetrodon.class, 9, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntitySauropod.class, 8, 2, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityStego.class, 8, 3, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityTricer.class, 8, 3, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityRex.class, 5, 1, 1));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPtero.class, 7, 2, 4));
		this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityTrilobite.class, 6, 2, 6));
		
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.waterlilyPerChunk = 16;
		this.theBiomeDecorator.reedsPerChunk = 16;
	}
	
	/**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     *//*
	@Override
    public BiomeDecorator createBiomeDecorator()
    {
        return getModdedBiomeDecorator(new DDBiomeDecorator());
    }*/
}
