package org.gaem;

import org.gaem.bodies.AbstractBody;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

public class VisualProfiler extends AbstractBody {
	private long drawTime;
	private long updateTime;
	
	private RectangleShape bar = new RectangleShape();
	private static Color drawColor = new Color(255, 100, 60);
	private static Color updateColor = new Color(100, 255, 60);
	private static int barHeight = 10;
	private static float barLengthMultipler = 1;

	public void setDrawTime(long drawTime) {
		this.drawTime = drawTime;
	}


	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public void draw(RenderTarget target, RenderStates states) {
		bar.setFillColor(drawColor);
		bar.setPosition(position);
		bar.setSize(new Vector2f(drawTime * barLengthMultipler, barHeight));
		target.draw(bar);
		bar.setFillColor(updateColor);
		bar.setPosition(Vector2f.add(position, new Vector2f(drawTime * barLengthMultipler, 0)));
		bar.setSize(new Vector2f(updateTime * barLengthMultipler, barHeight));
		target.draw(bar);
	}


	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
}
