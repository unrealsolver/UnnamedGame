package org.gaem.bodies;

import java.io.File;

import org.jsfml.graphics.*;
import org.jsfml.system.*;

public class TexturedBody extends DummyBody {
	private Sprite sprite;
	
	public TexturedBody(Vector2f position, Vector2f size, Texture texture) {
		super(position, size);
		sprite = new Sprite(texture);
		sprite.setPosition(position);
		Vector2i textureSize = texture.getSize();
		sprite.setScale(Vector2f.div(size, textureSize.x));
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(super.body, states);
		target.draw(sprite, states);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
