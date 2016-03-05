package com.dinodim.items;

import com.dinodim.main.RefStrings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class DDItemMonsterPlacer extends Item
{
	public int colorBase;
	public int colorSpots;
	public String entityToSpawnName = "";
	protected String entityToSpawnNameFull = "";
	private IIcon theIcon;

	public DDItemMonsterPlacer(String name, int parColorBase, int parColorSpots)
	{
		this.setTextureName(RefStrings.MOD_ID + ":spawn_egg");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.maxStackSize = 64;
		entityToSpawnName = name;
		entityToSpawnNameFull = RefStrings.MOD_ID + "." + entityToSpawnName; 
		colorBase = parColorBase;
		colorSpots = parColorSpots;
	}
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (worldIn.isRemote)
        {
            return stack;
        }
        else
        {
            MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, player, true);

            if (movingobjectposition == null)
            {
                return stack;
            }
            else
            {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
                {
                    int i = movingobjectposition.blockX;
                    int j = movingobjectposition.blockY;
                    int k = movingobjectposition.blockZ;

                    if (!worldIn.canMineBlock(player, i, j, k))
                    {
                        return stack;
                    }

                    if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack))
                    {
                        return stack;
                    }

                    if (worldIn.getBlock(i, j, k) instanceof BlockLiquid)
                    {
                        Entity entity = spawnEntity(worldIn, (double)i, (double)j, (double)k);

                        if (entity != null)
                        {
                            if (entity instanceof EntityLivingBase && stack.hasDisplayName())
                            {
                                ((EntityLiving)entity).setCustomNameTag(stack.getDisplayName());
                            }

                            if (!player.capabilities.isCreativeMode)
                            {
                                --stack.stackSize;
                            }
                        }
                    }
                }

                return stack;
            }
        }
    }

	/**
	 * This is called when the item is used, before the block is activated.
	 * @param stack The Item Stack
	 * @param player The Player that used the item
	 * @param world The Current World
	 * @param x Target X Position
	 * @param y Target Y Position
	 * @param z Target Z Position
	 * @param side The side of the target hit
	 * @return Return true to prevent any further processing.
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World worldIn, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(worldIn.isRemote)
		{
			return true;
		}
		else
		{
			Block block = worldIn.getBlock(x, y, z);
            x += Facing.offsetsXForSide[side];
            y += Facing.offsetsYForSide[side];
            z += Facing.offsetsZForSide[side];
            
			Entity entity = spawnEntity(worldIn, (double)x + 0.5D, (double)y, (double)z + 0.5D);
			
			if(entity != null)
			{
				if (entity instanceof EntityLivingBase && stack.hasDisplayName())
				{
					((EntityLiving)entity).setCustomNameTag(stack.getDisplayName());
				}

				if(!player.capabilities.isCreativeMode)
				{
					--stack.stackSize;
				}
			}
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		return (renderPass == 0) ? colorBase : colorSpots;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return "Spawn " + entityToSpawnName;
	}  


	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		super.registerIcons(par1IconRegister);
		this.theIcon = par1IconRegister.registerIcon(this.getIconString() + "_overlay");
	}

	/**
	 * Gets an icon index based on an item's damage value and the given render pass
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 > 0 ? this.theIcon : super.getIconFromDamageForRenderPass(par1, par2);
	}

	protected Entity spawnEntity(World worldIn, double x, double y, double z)
	{
		Entity entity = EntityList.createEntityByName(entityToSpawnName, worldIn);
		
		if (entity != null && entity instanceof EntityLivingBase)
		{
			System.out.println("Successfully spawned " + entityToSpawnName);
			EntityLiving entityliving = (EntityLiving)entity;
			entity.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(worldIn.rand.nextFloat() * 360.0F), 0.0F);
			entityliving.rotationYawHead = entityliving.rotationYaw;
			entityliving.renderYawOffset = entityliving.rotationYaw;
			entityliving.onSpawnWithEgg((IEntityLivingData)null);
			worldIn.spawnEntityInWorld(entity);
			entityliving.playLivingSound();            
		}
		
		return entity;
	}
}
