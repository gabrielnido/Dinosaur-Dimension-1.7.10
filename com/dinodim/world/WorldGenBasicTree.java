package com.dinodim.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBasicTree extends WorldGenAbstractTree
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;
    /** True if this tree should grow Vines. */
    private final boolean vinesGrow;
    private Block blockWood;
    private Block blockLeaf;
    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;
    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;

    public WorldGenBasicTree(Block woodBlock, Block leafBlock, int minHeight)
    {
        this(woodBlock, 0, leafBlock, 0, minHeight, false);
    }
    
    public WorldGenBasicTree(Block woodBlock, int woodMeta, Block leafBlock, int leafMeta, int minHeight, boolean doVinesGrow)
    {
    	super(true);
    	this.blockWood = woodBlock;
    	this.blockLeaf = leafBlock;
        this.metaWood = woodMeta;
        this.metaLeaves = leafMeta;
        this.minTreeHeight = minHeight;
        this.vinesGrow = doVinesGrow;
    }

    @Override
    public boolean generate(World worldIn, Random rand, int x, int y, int z)
    {
        int l = rand.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = worldIn.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(worldIn, j1, i1, k1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = worldIn.getBlock(x, y - 1, z);

                boolean isSoil = block2.canSustainPlant(worldIn, x, y - 1, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block2.onPlantGrow(worldIn, x, y - 1, z, x, y, z);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = y - b0 + l; k1 <= y + l; ++k1)
                    {
                        i3 = k1 - (y + l);
                        l1 = b1 + 1 - i3 / 2;

                        for (i2 = x - l1; i2 <= x + l1; ++i2)
                        {
                            j2 = i2 - x;

                            for (int k2 = z - l1; k2 <= z + l1; ++k2)
                            {
                                int l2 = k2 - z;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || rand.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = worldIn.getBlock(i2, k1, k2);

                                    if (block1.isAir(worldIn, i2, k1, k2) || block1.isLeaves(worldIn, i2, k1, k2))
                                    {
                                        this.setBlockAndNotifyAdequately(worldIn, i2, k1, k2, this.blockLeaf, this.metaLeaves);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = worldIn.getBlock(x, y + k1, z);

                        if (block.isAir(worldIn, x, y + k1, z) || block.isLeaves(worldIn, x, y + k1, z))
                        {
                            this.setBlockAndNotifyAdequately(worldIn, x, y + k1, z, this.blockWood, this.metaWood);

                            if (this.vinesGrow && k1 > 0)
                            {
                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(x - 1, y + k1, z))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, x - 1, y + k1, z, Blocks.vine, 8);
                                }

                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(x + 1, y + k1, z))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, x + 1, y + k1, z, Blocks.vine, 2);
                                }

                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(x, y + k1, z - 1))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, x, y + k1, z - 1, Blocks.vine, 1);
                                }

                                if (rand.nextInt(3) > 0 && worldIn.isAirBlock(x, y + k1, z + 1))
                                {
                                    this.setBlockAndNotifyAdequately(worldIn, x, y + k1, z + 1, Blocks.vine, 4);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        for (k1 = y - 3 + l; k1 <= y + l; ++k1)
                        {
                            i3 = k1 - (y + l);
                            l1 = 2 - i3 / 2;

                            for (i2 = x - l1; i2 <= x + l1; ++i2)
                            {
                                for (j2 = z - l1; j2 <= z + l1; ++j2)
                                {
                                    if (worldIn.getBlock(i2, k1, j2).isLeaves(worldIn, i2, k1, j2))
                                    {
                                        if (rand.nextInt(4) == 0 && worldIn.getBlock(i2 - 1, k1, j2).isAir(worldIn, i2 - 1, k1, j2))
                                        {
                                            this.growVines(worldIn, i2 - 1, k1, j2, 8);
                                        }

                                        if (rand.nextInt(4) == 0 && worldIn.getBlock(i2 + 1, k1, j2).isAir(worldIn, i2 + 1, k1, j2))
                                        {
                                            this.growVines(worldIn, i2 + 1, k1, j2, 2);
                                        }

                                        if (rand.nextInt(4) == 0 && worldIn.getBlock(i2, k1, j2 - 1).isAir(worldIn, i2, k1, j2 - 1))
                                        {
                                            this.growVines(worldIn, i2, k1, j2 - 1, 1);
                                        }

                                        if (rand.nextInt(4) == 0 && worldIn.getBlock(i2, k1, j2 + 1).isAir(worldIn, i2, k1, j2 + 1))
                                        {
                                            this.growVines(worldIn, i2, k1, j2 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

                        if (rand.nextInt(5) == 0 && l > 5)
                        {
                            for (k1 = 0; k1 < 2; ++k1)
                            {
                                for (i3 = 0; i3 < 4; ++i3)
                                {
                                    if (rand.nextInt(4 - k1) == 0)
                                    {
                                        l1 = rand.nextInt(3);
                                        this.setBlockAndNotifyAdequately(worldIn, x + Direction.offsetX[Direction.rotateOpposite[i3]], y + l - 5 + k1, z + Direction.offsetZ[Direction.rotateOpposite[i3]], Blocks.cocoa, l1 << 2 | i3);
                                    }
                                }
                            }
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
    private void growVines(World worldIn, int x, int startY, int z, int vineLen)
    {
        this.setBlockAndNotifyAdequately(worldIn, x, startY, z, Blocks.vine, vineLen);
        int i1 = 4;

        while (true)
        {
            --startY;

            if (!worldIn.getBlock(x, startY, z).isAir(worldIn, x, startY, z) || i1 <= 0)
            {
                return;
            }

            this.setBlockAndNotifyAdequately(worldIn, x, startY, z, Blocks.vine, vineLen);
            --i1;
        }
    }
}
