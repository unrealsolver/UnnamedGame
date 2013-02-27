package org.gaem.bodies;

import org.gaem.ObjectManager;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.omg.CORBA.TRANSACTION_MODE;

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
		boolean collides; 
		
		// Pre-check
		if (objectManager.getCollision(this) != null) {
			System.err.println("Unresolved collision!");
		}
		
		// Approximation loop
		while (Math.abs(tr.x) <= Math.abs(dx) && Math.abs(tr.y) <= Math.abs(dy)) {
			//step
			super.move(ir.x, ir.y);
			tr = Vector2f.add(ir, tr);
			
			if (objectManager.getCollision(this) != null) {
				//step back
				super.move(-ir.x, -ir.y);
				tr = Vector2f.sub(ir, tr); //Is it necessary?
				
				if (ir.x != 0) {
					// Move over X-axis
					super.move(ir.x, 0);
					//Test collision
					collides = objectManager.getCollision(this) != null?true:false;
					// Return after moving
					super.move(-ir.x, 0);
					
					if(collides) {
						//X collision!
						//resolve X collision
						v = new Vector2f(0, v.y);
						ir = new Vector2f(0, ir.y);
						//break;
					}
					
				}
				
				if (ir.y != 0) {
					// Move over Y-axis
					super.move(0, ir.y);
					//Test collision
					collides = objectManager.getCollision(this) != null?true:false;
					// Return after moving
					super.move(0, -ir.y);
					
					if(collides) {
						//Y collision!
						//resolve Y collision
						if (ir.y > 0) {
							//FIXME Bug: indirect collision will not work!
							isOnGround = true;
							v = new Vector2f(v.x/2f, v.y);
							//ir = new Vector2f(0, ir.y);
						}
						v = new Vector2f(v.x, 0);
						ir = new Vector2f(ir.x, 0);
						//break;
					}
				}
				
				if (ir.x == 0 && ir.y == 0) {
					break;
				}
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
