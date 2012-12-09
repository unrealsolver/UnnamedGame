package org.gaem.bodies;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

public abstract class AbstractBody implements Drawable{
	// Geometry:
	protected Vector2f position;
	protected Vector2f size;
	private Vector2f origin;
	
	// Behavior:
	private boolean killer;
	private boolean immortal;
	
	// Boilerplate:
	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public void setSize(Vector2f size) {
		this.size = size;
	}
	
	public Vector2f getOrigin() {
		return origin;
	}
	
	public void setOrigin(Vector2f origin) {
		this.origin = origin;
	}
	
	public boolean isKiller() {
		return killer;
	}
	
	public void setKiller(boolean killer) {
		this.killer = killer;
	}
	
	public boolean isImmortal() {
		return immortal;
	}
	
	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}
	
	// Useful stuff:
	public void move (Vector2f vector) {
		//TODO: Implement overloaded Vector2f.add(Vector2f)
		position = Vector2f.add(position, vector);
	}
	
	// Interface:
	public abstract void update(float dt);

	//TODO: Just look
	//draw (RenderTarget, RenderStates) gets from Drawable
	public abstract void draw(RenderTarget target, RenderStates states);
}
