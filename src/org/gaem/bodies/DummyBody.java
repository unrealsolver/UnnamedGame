package org.gaem.bodies;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class DummyBody extends AbstractBody {
	
	public DummyBody(Vector2f position, Vector2f size) {
		setPosition(position);
		setSize(size);
		setBounded(true);
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		drawBoundingBox(target, states);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
