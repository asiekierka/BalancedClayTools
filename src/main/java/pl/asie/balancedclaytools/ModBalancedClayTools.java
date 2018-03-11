/*
 * Copyright (c) 2018 Adrian Siekierka
 *
 * This file is part of BalancedClayTools.
 *
 * BalancedClayTools is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BalancedClayTools is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with BalancedClayTools.  If not, see <http://www.gnu.org/licenses/>.
 */

package pl.asie.balancedclaytools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = "balancedclaytools", name = "Balanced Clay Tools", version = "@VERSION@", dependencies = "after:preston")
public class ModBalancedClayTools {
	@SidedProxy(modId = "balancedclaytools", clientSide = "pl.asie.balancedclaytools.ProxyClient", serverSide = "pl.asie.balancedclaytools.ProxyCommon")
	public static ProxyCommon proxy;
	public static Item clayPickaxe, clayAxe, clayShovel;
	public static int prestonRecipeCompressionLevel;

	private Item prepare(Item toolItem, String name) {
		toolItem.setCreativeTab(CreativeTabs.TOOLS);
		toolItem.setRegistryName("balancedclaytools:" + name);
		toolItem.setUnlocalizedName(name);
		return toolItem;
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(proxy);

		clayPickaxe = prepare(new ItemPickaxeClay(), "clay_pickaxe");
		clayAxe = prepare(new ItemAxeClay(), "clay_axe");
		clayShovel = prepare(new ItemShovelClay(), "clay_shovel");

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		prestonRecipeCompressionLevel = config.getInt("recipeCompressionLevel", "general", 8, -1, 1000, "Set the level of compression for the clay blocks required for this item. If 0, uses vanilla clay (otherwise requires Preston!). If -1, the recipe is disabled.");

		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public void onRegisterItem(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(clayPickaxe, clayAxe, clayShovel);
	}

	private void createRecipe(Item result, String row1, String row2, String row3, ItemStack compressedStack, IForgeRegistry<IRecipe> registry) {
		IRecipe recipe = new ShapedOreRecipe(new ResourceLocation("balancedclaytools:clay_pickaxe"),
				result, row1, row2, row3, 'C', compressedStack, 'S', "stickWood").setMirrored(true);
		recipe.setRegistryName(result.getRegistryName());
		registry.register(recipe);
	}
	@SubscribeEvent
	public void onRegisterRecipe(RegistryEvent.Register<IRecipe> event) {
		if (prestonRecipeCompressionLevel >= 0) {
			ItemStack clayStack = new ItemStack(Blocks.CLAY, 1);
			ItemStack compressedStack = proxy.compressItem(clayStack, prestonRecipeCompressionLevel);

			if (!compressedStack.isEmpty()) {
				createRecipe(clayPickaxe, "CCC", " S ", " S ", compressedStack, event.getRegistry());
				createRecipe(clayAxe, "CC", "CS", " S", compressedStack, event.getRegistry());
				createRecipe(clayShovel, "C", "S", "S", compressedStack, event.getRegistry());
			}
		}
	}
}
