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

import net.minecraft.item.ItemStack;

public class ProxyCommonSuper {
	public ItemStack compressItem(ItemStack clayStack, int level) {
		return level == 0 ? clayStack : ItemStack.EMPTY;
	}
}
