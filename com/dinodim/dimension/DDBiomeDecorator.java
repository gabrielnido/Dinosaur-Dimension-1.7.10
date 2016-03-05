package com.dinodim.dimension;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND_PASS2;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import com.dinodim.blocks.DDBlocks;
import com.dinodim.world.WorldGenBasicTree;
import com.dinodim.world.WorldGenDDPlants;
import com.dinodim.world.WorldGenTallGrass;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

/** I don't use this! */
@Deprecated
public class DDBiomeDecorator extends BiomeDecorator
{
    public WorldGenerator tallTreeGen;
	public WorldGenerator smallPlantGen;
	public WorldGenerator grassGen;
	public int melonPatchesPerChunk;
	public int smallPlantsPerChunk;
	public int grassClustersPerChunk;
	
	public DDBiomeDecorator()
	{
        this.tallTreeGen = new WorldGenBasicTree(DDBlocks.log1, 0, Blocks.leaves2, 0, 10, false);
        this.smallPlantGen = new WorldGenDDPlants();
        this.grassGen = new WorldGenTallGrass();
		this.melonPatchesPerChunk = 1;
		this.smallPlantsPerChunk = 8;
		this.grassClustersPerChunk = 6;
		this.treesPerChunk = 1;

	}
	
	protected void genNonPreciousOre(int numVeins, WorldGenerator worldgen, int chunkX, int chunkZ)
    {
        for (int l = 0; l < numVeins; ++l)
        {
            int i1 = this.chunk_X + this.randomGenerator.nextInt(16);
            int j1 = this.randomGenerator.nextInt(chunkZ - chunkX) + chunkX;
            int k1 = this.chunk_Z + this.randomGenerator.nextInt(16);
            worldgen.generate(this.currentWorld, this.randomGenerator, i1, j1, k1);
        }
    }
	
	/** Generates ores in the world
	 * @author sky01 
	 */
	protected void addOreGen(Block block, Block blockToReplace, Random rand, World world, 
	int chunkX, int chunkZ, int minY, int maxY, int minVein, int maxVein, int numVeins) 
	{
		for(int i = 0; i < numVeins; i++) 
		{	
			int xPos = chunkX + rand.nextInt(16);
			int yPos = minY + rand.nextInt(maxY - minY);
			int zPos = chunkZ + rand.nextInt(16);
			int veinSize = minVein + rand.nextInt(maxVein - minVein);
			new WorldGenMinable(block, veinSize, blockToReplace).generate(world, rand, xPos, yPos, zPos);
		}
	}

    /**
     * Generates ores in the current chunk
     */
	@Override
    protected void generateOres()
    {
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        this.genNonPreciousOre(20, this.dirtGen, 0, 256);
        this.genNonPreciousOre(10, this.gravelGen, 0, 256);
        this.addOreGen(Blocks.iron_ore, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 0, 64, 3, 10, 20);
        this.addOreGen(Blocks.gold_ore, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 0, 32, 3, 10, 4);
        this.addOreGen(Blocks.redstone_ore, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 0, 16, 1, 4, 6);
        this.addOreGen(Blocks.diamond_ore, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 0, 16, 1, 7, 2);
        this.addOreGen(Blocks.lapis_ore, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 0, 16, 2, 8, 4);
        this.addOreGen(Blocks.lapis_ore, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 16, 64, 1, 7, 4);
        this.addOreGen(Blocks.coal_ore, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 32, 128, 4, 16, 14);
        this.addOreGen(Blocks.coal_block, Blocks.stone, randomGenerator, currentWorld, chunk_X, chunk_Z, 0, 32, 8, 24, 4);
        MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }

	@Override
	protected void genDecorations(BiomeGenBase biome) 
	{
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
		
		this.generateOres();
        int i;
        int j;
        int k;

        boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND);
        for (i = 0; doGen && i < this.sandPerChunk2; ++i)
        {
            j = this.chunk_X + nextInt(16) + 8;
            k = this.chunk_Z + nextInt(16) + 8;
            this.sandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CLAY);
        for (i = 0; doGen && i < this.clayPerChunk; ++i)
        {
            j = this.chunk_X + nextInt(16) + 8;
            k = this.chunk_Z + nextInt(16) + 8;
            this.clayGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SAND_PASS2);
        for (i = 0; doGen && i < this.sandPerChunk; ++i)
        {
            j = this.chunk_X + nextInt(16) + 8;
            k = this.chunk_Z + nextInt(16) + 8;
            this.gravelAsSandGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }

