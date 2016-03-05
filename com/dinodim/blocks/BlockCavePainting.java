package com.dinodim.blocks;

import com.dinodim.main.DDMain;
import com.dinodim.main.DDUtils;
import com.dinodim.main.RefStrings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCavePainting extends Block 
{
	private IIcon[] sideIcons = new IIcon[9];

	protected BlockCavePainting() 
	{
		super(Material.rock);
		this.setBlockName("cave_painting").setCreativeTab(DDMain.tabMain);
		this.setResistance(1.0F).setHardness(2.2F).setStepSound(soundTypeStone);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		BlockDDPortal testPortal = new BlockDDPortal();
		int[][] locs = {{0,0,1},{0,1,0},{1,0,0},{0,0,-1},{0,-1,0},{-1,0,0}};
		for(int i = 0, j = locs.length; i < j; ++i)
		{
			int xPos = x + locs[i][0];
			int yPos = y + locs[i][1];
			int zPos = z + locs[i][2];
			if(testPortal.isFrameValid(worldIn, xPos, yPos, zPos))
			{
				return DDUtils.setBlockIfAir(worldIn, xPos, yPos, zPos, DDBlocks.dinoPortal);
			}
		}

		return false;
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World worldIn, int x, int y, int z) 
	{
		worldIn.setBlockMetadataWithNotify(x, y, z, worldIn.rand.nextInt(sideIcons.length), 2);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 || side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : this.sideIcons[meta % this.sideIcons.length];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		for(int i = 0, j = sideIcons.length; i < j; ++i)
		{
			this.sideIcons[i] = reg.registerIcon(RefStrings.MOD_ID + ":cave" + i);
		}
	}
}
