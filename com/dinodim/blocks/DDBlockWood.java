package com.dinodim.blocks;

import com.dinodim.main.DDMain;
import com.dinodim.main.RefStrings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DDBlockWood extends Block 
{
	private IIcon iconWoodTop;
	private IIcon iconWood;
	private String name;
	private boolean isPlanks;

	protected DDBlockWood(String name, boolean isPlank) 
	{
		super(Material.wood);
		this.isPlanks = isPlank;
		this.setIconName(name);
		this.setBlockName(name);
		this.setCreativeTab(DDMain.tabMain).setStepSound(soundTypeWood);
		this.setHardness(2.1F).setResistance(12.0F);
	}
	
	private void setIconName(String nameString)
	{
		this.name = RefStrings.MOD_ID + ":" + nameString;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) 
	{
		this.iconWood = reg.registerIcon(this.name);
		if(!this.isPlanks)
		{
			this.iconWoodTop = reg.registerIcon(this.name + "_top");
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) 
	{
		return !this.isPlanks && (side == 1 || side == 0) ? this.iconWoodTop : this.iconWood;
	}
	
	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        byte b0 = 4;
        int i1 = b0 + 1;

        if (p_149749_1_.checkChunksExist(p_149749_2_ - i1, p_149749_3_ - i1, p_149749_4_ - i1, p_149749_2_ + i1, p_149749_3_ + i1, p_149749_4_ + i1))
        {
            for (int j1 = -b0; j1 <= b0; ++j1)
            {
                for (int k1 = -b0; k1 <= b0; ++k1)
                {
                    for (int l1 = -b0; l1 <= b0; ++l1)
                    {
                        Block block = p_149749_1_.getBlock(p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        if (block.isLeaves(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1))
                        {
                            block.beginLeavesDecay(p_149749_1_, p_149749_2_ + j1, p_149749_3_ + k1, p_149749_4_ + l1);
                        }
                    }
                }
            }
        }
    }
	
	@Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
	
	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
	{
	    return true;
	}
}
