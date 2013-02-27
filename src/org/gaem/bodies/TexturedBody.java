package org.gaem.bodies;

import org.jsfml.graphics.*;
import org.jsfml.system.*;

public class TexturedBody extends AbstractBody {
	protected Sprite sprite;
	
	public TexturedBody(Vector2f position, Vector2f size, Texture texture) {
		super(position, size);
		setTexture(texture);
	}
	
	public TexturedBody(Vector2f position, Vector2f size) {
		super(position, size);
		sprite = new Sprite();
	}
	
	public TexturedBody(Vector2f position, Texture texture) {
		super(position, new Vector2f());
		setTexture(texture);
		fitCanvasToTexture();
		scale(4);
		setBounded(false);
	}
	
	public void setTexture(Texture texture) {
		sprite = new Sprite(texture);
		sprite.setPosition(getPosition());
	}
	
	public void fitTextureToCanvas() {
		Vector2i textureSize = sprite.getTexture().getSize();
		sprite.setScale(new Vector2f(getSize().x/textureSize.x,
									 getSize().y/textureSize.y));
	}
	
	public void fitCanvasToTexture() {
		FloatRect rect = sprite.getLocalBounds();
		setSize(new Vector2f(rect.width, rect.height));
	}
	
	public void scaleImg(float amount) {
		sprite.setScale(new Vector2f(amount, amount));
	}
	
	public void scale(float amount) {
		sprite.setScale(new Vector2f(amount, amount));
		setSize(Vector2f.mul(getSize(), amount));
	}
	
	@Override
	public void move(float x, float y) {
		super.move(x, y); //shit <- super
		sprite.move(x, y);//gut <- SFML
	}
	
	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(sprite, states);
		drawBoundingBox(target, states);
	}

	@Override
	public void update(float dt) {
		fancyBeamUpdate(dt);
	}
}
