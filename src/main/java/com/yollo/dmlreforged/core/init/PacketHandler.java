package com.yollo.dmlreforged.core.init;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.network.ServerboundResultingItemPacket;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    
    private PacketHandler() {
    }

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(DeepMobLearning.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals);
	
	public static void init() {
		int index = 0;
		INSTANCE.messageBuilder(ServerboundResultingItemPacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
		.encoder(ServerboundResultingItemPacket::encode).decoder(ServerboundResultingItemPacket::new)
		.consumerNetworkThread(ServerboundResultingItemPacket::handle).add();
	}

}