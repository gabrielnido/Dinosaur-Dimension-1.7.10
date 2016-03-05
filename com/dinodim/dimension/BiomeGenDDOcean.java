package com.dinodim.dimension;

import com.dinodim.entity.EntityTrilobite;

import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.biome.BiomeGenOcean;

public class BiomeGenDDOcean extends BiomeGenOcean
{
	public BiomeGenDDOcean(int id)
	{
		super(id);
		this.setTemperatureRainfall(1.3F, 0.9F);
		this.setColor(2900485);
		
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityTrilobite.class, 12, 2, 6));
		
		this.theBiomeDecorator.grassPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = 0;
		this.theBiomeDecorator.waterlilyPerChunk = 8;
	}
}
