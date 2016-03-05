package com.dinodim.blocks;

import com.dinodim.main.RefStrings;

import net.minecraft.block.Block;

public class BlockFullDecorPlant extends DDBlockPlant
{
	public BlockFullDecorPlant(String name)
	{
		super();
		this.setBlockTextureName(RefStrings.MOD_ID + ":" + name);
		this.setBlockName(name);
		if(this.getRenderType() == 6)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		}
	}
}
