import java.io.File;
import java.io.IOException;

import org.gaem.ObjectManager;
import org.gaem.bodies.AbstractBody;
import org.gaem.bodies.DummyBody;
import org.gaem.bodies.Player;
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
		
		// [COLOURS]
		Color bgColor = new Color(127, 127, 255);
		
		// [FONTS]
		Font coolFont = new Font();
		try {
		    coolFont.loadFromFile(new File("resources/visitor1.ttf"));
		} catch(IOException e) {
		    //Failed to load font
		    e.printStackTrace();
		}
		
		// [TEXTURES]
		Texture groundTexture = new Texture();
		Texture catTexture = new Texture();
		try {
			groundTexture.loadFromFile(new File("resources/ground.png"));
			catTexture.loadFromFile(new File("resources/cat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// [OBJECT MANAGER]
		ObjectManager objects = new ObjectManager(window);
		
		// [TEXTS]
		Text fpsText = new Text(" ", coolFont,24);
		fpsText.setPosition(10, 0);

		// [ENTITIES]
		CircleShape circle = new CircleShape(75, 6);
		circle.setFillColor(new Color(240, 220, 30));
		circle.setOrigin(55, 55);
		circle.setPosition(200, 200);
		
		AbstractBody dummy = new DummyBody (new Vector2f(100,100), new Vector2f(100, 100));
		objects.add(dummy);
		
		TexturedBody dummy2 = new TexturedBody(new Vector2f(300,160), groundTexture);
		dummy2.scale(4);
		objects.add(dummy2);
		
		Player cat = new Player(new Vector2f(400,280), catTexture);
		cat.scale(1);
		cat.setObjectManager(objects);
		objects.add(cat);
		
		while(window.isOpen()) {
			deltaTime = frameClock.restart();
		    deltaSeconds = deltaTime.asSeconds();
		    fps = 1/deltaSeconds;
		    
		    if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
		    	cat.move(0,-1);
		    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
		    	cat.move(0,1);
		    }
		    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
		    	cat.move(-1,0);
		    }  else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
		    	cat.move(1,0);
		    }
		    
			if (fpsCounter++ > 10) {
				fpsText.setString("fps: " + Integer.toString((int) fps));
		    	fpsCounter = 0;
		    }
			
		    window.clear(bgColor);

		    circle.setRotation(deltaSeconds * 50 + circle.getRotation());
		    window.draw(circle);
		    window.draw(fpsText);
		    //window.draw(dummy);
		    //window.draw(dummy2);
		    //window.draw(cat);
		    objects.drawAll();
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