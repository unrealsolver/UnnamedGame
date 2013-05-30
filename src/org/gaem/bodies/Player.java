package org.gaem.bodies;

import org.gaem.ObjectManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.omg.CORBA.TRANSACTION_MODE;

public class Player extends TexturedBody {
	//FIXME СРАНЫЙ БАРДАК
	
	private Vector2f a = new Vector2f(0, 15);
	
	private boolean isRunning;
	private boolean isLocked;
	
	public Player(Vector2f position, Vector2f size, Texture texture) {
		super(position, size, texture);
	}
	
	public Player(Vector2f position, Texture texture) {
		super(position, new Vector2f(0, 0));
		collidable = true;
		setTexture(texture);
		fitCanvasToTexture();
	}
	
	public void lock() {
		isLocked = true;
		setOutlineColour(Color.WHITE);
		System.err.println("* Locked *");
		System.out.println("v: " + v);
		System.out.println("Collision: " + objectManager.getCollision(this));
		System.out.println("On ground: " + isOnGround);
	}
	
	public void unlock() {
		isLocked = false;
		setOutlineColour(Color.BLACK);
		System.err.println("* Unlocked *");
		System.out.println("v: " + v);
		System.out.println(isOnGround);
	}
	
	public void setObjectManager(ObjectManager objects) {
		this.objectManager = objects;
	}
	
	public void speedUp(float vx, float vy) {
		v = new Vector2f(vx + v.x, vy + v.y);
		isRunning = true;
	}
	
	public void run(float speed) {
		v = new Vector2f(speed, v.y);
		isRunning = true;
	}
	
	@Override
	public void update(float dt) {
		if (isLocked) {
			return;
		}
		v = Vector2f.add(v, a);
		move(v.x * dt, v.y * dt);
	}
	
	@Override
	public void move(float dx, float dy) {	
		super.move(dx, dy);
		sprite.setPosition(position.x, position.y);
	}
	
	public void jump() {
		if (isOnGround) {
			v = new Vector2f(v.x, v.y - 440);
			isOnGround = false;
		}
	}
}
