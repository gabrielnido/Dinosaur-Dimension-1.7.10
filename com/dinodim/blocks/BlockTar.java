package com.dinodim.blocks;

import java.util.Random;

import com.dinodim.main.DDMain;
import com.dinodim.main.RefStrings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTar extends Block
{		
	public BlockTar()
	{
		super(Material.web);
		this.setBlockName("tar").setBlockTextureName(RefStrings.MOD_ID + ":tar").setCreativeTab(DDMain.tabMain);
		this.setHardness(16F).setResistance(0.1F).setHarvestLevel("shovel", 0);
		this.setTickRandomly(true);
	}
	
	/**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
	@Override
    public void onEntityCollidedWithBlock(World worldIn, int x, int y, int z, Entity entity)
    {
        entity.setInWeb();
    }
	
	/**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
	
	/**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public void updateTick(World worldIn, int x, int y, int z, Random rand)
    {
    	if(worldIn.isAirBlock(x, y - 1, z))
		{
    		worldIn.setBlock(x, y - 1, z, this);
    		worldIn.setBlockToAir(x, y, z);
		}
    }
	
	/**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }
}
