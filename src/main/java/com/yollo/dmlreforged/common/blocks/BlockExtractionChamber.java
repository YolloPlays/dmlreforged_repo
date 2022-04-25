package com.yollo.dmlreforged.common.blocks;

import com.yollo.dmlreforged.common.blocks.entity.BlockEntityExtractionChamber;
import com.yollo.dmlreforged.common.util.container.ExtractionChamberContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class BlockExtractionChamber extends HorizontalDirectionalBlock implements EntityBlock{
	
	public BlockExtractionChamber() {
		super(BlockBehaviour.Properties.of(Material.STONE).lightLevel((blockstate) -> {return 15;}).strength(4f, 10.0f));
		registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
			BlockEntityType<T> pBlockEntityType) {
		return EntityBlock.super.getTicker(pLevel, pState, pBlockEntityType);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new BlockEntityExtractionChamber(pPos, pState);
	}
	
	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
			BlockHitResult pHit) {
		if (!pLevel.isClientSide
				&& pLevel.getBlockEntity(pPos) instanceof final BlockEntityExtractionChamber generator) {
			final MenuProvider container = new SimpleMenuProvider(
					ExtractionChamberContainer.getServerContainer(generator, pPos), TextComponent.EMPTY);
			NetworkHooks.openGui((ServerPlayer) pPlayer, container);
		}
		return InteractionResult.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
		if (!pState.is(pNewState.getBlock())) {
			BlockEntity blockentity = pLevel.getBlockEntity(pPos);
			if (blockentity instanceof BlockEntityExtractionChamber extChamber) {
				System.out.println(!pState.is(pNewState.getBlock()));
				for (int index = 0; index < extChamber.inventory.getSlots(); index++) {
					if (!extChamber.inventory.getStackInSlot(index).isEmpty()) {
						pLevel.addFreshEntity(new ItemEntity(pLevel, pPos.getX() + 0.5D, pPos.getY() + 0.5D,
								pPos.getZ() + 0.5D, extChamber.inventory.getStackInSlot(index)));
					}
				}
			}
		}
		super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
	}
}