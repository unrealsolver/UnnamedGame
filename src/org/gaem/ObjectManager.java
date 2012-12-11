package org.gaem;

import java.util.ArrayList;
import java.util.List;

import org.gaem.bodies.AbstractBody;
import org.jsfml.graphics.RenderTarget;

public class ObjectManager {
// Very important.
// Not loss.
	private List<AbstractBody> objects = new ArrayList<AbstractBody>();
	RenderTarget renderTarget;
	
	public ObjectManager(RenderTarget renderTarget) {
		super();
		setRenderTarget(renderTarget);
	}
	
	public void setRenderTarget(RenderTarget renderTarget) {
		this.renderTarget = renderTarget;
	}
	
	public void add(AbstractBody body) {
		objects.add(body);
	}
	
	public boolean testCollision(AbstractBody body) {
		for(AbstractBody object : objects) {
			if (object != body) {
				if (body.checkCollision(object)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void drawAll() {
		for(AbstractBody object : objects) {
			renderTarget.draw(object);
		}
	}
	
	public void updateAll(float dt) {
		for(AbstractBody object : objects) {
			object.update(dt);
		}
	}
}
