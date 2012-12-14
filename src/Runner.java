import java.io.File;
import java.io.IOException;

import org.gaem.ObjectManager;
import org.gaem.bodies.*;
import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;

class Runner {

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
		Texture jockerTexture = new Texture();
		Texture jockerMirrorTexture = new Texture();
		try {
			groundTexture.loadFromFile(new File("resources/ground.png"));
			catTexture.loadFromFile(new File("resources/cat.png"));
			jockerTexture.loadFromFile(new File("resources/jocker.png"));
			jockerMirrorTexture.loadFromFile(new File("resources/jocker_mirrored.png"));
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
		circle.setOrigin(75, 75);
		circle.setPosition(200, 200);
		
		TexturedBody dummy = new TexturedBody (new Vector2f(100,100), groundTexture);
		objects.add(dummy);
		((TexturedBody) objects.getLast()).scale(4);
		
		TexturedBody dummy2 = new TexturedBody(new Vector2f(300,160), groundTexture);
		dummy2.scale(4);
		objects.add(dummy2);
		
		objects.add(new StaticBody(new Vector2f (420, 210), new Vector2f(30, 30)));
		objects.add(new StaticBody(new Vector2f (450, 280), new Vector2f(60, 40)));
		objects.add(new StaticBody(new Vector2f (600, 180), new Vector2f(30, 200)));
		objects.add(new StaticBody(new Vector2f (500, 380), new Vector2f(130, 40)));
		objects.add(new StaticBody(new Vector2f (30, 420), new Vector2f(600, 20)));
		objects.add(new StaticTexturedBody(new Vector2f (330, 320), groundTexture));
		objects.add(new StaticTexturedBody(new Vector2f (150, 220), groundTexture));
		//((TexturedBody) objects.getLast()).scale(4);
		
		Player cat = new Player(new Vector2f(300, 80), jockerTexture);
		cat.scale(2);
		cat.setObjectManager(objects);
		cat.setBounded(false);
		cat.setNormalTexture(jockerTexture);
		cat.setMirrorTexture(jockerMirrorTexture);
		objects.add(cat);
		
		while(window.isOpen()) {
			deltaTime = frameClock.restart();
		    deltaSeconds = deltaTime.asSeconds();
		    fps = 1/deltaSeconds;
		    
		    if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
		    	cat.jump();
		    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
		    	//cat.move(0,2);
		    }
		    final float SPEED = 200;
		    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
		    	cat.run(-SPEED);
		    }  else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
		    	cat.run(SPEED);
		    }
		    
			if (fpsCounter++ > 10) {
				fpsText.setString("fps: " + Integer.toString((int) fps));
		    	fpsCounter = 0;
		    }
			
			objects.updateAll(deltaSeconds);
			
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