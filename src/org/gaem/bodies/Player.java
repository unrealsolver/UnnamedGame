package org.gaem.bodies;

import org.gaem.ObjectManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Player extends TexturedBody {
	//FIXME СРАНЫЙ БАРДАК
	
	private ObjectManager objectManager;
	private Vector2f v = new Vector2f(0 ,0);
	private Vector2f a = new Vector2f(0, 15);
	private boolean isOnGround;
	private boolean isRunning;
	private boolean isLocked;
	
	public Player(Vector2f position, Vector2f size, Texture texture) {
		super(position, size, texture);
	}
	
	public Player(Vector2f position, Texture texture) {
		super(position, new Vector2f());
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
	
	//FIXME Use integer cords. May be.
	//TODO Move to super
	@Override
	public void move(float dx, float dy) {	
		float abs_r = (float) Math.sqrt(dx*dx + dy*dy);
		Vector2f tr = new Vector2f(0, 0);
		Vector2f ir = new Vector2f(dx/abs_r, dy/abs_r);
		
		// Pre-check
		if (objectManager.getCollision(this) != null) {
			System.err.println("Unresolved collision!");
		}
		
		// Approximation loop
		while (Math.abs(tr.x) <= Math.abs(dx) && Math.abs(tr.y) <= Math.abs(dy)) {
			// Next step
			super.move(ir.x, ir.y);
			tr = Vector2f.add(tr, ir);
			
			if (objectManager.getCollision(this) != null) {
				isOnGround = false;
				// step backwards
				super.move(-ir.x, -ir.y);
				tr = Vector2f.sub(tr, ir);

				// try to move DOWN
				super.move(0, ir.y);
				
				// Check collision
				boolean collision = objectManager.getCollision(this) != null;
				
				// Return (UP)
				super.move(0, -ir.y);
				
				if (collision) {
					// Bottom collision
					isOnGround = true;
					// stop moving
					v = new Vector2f(v.x,0);
					ir = new Vector2f(ir.x, 0);
				} else {
					// Non-bottom collision
					isOnGround = false;
					
					// try to move LEFT
					super.move(-1, 0);
					// Check collision
					collision = objectManager.getCollision(this) != null;
					
					// Return (RIGHT)
					super.move(1, 0);
					
					if (collision) {
						// Left collision
						// stop moving
						v = new Vector2f(0, v.y);
						ir = new Vector2f(0, ir.y);
					} else {
						// try to move RIGHT
						super.move(1, 0);
						
						// Check collision
						collision = collision | (objectManager.getCollision(this) != null);
						
						// Return (LEFT)
						super.move(-1, 0);
						
						if (collision) {
							// Left collision
							// stop moving
							v = new Vector2f(0, v.y);
							ir = new Vector2f(0, ir.y);
						}
					}
				}
			}
			
			// Exiting from loop if no moving
			if (ir.x == 0 && ir.y == 0) {
				break;
			}
		}
		
	}
	
	public void jump() {
		if (isOnGround) {
			v = new Vector2f(v.x, v.y - 440);
			isOnGround = false;
		}
	}
}
