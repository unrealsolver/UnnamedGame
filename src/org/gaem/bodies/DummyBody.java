package org.gaem.bodies;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class DummyBody extends AbstractBody {
	
	protected RectangleShape body;
	
	public DummyBody(Vector2f position, Vector2f size) {
		this.position = position;
		this.size = size;
		body = new RectangleShape(size);
		body.setPosition(position);
		body.setFillColor(new Color(180, 130,140,128));
		body.setOutlineColor(new Color(140, 255,100));
		body.setOutlineThickness(4);
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(body, states);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

}
