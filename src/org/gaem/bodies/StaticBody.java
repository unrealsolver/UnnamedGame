package org.gaem.bodies;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

public class StaticBody extends AbstractBody {
	protected final static float DEFAULT_SCALE = 4;
	
	public StaticBody() {
		super();
	}
	
	public StaticBody(Vector2f position) {
		super(position);
	}
	
	public StaticBody(Vector2f position, Vector2f size) {
		super(position, size);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(RenderTarget target, RenderStates states) {
		drawBoundingBox(target, states);
	}

}
