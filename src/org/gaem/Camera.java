package org.gaem;

import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;

public class Camera {
	private View view = new View();

	
	public View getView() {
		return view;
	}

	public void setView(View view) {

		this.view = view;
	}
	
	public void setActorPos(Vector2f position) {
		Vector2f center = view.getCenter();
		//Vector2f newPos = new Vector2f();
		
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
