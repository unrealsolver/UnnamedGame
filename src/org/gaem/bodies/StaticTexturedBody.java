package org.gaem.bodies;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class StaticTexturedBody extends StaticBody {

	private Sprite sprite = new Sprite();
	
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
		fitSizeToSprite();
		setScale(DEFAULT_SCALE);	
	}
	
	public void move(float dx, float dy) {
		super.move(dx, dy);
		sprite.move(dx, dy);
	}
	
	public void move(Vector2f dd) {
		this.move(dd.x, dd.y);
	}
	
	public void setScale(float scale) {
		this.setScale(scale, scale);
	}
	
	public void setScale(float scaleX, float scaleY) {
		Vector2f size = getSize();
		setSize(size.x * scaleX, size.y * scaleY);
		sprite.scale(new Vector2f(scaleX, scaleY));
	}
	
	public void setTexture(Texture texture) {
		sprite.setTexture(texture);
	}
	
	public void fitSizeToSprite() {
		FloatRect spriteRect = sprite.getLocalBounds();
		Vector2f spriteSize = new Vector2f(spriteRect.width, spriteRect.height);
		setSize(spriteSize.x, spriteSize.y);
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		sprite.draw(target, states);
		super.draw(target, states);
	}
}
