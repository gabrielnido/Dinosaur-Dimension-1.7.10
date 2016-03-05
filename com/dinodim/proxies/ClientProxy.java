package com.dinodim.proxies;

import com.dinodim.entity.EntityDimetrodon;
import com.dinodim.entity.EntityPtero;
import com.dinodim.entity.EntityRex;
import com.dinodim.entity.EntitySauropod;
import com.dinodim.entity.EntityStego;
import com.dinodim.entity.EntityTricer;
import com.dinodim.entity.EntityTrilobite;
import com.dinodim.entity.EntityVRaptor;
import com.dinodim.renders.RenderDimetrodon;
import com.dinodim.renders.RenderPtery;
import com.dinodim.renders.RenderRex;
import com.dinodim.renders.RenderSauropod;
import com.dinodim.renders.RenderStego;
import com.dinodim.renders.RenderTricer;
import com.dinodim.renders.RenderTrilobite;
import com.dinodim.renders.RenderVRaptor;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{	
	/**
	 * This is used mainly for rendering entities, which is always client-side only
	 */
	@Override
	public void registerRenders()
	{			
		RenderingRegistry.registerEntityRenderingHandler(EntityDimetrodon.class, new RenderDimetrodon());
		RenderingRegistry.registerEntityRenderingHandler(EntityVRaptor.class, new RenderVRaptor());
		RenderingRegistry.registerEntityRenderingHandler(EntityStego.class, new RenderStego());
		RenderingRegistry.registerEntityRenderingHandler(EntitySauropod.class, new RenderSauropod());
		RenderingRegistry.registerEntityRenderingHandler(EntityPtero.class, new RenderPtery());
		RenderingRegistry.registerEntityRenderingHandler(EntityTricer.class, new RenderTricer());
		RenderingRegistry.registerEntityRenderingHandler(EntityRex.class, new RenderRex());
		RenderingRegistry.registerEntityRenderingHandler(EntityTrilobite.class, new RenderTrilobite());
	}
}
