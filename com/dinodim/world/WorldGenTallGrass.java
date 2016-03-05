package com.dinodim.world;

import java.util.Random;

import com.dinodim.blocks.DDBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTallGrass extends WorldGenerator
{	
	@Override
	public boolean generate(World worldIn, Random rand, int startX, int startY, int startZ) 
	{
		int x;
		int y;
		int z;
		int h;
		Block curBlock;
		
		for (int l = 0; l < 64; ++l)
        {
            x = startX + rand.nextInt(16) - rand.nextInt(16);
            z = startZ + rand.nextInt(16) - rand.nextInt(16);
            y = worldIn.getHeightValue(x, z);
            h = 2 + rand.nextInt(2);

            for(int i = 0; i < h; i++)
            {
            	curBlock = worldIn.getBlock(x, y + i, z);
            	if ((curBlock == Blocks.air || curBlock.getMaterial() == Material.plants || curBlock == Blocks.double_plant) && (!worldIn.provider.hasNoSky || y < 252) && DDBlocks.grassTall.canPlaceBlockAt(worldIn, x, y + i, z))
            	{
            		worldIn.setBlock(x, y + i, z, DDBlocks.grassTall);
            	}
            }
        }
      
		return true;
	}
}
