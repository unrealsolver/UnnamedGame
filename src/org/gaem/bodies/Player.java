package org.gaem.bodies;

import org.gaem.ObjectManager;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Player extends TexturedBody {

	private ObjectManager objects;
	
	public Player(Vector2f position, Vector2f size, Texture texture) {
		super(position, size, texture);
	}
	
	public Player(Vector2f position, Texture texture) {
		super(position, texture);
	}
	
	public void setObjectManager(ObjectManager objects) {
		this.objects = objects;
	}
	
	@Override
	public void move(float x, float y) {
		move(new Vector2f(x, y));
		if (objects.testCollision(this)) {
			move(new Vector2f(-x, -y));
		}
		
	}

}
