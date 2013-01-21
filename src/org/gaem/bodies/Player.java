package org.gaem.bodies;

import org.gaem.ObjectManager;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.gaem.bodies.AbstractBody;

public class Player extends TexturedBody {
	//FIXME СРАНЫЙ БАРДАК
	Texture mirror = new Texture();
	Texture normal = new Texture();
	
	private ObjectManager objects;
	private Vector2f v = new Vector2f(0 ,0);
	private Vector2f a = new Vector2f(0, 15);
	private boolean isOnGround;
	private boolean isRunning;
	
	public Player(Vector2f position, Vector2f size, Texture texture) {
		super(position, size, texture);
	}
	
	public Player(Vector2f position, Texture texture) {
		super(position, new Vector2f());
		//setPosition(position);
		setTexture(texture);
		fitCanvasToTexture();
		//super(position, texture);
	}
	
	public void setObjectManager(ObjectManager objects) {
		this.objects = objects;
	}
	
	public void setMirrorTexture(Texture texture) {
		mirror = texture;
	}
	
	public void setNormalTexture(Texture texture) {
		normal = texture;
	}
	
	@Override
	public void update(float dt) {
		v = Vector2f.add(v, a);
		move(v.x * dt, v.y * dt);
		
		if (v.x > 0) {
			setTexture(normal);
			scaleImg(2);
		}
		
		if (v.x < 0) {
			setTexture(mirror);
			scaleImg(2);
		}
	}
	
	public void speedUp(float vx, float vy) {
		v = new Vector2f(vx + v.x, vy + v.y);
		isRunning = true;
	}
	
	public void run(float speed) {
		v = new Vector2f(speed, v.y);
		isRunning = true;
	}
	
	//TODO Move to super
	@Override
	public void move(float x, float y) {
		
		super.move(x, 0);
		if (objects.testCollision(this)) {
			super.move(-x, 0);
			v = new Vector2f(0, v.y);
		}
		super.move(0, y);
		if (objects.testCollision(this)) {
			super.move(0, -y);
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
