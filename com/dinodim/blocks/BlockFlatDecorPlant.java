package com.dinodim.blocks;

import com.dinodim.main.RefStrings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFlatDecorPlant extends DDBlockPlant
{
	private IIcon icon;
	private IIcon iconSide;
	
	public BlockFlatDecorPlant(String name)
	{
		super();
		this.setRenderType(0);
		this.setBlockTextureName(RefStrings.MOD_ID + ":" + name);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
		this.setBlockName(name);
	}
	
	/**
     * Gets the block's texture. Args: side, meta
     */
	@Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		return side == 1 || side == 6 ? this.icon : this.iconSide;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
		this.icon = reg.registerIcon(this.getTextureName());
		this.iconSide = reg.registerIcon(RefStrings.MOD_ID + ":BLANK");
    }
}
