package org.gaem.bodies;

import java.util.List;

import org.gaem.ObjectManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Player extends TexturedBody {
	//FIXME СРАНЫЙ БАРДАК
	
	private enum Pentration {
		LEFT, RIGHT, TOP, BOTTOM;
	}
	
	private ObjectManager objectManager;
	private Vector2f v = new Vector2f(0 ,0);
	private Vector2f a = new Vector2f(0, 15);
	private boolean isOnGround;
	private boolean isRunning;
	
	public Player(Vector2f position, Vector2f size, Texture texture) {
		super(position, size, texture);
	}
	
	public Player(Vector2f position, Texture texture) {
		super(position, new Vector2f());
		setTexture(texture);
		fitCanvasToTexture();
	}
	
	public void setObjectManager(ObjectManager objects) {
		this.objectManager = objects;
	}
	
	@Override
	public void update(float dt) {
		v = Vector2f.add(v, a);
		
		List<AbstractBody> objects =objectManager.getObjects();

		for (AbstractBody object : objects) {
			if (object.checkCollision(this)) {
				//object.fancyBeam();
				//object.setOutlineColour(Color.YELLOW);		
			} else {
				//object.setOutlineColour(Color.BLACK);	
			}
		}
		move(v.x * dt, v.y * dt);
	}
	
	public void speedUp(float vx, float vy) {
		v = new Vector2f(vx + v.x, vy + v.y);
		isRunning = true;
	}
	
	public void run(float speed) {
		v = new Vector2f(speed, v.y);
		isRunning = true;
	}
	
	//FIXME Use integer cords
	//TODO Move to super
	@Override
	public void move(float dx, float dy) {
		//FIXME !!! CHECK ALL COLLISIONS HERE !!!
		// DO NOT USE COLLISIONS LISTS!
		
		List<AbstractBody> objects =objectManager.getObjects();
		AbstractBody other;
				
		for (AbstractBody object : objects) {
			if (object.checkCollision(this)) {
				//Collision
				object.fancyBeam();
				if (dx > 0) {
					//super.move(object.getPosition().x - position.x - size.x, 0); //FIXME -1 - BUG 
				} else {
					//super.move(object.getPosition().x + object.getSize().x - position.x, 0); //FIXME +1 - BUG
				}
			}
		}
		
		super.move(dx, 0);
		other = objectManager.getCollision(this);
		if (other != null) {
			
			if (dx > 0) {
				//super.move(other.getPosition().x - position.x - size.x, 0); //FIXME -1 - BUG 
			} else if (dx < -1) {
				//super.move(other.getPosition().x + other.getSize().x - position.x, 0); //FIXME +1 - BUG
			}
		}
		
		super.move(0, dy);
		
		other = objectManager.getCollision(this);
		if (other != null) {
			//super.move(0, -y);
			other.fancyBeam();
			Vector2f otherPos = ((AbstractBody) other).getPosition();
			if (dy > 0) {
				super.move(0, -dy);
				isOnGround = true;
				super.move(0, otherPos.y - position.y - size.y);
			} else {
				super.move(0, otherPos.y + other.getSize().y - position.y);
			}
			v = new Vector2f(v.x, 0);
			
			if (!isRunning) {
				v = new Vector2f(0, 0);
			}
		}
		else {
			isOnGround = false;
		}
		isRunning = false;
	}

	public void jump() {
		if (isOnGround) {
			v = new Vector2f(v.x, v.y - 440);
		}
	}
}
