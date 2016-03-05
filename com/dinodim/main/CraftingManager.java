package com.dinodim.main;

import com.dinodim.blocks.DDBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingManager 
{
	public static void mainRegistry() 
	{
		addShapedCraftingRecipes();
		addShapelessCraftingRecipes();
		addSmeltingRecipes();
	}

	private static void addShapedCraftingRecipes() 
	{	
		// GameRegistry.addShapedRecipe(new ItemStack(output), new Object[]{input});
		// example:  GameRegistry.addShapedRecipe(new ItemStack(Items.stone_pickaxe, 1), new Object[]{"SSS"," l "," l ",'S',Blocks.stone,'l',Items.stick});
		GameRegistry.addShapedRecipe(new ItemStack(Items.stick, 4), new Object[]{" # "," # ",'#',DDBlocks.plank1});
		GameRegistry.addShapedRecipe(new ItemStack(Items.stick, 4),  new Object[]{"#","#",'#',DDBlocks.plank1});
	}
	
	private static void addShapelessCraftingRecipes() 
	{	
		// GameRegistry.addShapelessRecipe(new ItemStack(output), new Object[]{input});
		// example:  GameRegistry.addShapelessRecipe(new ItemStack(Blocks.grass, 1), new Object[]{Blocks.dirt,Items.wheat_seeds});
		GameRegistry.addShapelessRecipe(new ItemStack(DDBlocks.plank1, 4), new Object[]{DDBlocks.log1});
	}
	
	private static void addSmeltingRecipes()
	{
		// GameRegistry.addSmelting(input, new ItemStack(output), (float)xp_gain);
		// example:  GameRegistry.addSmelting(Blocks.coal_block, new ItemStack(Items.diamond, 1), 10.0F); 
	}
}
