package org.gaem.bodies;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class StaticTexturedBody extends StaticBody {

	Sprite sprite = new Sprite();
	
	public StaticTexturedBody() {
		super();
	}
	
	public StaticTexturedBody(Vector2f position) {
		super(position);
		sprite.setPosition(position);
	}
	
	public StaticTexturedBody(Vector2f position, Texture texture) {
		this(position);
		setTexture(texture);
	}
	
	public void move(float dx, float dy) {
		super.move(dx, dy);
		sprite.move(dx, dy);
	}
	
	public void move(Vector2f dd) {
		this.move(dd.x, dd.y);
	}
	
	public void setTexture(Texture texture) {
		sprite.setTexture(texture);
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		sprite.draw(target, states);
		super.draw(target, states);
	}
}
