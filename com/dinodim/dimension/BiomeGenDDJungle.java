package com.dinodim.dimension;

import java.util.Random;

import com.dinodim.entity.EntityDimetrodon;
import com.dinodim.entity.EntityPtero;
import com.dinodim.entity.EntityRex;
import com.dinodim.entity.EntitySauropod;
import com.dinodim.entity.EntityStego;
import com.dinodim.entity.EntityTricer;
import com.dinodim.entity.EntityTrilobite;
import com.dinodim.entity.EntityVRaptor;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenDDJungle extends BiomeGenBase
{
	private boolean isEdgeBiome;

	public BiomeGenDDJungle(int biomeId, boolean isEdge)
	{
		super(biomeId);
		this.setTemperatureRainfall(1.3F, 0.98F);
		this.setColor(2900485);
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.enableRain = true;
		this.theBiomeDecorator.generateLakes = true;
		
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityVRaptor.class, 12, 3, 6));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityDimetrodon.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityStego.class, 10, 3, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityTricer.class, 10, 3, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityPtero.class, 10, 4, 4));
		this.spawnableWaterCreatureList.add(new SpawnListEntry(EntityTrilobite.class, 8, 2, 6));
		
		this.theBiomeDecorator.treesPerChunk = isEdge ? 2 : 35;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.theBiomeDecorator.reedsPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 25;
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
    {
        return (WorldGenAbstractTree)(rand.nextInt(10) == 0 ? this.worldGeneratorBigTree : (rand.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (!this.isEdgeBiome && rand.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, 3, 3) : new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true))));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
    {
        return p_76730_1_.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : new WorldGenTallGrass(Blocks.tallgrass, 1);
    }

    @Override
    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
    {
        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
        int k = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
        int l = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
        int height = p_76728_1_.getHeightValue(k, l) * 2; //This was the original input for the nextInt below.  But it could == 0, which crashes nextInt
        if (height < 1) height = 1;
        int i1 = p_76728_2_.nextInt(height);
        (new WorldGenMelon()).generate(p_76728_1_, p_76728_2_, k, i1, l);
        WorldGenVines worldgenvines = new WorldGenVines();

        for (l = 0; l < 50; ++l)
        {
            i1 = p_76728_3_ + p_76728_2_.nextInt(16) + 8;
            short short1 = 128;
            int j1 = p_76728_4_ + p_76728_2_.nextInt(16) + 8;
            worldgenvines.generate(p_76728_1_, p_76728_2_, i1, short1, j1);
        }
    }
}
