package com.dinodim.main;

import com.dinodim.blocks.DDBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DDCreativeTab extends CreativeTabs {
	
	public DDCreativeTab(String label) 
	{
		super(label);
	}

	@Override
	public Item getTabIconItem() 
	{
		return Item.getItemFromBlock(DDBlocks.flowerBulb);
	}

}
