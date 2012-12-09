import java.io.File;
import java.io.IOException;

import org.gaem.bodies.AbstractBody;
import org.gaem.bodies.DummyBody;
import org.gaem.bodies.TexturedBody;
import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;

class Runner {
	
	private class Entity {
		
	}
	
	public static void main(String[] args) {
		RenderWindow window = new RenderWindow();
		window.create(new VideoMode(640, 480), "Hello!");
		
		//window.setFramerateLimit(10);
		
		Clock frameClock = new Clock();
		Time deltaTime;
		float deltaSeconds;
		float fps;
		int fpsCounter = 0;
		
		Font coolFont = new Font();
		try {
		    coolFont.loadFromFile(new File("resources/visitor1.ttf"));
		} catch(IOException e) {
		    //Failed to load font
		    e.printStackTrace();
		}
		
		Text fpsText = new Text(" ", coolFont,24);
		fpsText.setPosition(10, 0);
		//fpsText.setColor(Color.WHITE);
		
		CircleShape circle = new CircleShape(75, 6);
		circle.setFillColor(new Color(240, 220, 30));
		circle.setOrigin(55, 55);
		circle.setPosition(200, 200);
		
		AbstractBody dummy = new DummyBody (new Vector2f(100,100), new Vector2f(100, 100));
		
		Texture groundTexture = new Texture();
		try {
			groundTexture.loadFromFile(new File("resources/ground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AbstractBody dummy2 = new TexturedBody(new Vector2f(300,160), new Vector2f(100, 100), 
				groundTexture);
		
		Color bgColor = new Color(127, 127, 255);
		
		while(window.isOpen()) {
			deltaTime = frameClock.restart();
		    deltaSeconds = deltaTime.asSeconds();
		    fps = 1/deltaSeconds;
		    
		    if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
		    	circle.move(0,-1);
		    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
		    	circle.move(0,1);
		    }
		    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
		    	circle.move(-1,0);
		    }  else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
		    	circle.move(1,0);
		    }
		    
			if (fpsCounter++ > 10) {
				fpsText.setString("fps: " + Integer.toString((int) fps));
		    	fpsCounter = 0;
		    }
			
		    window.clear(bgColor);

		    circle.setRotation(deltaSeconds * 50 + circle.getRotation());
		    window.draw(circle);
		    window.draw(fpsText);
		    window.draw(dummy);
		    window.draw(dummy2);
		    window.display();

		    //Handle events
		    for(Event event : window.pollEvents()) {
		        if(event.type == Event.Type.CLOSED) {
		            //The user pressed the close button
		            window.close();
		        }
		    }
		}
		
	}
}