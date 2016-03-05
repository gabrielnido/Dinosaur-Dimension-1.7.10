package com.dinodim.world;

import java.util.Random;

import com.dinodim.blocks.DDBlocks;
import com.dinodim.main.DDUtils;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCavePaintings extends WorldGenerator
{
	public static boolean generate(World worldIn, int startX, int baseY, int startZ, int radius, int numToAdd)
	{
		int posX;
		int posY;
		int posZ;
		
		for (int i = 0; i < numToAdd; ++i)
        {
            posX = startX + worldIn.rand.nextInt(radius) - worldIn.rand.nextInt(radius);
            posY = baseY + worldIn.rand.nextInt(radius);
            posZ = startZ + worldIn.rand.nextInt(radius) - worldIn.rand.nextInt(radius);
            if(worldIn.getBlock(posX, posY, posZ) == Blocks.stone && DDUtils.isBlockTouchingAir(worldIn, posX, posY, posZ))
			{
				worldIn.setBlock(posX, posY, posZ, DDBlocks.caveBlock);
			}
        }	
		return true;
	}

	@Override
	public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) 
	{
		return false;
	}
}
