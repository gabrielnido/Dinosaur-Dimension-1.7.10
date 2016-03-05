package com.dinodim.blocks;

import java.util.List;
import java.util.Random;

import com.dinodim.main.RefStrings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockStackableGrass extends DDBlockPlant
{
	private IIcon[] icons = new IIcon[2];
	/** How tall this plant can "grow" */
	private int maxHeight = 3;
			
	public BlockStackableGrass(String name) 
	{
		super();
		this.setBlockName(name);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
	
	/**
     * Gets the block's texture. Args: side, meta
     */
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		if(meta > 1)
		{
			meta = 1;
		}
		return this.icons[meta];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
		String s = RefStrings.MOD_ID + ":tall_grass";
        this.icons[0] = reg.registerIcon(s + "_top");
        this.icons[1] = reg.registerIcon(s + "_bottom");
    }
	
	@Override
	public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block block) 
	{
		this.updateTick(worldIn, x, y, z, rand);
	}
	
	/**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
    public int onBlockPlaced(World worldIn, int x, int y, int z, int side, float p_149660_6_, float p_149660_7_, float p_149660_8_, int meta)
    {
		if(worldIn.getBlock(x, y - 1, z) == this)
		{
			//metadata:  0 == top, 1 == bottom
			worldIn.setBlockMetadataWithNotify(x, y - 1, z, 1, 2);
		}
        return meta;
    }
	
	/**
     * Ticks the block if it's been scheduled
     */
	@Override
    public void updateTick(World worldIn, int x, int y, int z, Random rand) 
    {
		super.updateTick(worldIn, x, y, z, rand);
    	//metadata:  0 == top, 1 == bottom
		if(worldIn.getBlock(x, y + 1, z) == this)
		{
			worldIn.setBlockMetadataWithNotify(x, y, z, 1, 2);
		}
		else
		{
			worldIn.setBlockMetadataWithNotify(x, y, z, 0, 2);
		}
		
		
    }
	
	/**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
	@Override
    public boolean canPlaceBlockAt(World worldIn, int x, int y, int z)
    {
        return this.canSustainPlant(worldIn, x, y - 1, z, ForgeDirection.UP, this);
    }
	
	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
		Block block = world.getBlock(x, y, z);
        Block plant = plantable.getPlant(world, x, y + 1, z);

        if ((block == plant && this == plant) || this.canPlaceBlockOn(block))
        {
        	// limits sustainable height to #maxHeight
            return !(world.getBlock(x, y - (this.maxHeight - 1), z) == this);
        }

        return false;
    }
	
	/**
     * is the block grass, dirt or farmland
     */
	@Override
    protected boolean canPlaceBlockOn(Block b)
    {
        return b == Blocks.grass || b == Blocks.dirt || b == Blocks.farmland || b == this;
    }
    
    /**
     * The type of render function that is called for this block
     */
	@Override
    public int getRenderType()
    {
        return 1;
    }
	
	/**
     * checks if the block can stay, if not drop as item
     */
	@Override
    protected void checkAndDropBlock(World worldIn, int x, int y, int z)
    {
        if (!this.canBlockStay(worldIn, x, y, z))
        {
        	if(worldIn.getBlock(x, y + 1, z) == this)
        	{
        		worldIn.setBlock(x, y + 1, z, Blocks.air, 0, 3);
        		/*if(worldIn.getBlock(x, y + 2, z) == this)
            	{
            		worldIn.setBlock(x, y + 2, z, Blocks.air, 0, 3);
            	}*/
        	}

        	worldIn.setBlock(x, y, z, Blocks.air, 0, 2);
        }
    }
}
