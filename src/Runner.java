import java.io.File;
import java.io.IOException;

import org.gaem.Camera;
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
		// [VIEWS[
		View cam = new View(new Vector2f(500, 300), new Vector2f(640,480));
		Camera camera = new Camera();
		camera.setView(cam);
		
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
		CircleShape circle = new CircleShape(75, 9);
		circle.setFillColor(new Color(240, 220, 30));
		circle.setOrigin(75, 75);
		circle.setPosition(100, -100);
		
		objects.add(new StaticBody(new Vector2f (550, 300), new Vector2f(40, 40)));
		objects.add(new StaticBody(new Vector2f (600, 180), new Vector2f(300, 30)));
		objects.add(new StaticBody(new Vector2f (330, 260), new Vector2f(100, 30)));
		objects.add(new StaticBody(new Vector2f (600, 180), new Vector2f(30, 240)));
		objects.add(new StaticBody(new Vector2f (500, 380), new Vector2f(130, 40)));
		objects.add(new StaticBody(new Vector2f (10, 420), new Vector2f(600, 20)));
		objects.add(new StaticBody(new Vector2f (10, 0), new Vector2f(20, 440)));
		objects.add(new StaticTexturedBody(new Vector2f (30, 320), groundTexture));
		objects.add(new StaticTexturedBody(new Vector2f (30, 220), groundTexture));
		objects.add(new StaticTexturedBody(new Vector2f (130, 320), groundTexture));
		//objects.add(new StaticTexturedBody(new Vector2f (150, 220), groundTexture));
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
		    	//cat.move(0,-2);
		    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
		    	//cat.move(0,2);
		    }
		    final float SPEED = 200;
		    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
		    	cat.run(-SPEED);
		    	//cat.move(-2, 0);
		    }  else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
		    	cat.run(SPEED);
		    	//cat.move(2, 0);
		    }
		    
			if (fpsCounter++ > 10) {
				fpsText.setString("fps: " + Integer.toString((int) fps));
		    	fpsCounter = 0;
		    }
			
			objects.updateAll(deltaSeconds);
			
		    window.clear(bgColor);
		    
		    camera.setActorPos(cat.getPosition());
		    window.setView(cam);
		    circle.setRotation(deltaSeconds * 50 + circle.getRotation());
		    window.draw(circle);
		    
		    objects.drawAll();
		    
		    window.setView(window.getDefaultView());
		    window.draw(fpsText);
		    
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