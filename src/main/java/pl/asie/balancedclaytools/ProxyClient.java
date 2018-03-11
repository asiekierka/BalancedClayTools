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

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProxyClient extends ProxyCommon {
	@SubscribeEvent
	public void onModelRegistry(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(ModBalancedClayTools.clayPickaxe, 0, new ModelResourceLocation("balancedclaytools:clay_pickaxe", "inventory"));
		ModelLoader.setCustomModelResourceLocation(ModBalancedClayTools.clayAxe, 0, new ModelResourceLocation("balancedclaytools:clay_axe", "inventory"));
		ModelLoader.setCustomModelResourceLocation(ModBalancedClayTools.clayShovel, 0, new ModelResourceLocation("balancedclaytools:clay_shovel", "inventory"));
	}
}
