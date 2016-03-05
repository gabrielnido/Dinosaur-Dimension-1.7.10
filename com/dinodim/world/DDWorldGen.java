package com.dinodim.world;

import java.util.Random;

import com.dinodim.blocks.DDBlocks;
import com.dinodim.dimension.BiomeGenDDJungle;
import com.dinodim.dimension.BiomeGenDDPlains;
import com.dinodim.dimension.DDDimensionRegistry;
import com.dinodim.main.DDUtils;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DDWorldGen implements IWorldGenerator {

	int CHUNKSIZE = 16;
	private WorldGenerator tallGrassGen;
	private WorldGenerator ddPlantGen;
	private WorldGenerator tallTreeGen;

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{	
		//allow features and ores to generate only in specific dimensions
		switch(world.provider.dimensionId) 
		{
		case -1: generateNether(rand, chunkX * CHUNKSIZE, chunkZ * CHUNKSIZE, world);
		break;
		case 0:	generateOverworld(rand, chunkX * CHUNKSIZE, chunkZ * CHUNKSIZE, world);
		break;
		case 1:	generateEnd(rand, chunkX * CHUNKSIZE, chunkZ * CHUNKSIZE, world);
		break;
		case DDDimensionRegistry.dimensionID: generateDinoDim(rand, chunkX * CHUNKSIZE, chunkZ * CHUNKSIZE, world);
		break;
		}
	}

	private void generateDinoDim(Random rand, int chunkX, int chunkZ, World worldIn) 
	{
		tallGrassGen = new WorldGenTallGrass();
		ddPlantGen = new WorldGenDDPlants();
		boolean genDDPlants = false;
		BiomeGenBase biome = worldIn.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
		if(biome instanceof BiomeGenDDPlains)
		{	
			addTallTreeGen(worldIn, rand, chunkX, chunkZ, 1);
			genDDPlants = true;
		}
		else if(biome instanceof BiomeGenDDJungle)
		{
			genDDPlants = true;
		}
		
		if(genDDPlants)
		{
			addBasicWorldGen(tallGrassGen, worldIn, rand, chunkX, chunkZ, 1);	
			addBasicWorldGen(ddPlantGen, worldIn, rand, chunkX, chunkZ, 1);
		}
	}

	private void generateOverworld(Random rand, int chunkX, int chunkZ, World worldIn) 
	{		
		for(int i = rand.nextInt(3); i < 4; i++) 
		{	
			int xPos = chunkX + rand.nextInt(CHUNKSIZE);		
			int zPos = chunkZ + rand.nextInt(CHUNKSIZE);
			int yPos = DDUtils.getFirstAirBlockFromBelow(worldIn, xPos, zPos);
			int size = 2 + rand.nextInt(4);
			if(yPos > 0 && yPos < worldIn.getHeightValue(xPos, zPos) - 1)
			{
				WorldGenCavePaintings.generate(worldIn, xPos, yPos, zPos, size, size * 2 + rand.nextInt(size * 2));
			}
		}
	}

	private void generateNether(Random rand, int chunkX, int chunkZ, World worldIn) 
	{

	}

	private void generateEnd(Random rand, int chunkX, int chunkZ, World worldIn)
	{	

	}

	private void addBasicWorldGen(WorldGenerator worldgen, World worldIn, Random rand, int chunkX, int chunkZ, int numToAdd)
	{
		if(numToAdd < 0) numToAdd = 0;

		for(int i = 0; i < numToAdd; ++i)
		{
			int xPos = chunkX + rand.nextInt(CHUNKSIZE);
			int zPos = chunkZ + rand.nextInt(CHUNKSIZE);
			int yPos = worldIn.getHeightValue(xPos, zPos);
			worldgen.generate(worldIn, rand, xPos, yPos, zPos);
		}
	}

	private void addTallTreeGen(World worldIn, Random rand, int chunkX, int chunkZ, int treesPerChunk)
	{
		Block[] treeBlocks = {DDBlocks.log1, Blocks.log};
		Block treeBlock = treeBlocks[rand.nextInt(treeBlocks.length)];
		int woodMeta = 0;
		if(rand.nextBoolean()) treesPerChunk += rand.nextInt(3);

		for(int i = 0; i < treesPerChunk; i++) 
		{
			treeBlock = treeBlocks[rand.nextInt(treeBlocks.length)];
			if(treeBlock == Blocks.log) 
			{
				woodMeta = 3;
			}
			this.tallTreeGen = new WorldGenBasicTree(treeBlock, woodMeta, Blocks.leaves, 3, 10, rand.nextBoolean());
			int xPos = chunkX + rand.nextInt(CHUNKSIZE);
			int zPos = chunkZ + rand.nextInt(CHUNKSIZE);
			int yPos = worldIn.getHeightValue(xPos, zPos);
			tallTreeGen.generate(worldIn, rand, xPos, yPos, zPos);
		}
	}

	/** Generates ores in the world
	 * @author sky01 
	 */
	private void addOreGen(Block block, Block blockToReplace, Random rand, World world, 
			int chunkX, int chunkZ, int minY, int maxY, int minVein, int maxVein, int numVeins) 
	{
		for(int i = 0; i < numVeins; i++) 
		{	
			int xPos = chunkX + rand.nextInt(CHUNKSIZE);
			int yPos = minY + rand.nextInt(maxY - minY);
			int zPos = chunkZ + rand.nextInt(CHUNKSIZE);
			int veinSize = minVein + rand.nextInt(maxVein - minVein);
			new WorldGenMinable(block, veinSize, blockToReplace).generate(world, rand, xPos, yPos, zPos);
		}
	}
}
