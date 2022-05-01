package com.yollo.dmlreforged.core.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class LivingExpConfig {
	
	public static ForgeConfigSpec.IntValue LIVING_MATTER_OVERWORLDIAN; //10;
	public static ForgeConfigSpec.IntValue LIVING_MATTER_HELLISH; //14;
	public static ForgeConfigSpec.IntValue LIVING_MATTER_EXTRATERRESTRIAL; //20;
	
	public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
		SERVER_BUILDER.comment("Balance the EXP received from using Living Matter Item").push("living_matter");
		LIVING_MATTER_OVERWORLDIAN = SERVER_BUILDER.defineInRange("livingOverworldian", 10, 0, 999);
		LIVING_MATTER_HELLISH = SERVER_BUILDER.defineInRange("livingHellish", 14, 0, 999);
		LIVING_MATTER_EXTRATERRESTRIAL = SERVER_BUILDER.defineInRange("livingExtraterrestrial", 20, 0, 999);
		SERVER_BUILDER.pop();
	}
	

    public static final int getExp(String type) {
    	if(type == "living_matter_extraterrestrial") {
    		return LIVING_MATTER_EXTRATERRESTRIAL.get();
    	}
    	else if(type == "living_matter_hellish") {
    		return LIVING_MATTER_HELLISH.get();
    	}
    	else if(type == "living_matter_overworldian"){
    		return LIVING_MATTER_OVERWORLDIAN.get();
    	}
    	else {
    		return 0;
    	}
    }
    

}
