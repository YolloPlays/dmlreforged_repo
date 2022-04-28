package com.yollo.dmlreforged.common.network;

import java.util.function.Supplier;

import com.yollo.dmlreforged.common.blocks.entity.BlockEntityExtractionChamber;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class ServerboundResultingItemPacket {

	public final ItemStack stack;
	public final BlockPos bPos;

    public ServerboundResultingItemPacket(BlockPos pos, ItemStack stack) {
        this.bPos = pos;
        this.stack =stack;
    }
    
    public ServerboundResultingItemPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos(), buffer.readItem());
    }
    
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.bPos);
        buffer.writeItem(stack);
    }
    
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            final BlockEntity blockEntity = ctx.get().getSender().level.getBlockEntity(this.bPos);
            if (blockEntity instanceof final BlockEntityExtractionChamber eCham) {
				eCham.setResultingItem(this.stack);
				eCham.update();
            }
        });
        
        ctx.get().setPacketHandled(true);
    }
}
