package com.dinodim.world;

import java.util.Random;

import com.dinodim.blocks.DDBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDDPlants extends WorldGenerator {

	private Block[] plantArray = 
	{DDBlocks.plantFlat1,DDBlocks.plantFlat2,DDBlocks.plantFlat3,
	 DDBlocks.flowerBlue,DDBlocks.flowerBulb,DDBlocks.flowerPurple,
	 DDBlocks.flowerWhite,DDBlocks.grassShort};

	@Override
	public boolean generate(World worldIn, Random rand, int startX, int startY, int startZ) 
	{
		int x;
		int y;
		int z;
		Block plant;
		Block curBlock;
		
		for (int l = 0; l < 32; ++l)
        {
            x = startX + rand.nextInt(16) - rand.nextInt(16);
            z = startZ + rand.nextInt(16) - rand.nextInt(16);
            y = worldIn.getHeightValue(x, z);
            curBlock = worldIn.getBlock(x, y, z);
            plant = this.getRandomPlant(rand);

            if(plant == DDBlocks.plantFlat2)
            {
             	this.addCloverPatch(worldIn, rand, x, y, z);
            }
            else if(this.canPlacePlantAt(worldIn, x, y, z))
            {
              	worldIn.setBlock(x, y, z, plant);
            }
        }
        
		return true;
	}

	private boolean isBlockReplaceable(Block b) {
		return b == Blocks.air || (b.getMaterial() == Material.plants && b != DDBlocks.grassTall);
	}

	private Block getRandomPlant(Random rand) 
	{
		return this.plantArray[rand.nextInt(this.plantArray.length)];
	}
	
	private boolean canPlacePlantAt(World worldIn, int x, int y, int z)
	{
		return this.isBlockReplaceable(worldIn.getBlock(x, y, z)) && (!worldIn.provider.hasNoSky || y < 255) && DDBlocks.flowerBlue.canBlockStay(worldIn, x, y, z);
	}
	
	private void addCloverPatch(World worldIn, Random rand, int xPos, int yPos, int zPos)
	{
		for (int i = 0; i < 16; ++i)
        {
			int x = xPos + rand.nextInt(3) - rand.nextInt(3);
			int z = zPos + rand.nextInt(3) - rand.nextInt(3);
			int y = worldIn.getHeightValue(x, z);

			if (rand.nextBoolean() && this.canPlacePlantAt(worldIn, x, y, z))
			{
				worldIn.setBlock(x, y, z, DDBlocks.plantFlat2);
			}
        }
	}

}
