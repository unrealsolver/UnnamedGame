package org.gaem.bodies;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class DynamicBody extends StaticBody {

	public DynamicBody(Vector2f position) {
		super(position);
	}

	public DynamicBody() {
		super();
	}
	
	/*@Override
	public void update(float dt) {	
	}*/
	
	
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		super.draw(target, states);
	}
	
}
