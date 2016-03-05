package com.dinodim.dimension;

import java.util.Random;

import com.dinodim.blocks.DDBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class BiomeGenDDDesert extends BiomeGenBase
{
	public BiomeGenDDDesert(int id)
	{
		super(id);
		this.setTemperatureRainfall(1.3F, 0.0F);
		this.setColor(2900485);
		this.topBlock = Blocks.sand;
		this.fillerBlock = Blocks.sand;
		
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.enableRain = false;
		this.enableSnow = false;
		this.theBiomeDecorator.generateLakes = false;
		this.theBiomeDecorator.deadBushPerChunk = 6;
		this.theBiomeDecorator.grassPerChunk = 0;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.cactiPerChunk = 20;
		this.theBiomeDecorator.waterlilyPerChunk = 0;
	}
	
	@Override
	public void decorate(World worldIn, Random rand, int x, int z)
    {
        super.decorate(worldIn, rand, x, z);

        if (rand.nextInt(24) == 0)
        {
            int k = x + rand.nextInt(16) + 8;
            int l = z + rand.nextInt(16) + 8;
            WorldGenLakes worldgenlake = new WorldGenLakes(DDBlocks.tar);
            worldgenlake.generate(worldIn, rand, k, worldIn.getHeightValue(k, l), l);
        }
    }
}
