package com.dinodim.blocks;

import java.util.Random;

import com.dinodim.main.DDMain;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class DDBlockPlant extends BlockBush 
{
	public Random rand;
	private int renderType = 1;
	
	public DDBlockPlant() 
	{
		super(Material.plants);
		this.setHardness(0.0F);
		this.setCreativeTab(DDMain.tabMain);
	}
	
	/** returns block, for use in constructors */
	public Block setRenderType(int i)
	{
		this.renderType = i;
		return this;
	}
	
	/**
     * Returns the quantity of items to drop on block destruction.
     */
	@Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }
    
	@Override
	protected boolean canSilkHarvest()
    {
        return false;
    }
	
	/**
     * The type of render function that is called for this block
     */
	@Override
    public int getRenderType()
    {
        return this.renderType;
    }
	
	/**
     * Determines if a new block can be replace the space occupied by this one,
     * Used in the player's placement code to make the block act like water, and lava.
     *
     * @param world The current world
     * @param x X Position
     * @param y Y position
     * @param z Z position
     * @return True if the block is replaceable by another block
     */
	@Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}
