package org.gaem;

import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;

public class Camera {
	//TODO Add constants here
	//TODO Use IntRect (or FloatRect) for camera's box size.
	
	private View view = new View();

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	//TODO Use constants
	//TODO Use differential offset for x/y and upside/downside.
	//TODO Use center point instead left upside corner for adjustment.
	public void setActorPos(Vector2f position) {
		Vector2f center = view.getCenter();
		
		//100 - size of box, where player can move without camera adjustment
		if (position.x > center.x + 100) { 
			view.setCenter(new Vector2f(position.x - 100, view.getCenter().y));
		}
		if (position.x < center.x - 100) {
			view.setCenter(new Vector2f(position.x + 100, view.getCenter().y));
		}
		if (position.y > center.y + 100) { 
			view.setCenter(new Vector2f(view.getCenter().x, position.y - 100));
		}
		if (position.y < center.y - 100) {
			view.setCenter(new Vector2f(view.getCenter().x, position.y + 100));
		}
	}
}
