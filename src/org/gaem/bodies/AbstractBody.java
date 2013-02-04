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
		boundingBox.setOutlineColor(Color.BLACK);
		boundingBox.setOutlineThickness(-2);
		boundingBox.setFillColor(Color.TRANSPARENT);
		setBounded(true);
	}
	
	// Geometry:
	protected Vector2f position;
	protected Vector2f size;
	protected Vector2f origin;
	
	// Behavior:
	private boolean killer;
	private boolean immortal;
	
	// Drawing:
	private boolean bounded;
	
	// For fun:
	private int colourRG = 255;
	private int colourSpd = 370;
	
	public AbstractBody() {
		setPosition(new Vector2f(0, 0));
		setSize(new Vector2f(10, 10));
	}
	
	public AbstractBody(Vector2f position) {
		setPosition(position);
		setSize(new Vector2f(10, 10));
	}
	
	public AbstractBody(Vector2f position, Vector2f size) {
		setPosition(position);
		setSize(size);
	}
	
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
	
	public void setSize(float x, float y) {
		this.size = new Vector2f(x, y);
		boundingBox.setSize(size);
	}
	
	public void setSize(Vector2f size) {
		this.setSize(size.x, size.y);
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
	
	public void setOutlineColour(Color colour) {
		boundingBox.setOutlineColor(colour);
	}
	
	//Just for fun:
	public void fancyBeam() {
		colourRG = 255;
	}
	
	public void fancyBeamUpdate (float dt) {
		colourRG -= (int) colourSpd * dt;
		if (colourRG < 0) {
			colourRG = 0;
		}
		boundingBox.setOutlineColor(new Color(colourRG, colourRG/4, colourRG/6));
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
	
	public boolean checkCollision(AbstractBody other) {
		float dxPos = this.position.x - other.position.x;
		if ((this.size.x > -dxPos) && (other.size.x >= dxPos)) {
			float dyPos = this.position.y - other.position.y;
			if ((this.size.y > -dyPos) && (other.size.y >= dyPos)) {
				return true;
			}
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