        i = this.treesPerChunk;
        int l;
        int i1;

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, TREE);
        for (j = 0; j < this.treesPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            this.tallTreeGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, BIG_SHROOM);
        for (j = 0; doGen && j < this.bigMushroomsPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, k, this.currentWorld.getHeightValue(k, l), l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, FLOWERS);
        for (j = 0; doGen && j < this.flowersPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, k, this.currentWorld.getHeightValue(k, l), l);
        }
        for(j = 0; doGen && j < this.smallPlantsPerChunk; ++j)
        {
        	k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            this.smallPlantGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, GRASS);
        for (j = 0; doGen && j < this.grassClustersPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            grassGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DEAD_BUSH);
        for (j = 0; doGen && j < this.deadBushPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            (new WorldGenDeadBush(Blocks.deadbush)).generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LILYPAD);
        for (j = 0; doGen && j < this.waterlilyPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;

            for (i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2); i1 > 0 && this.currentWorld.isAirBlock(k, i1 - 1, l); --i1)
            {
                ;
            }

            this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, SHROOM);
        for (j = 0; doGen && j < this.mushroomsPerChunk; ++j)
        {
            if (this.randomGenerator.nextInt(4) == 0)
            {
                k = this.chunk_X + nextInt(16) + 8;
                l = this.chunk_Z + nextInt(16) + 8;
                i1 = this.currentWorld.getHeightValue(k, l);
                this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
            }

            if (this.randomGenerator.nextInt(8) == 0)
            {
                k = this.chunk_X + nextInt(16) + 8;
                l = this.chunk_Z + nextInt(16) + 8;
                i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
                this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
            }
        }

        if (doGen && this.randomGenerator.nextInt(4) == 0)
        {
            j = this.chunk_X + nextInt(16) + 8;
            k = this.chunk_Z + nextInt(16) + 8;
            l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
        }

        if (doGen && this.randomGenerator.nextInt(8) == 0)
        {
            j = this.chunk_X + nextInt(16) + 8;
            k = this.chunk_Z + nextInt(16) + 8;
            l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, j, l, k);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, REED);
        for (j = 0; doGen && j < this.reedsPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        for (j = 0; doGen && j < 10; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            this.reedGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, PUMPKIN) && this.randomGenerator.nextInt(32) == 0;
        for (j = 0; doGen && (j < this.melonPatchesPerChunk); j++)
        {
            j = this.chunk_X + nextInt(16) + 8;
            k = this.chunk_Z + nextInt(16) + 8;
            l = nextInt(this.currentWorld.getHeightValue(j, k) * 2);
            (new WorldGenMelon()).generate(this.currentWorld, this.randomGenerator, j, l, k);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, CACTUS);
        for (j = 0; doGen && j < this.cactiPerChunk; ++j)
        {
            k = this.chunk_X + nextInt(16) + 8;
            l = this.chunk_Z + nextInt(16) + 8;
            i1 = nextInt(this.currentWorld.getHeightValue(k, l) * 2);
            this.cactusGen.generate(this.currentWorld, this.randomGenerator, k, i1, l);
        }

        doGen = TerrainGen.decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, LAKE);
        if (doGen && this.generateLakes)
        {
            for (j = 0; j < 40; ++j)
            {
                k = this.chunk_X + nextInt(16) + 8;
                l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(248) + 8);
                i1 = this.chunk_Z + nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.flowing_water)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
            }

            for (j = 0; j < 30; ++j)
            {
                k = this.chunk_X + nextInt(16) + 8;
                l = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8) + 8);
                i1 = this.chunk_Z + nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.flowing_lava)).generate(this.currentWorld, this.randomGenerator, k, l, i1);
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
	}
	
	private int nextInt(int i) 
	{
        if (i <= 1)
            return 0;
        return this.randomGenerator.nextInt(i);
	}
}
