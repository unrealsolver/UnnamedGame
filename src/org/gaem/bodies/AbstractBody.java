package org.gaem.bodies;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

public abstract class AbstractBody implements Drawable{
	// ...:
	private RectangleShape boundingBox = new RectangleShape();
	{
		boundingBox.setOutlineColor(Color.WHITE);
		boundingBox.setOutlineThickness(2);
		boundingBox.setFillColor(Color.TRANSPARENT);
	}
	
	// Geometry:
	private Vector2f position;
	private Vector2f size;
	private Vector2f origin;
	
	// Behavior:
	private boolean killer;
	private boolean immortal;
	
	// Drawing:
	private boolean bounded;
	
	// Boilerplate:
	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
		boundingBox.setPosition(position);
	}
	
	public Vector2f getSize() {
		return size;
	}
	
	public void setSize(Vector2f size) {
		this.size = size;
		boundingBox.setSize(size);
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
	
	public boolean isBounded() {
		return bounded;
	}
	
	public void setBounded(boolean bounded) {
		this.bounded = bounded;
	}
	
	// Useful stuff:
	public void move (Vector2f vector) {
		//TODO: Implement overloaded Vector2f.add(Vector2f) //SHIT
		setPosition(Vector2f.add(position, vector));
	}
	
	//TODO SHIT
	public void move(float x, float y) {
		move(new Vector2f(x, y));
	}
	
	//TODO ?
	public boolean checkCollision(AbstractBody other) {
		FloatRect otherRect = new FloatRect(other.position, other.size);
		if (otherRect.contains(position)) {
			return true;
		}
		return false;
	}
	
	public void drawBoundingBox(RenderTarget target, RenderStates states) {
		if(bounded) {
			target.draw(boundingBox, states);
		}
	}
	
	// Interface:
	public abstract void update(float dt);

	//TODO: Just look
	//draw (RenderTarget, RenderStates) gets from Drawable
	public abstract void draw(RenderTarget target, RenderStates states);
}
