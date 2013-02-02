package org.gaem.bodies;

import org.gaem.ObjectManager;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Player extends TexturedBody {
	//FIXME СРАНЫЙ БАРДАК
	
	private ObjectManager objects;
	private Vector2f v = new Vector2f(0 ,0);
	private Vector2f a = new Vector2f(0, 10);
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
		this.objects = objects;
	}
	
	@Override
	public void update(float dt) {
		v = Vector2f.add(v, a);
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
	
	//TODO Move to super //wut?
	@Override
	public void move(float x, float y) {
		
		super.move(x, 0);
		Object other = objects.testCollision(this);
		if (other != null) {
			super.move(-x, 0);
			v = new Vector2f(0, v.y);
		}
		super.move(0, y);
		
		other = objects.testCollision(this);
		if (other != null) {
			//super.move(0, -y);
			Vector2f otherPos = ((AbstractBody) other).getPosition();
			if (v.y > 0) {
				//super.move(0, -y);
				super.move(0, otherPos.y - position.y - size.y);
			} else {
				super.move(0, -y);
			}
			v = new Vector2f(v.x, 0);
			isOnGround = true;
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
