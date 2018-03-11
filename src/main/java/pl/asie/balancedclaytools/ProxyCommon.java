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

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import pl.asie.preston.api.PrestonAPI;

import java.util.Random;

public class ProxyCommon extends ProxyCommonSuper {
	private final Random random = new Random();

	@Optional.Method(modid = "preston")
	@Override
	public ItemStack compressItem(ItemStack clayStack, int level) {
		return PrestonAPI.setCompressionLevel(clayStack, level);
	}

	public void onBlockDestroyed(ItemStack stack, World worldIn, ItemStack stack1, BlockPos pos, EntityLivingBase entityLiving) {
		if (random.nextInt(500000) == 2 && !worldIn.isRemote) {
			EntityItem entityItem = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.CLAY_BALL));
			worldIn.spawnEntity(entityItem);
		}
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if (entity instanceof EntityCreeper && player.world.isRemote) {
			player.getEntityWorld().playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_CREEPER_PRIMED, SoundCategory.HOSTILE, 1.0F, 0.5F, false);
		}
		return true;
	}
}
