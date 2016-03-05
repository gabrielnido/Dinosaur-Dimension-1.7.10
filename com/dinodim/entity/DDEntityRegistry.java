package com.dinodim.entity;

import com.dinodim.main.DDUtils;

import net.minecraft.entity.Entity;

public class DDEntityRegistry 
{
	public static void mainRegistry()
	{
		registerEntities();
	}
	
	private static void registerEntities()
	{
		DDUtils.registerEntityAndEgg(EntityDimetrodon.class, "Dimetrodon", 0xede7b6, 0x33a04b);
		DDUtils.registerEntityAndEgg(EntityVRaptor.class, "Raptor", 0xede7b6, 0x99e27c);
		DDUtils.registerEntityAndEgg(EntityStego.class, "Stegosaurus", 0x89653d, 0xcec458);
		DDUtils.registerEntityAndEgg(EntitySauropod.class, "Sauropod", 0x89653d, 0x8c8c8c);
		DDUtils.registerEntityAndEgg(EntityPtero.class, "Pteranodon", 0xede7b6, 0xb51e1e);
		DDUtils.registerEntityAndEgg(EntityTricer.class, "Triceratops", 0x89653d, 0x564096);
		DDUtils.registerEntityAndEgg(EntityRex.class, "T-Rex", 0xede7b6, 0x4c4c77);
		DDUtils.registerEntityAndEgg(EntityTrilobite.class, "Trilobite", 0x6d6a52, 0x376dcc);
	}
}
