import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.gaem.Camera;
import org.gaem.ObjectManager;
import org.gaem.bodies.*;
import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.Event;
import org.jsfml.window.event.KeyEvent;

class Runner {

	public static void main(String[] args) {
		RenderWindow window = new RenderWindow();
		window.create(new VideoMode(640, 480), "Hello!");
		
		window.setFramerateLimit(120);
		
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
		    coolFont.loadFromFile(Paths.get("resources", "visitor1.ttf"));
		} catch(IOException e) {
		    //Failed to load font
		    e.printStackTrace();
		}
		
		// [TEXTURES]
		Texture groundTexture = new Texture();
		Texture catTexture = new Texture();
		Texture jockerTexture = new Texture();
		try {
			groundTexture.loadFromFile(Paths.get("resources", "ground.png"));
			catTexture.loadFromFile(Paths.get("resources", "cat.png"));
			jockerTexture.loadFromFile(Paths.get("resources", "jocker.png"));
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
		
		objects.add(new TexturedBody(new Vector2f (-5000, 600), new Vector2f(10000, 30)));
		objects.add(new TexturedBody(new Vector2f (550, 300), new Vector2f(40, 40)));
		objects.add(new TexturedBody(new Vector2f (600, 180), new Vector2f(300, 30)));
		objects.add(new TexturedBody(new Vector2f (300, 260), new Vector2f(160, 30)));
		objects.add(new TexturedBody(new Vector2f (600, 180), new Vector2f(30, 240)));
		objects.add(new TexturedBody(new Vector2f (500, 380), new Vector2f(130, 40)));
		objects.add(new TexturedBody(new Vector2f (300, 230), new Vector2f(10, 30)));
		objects.add(new TexturedBody(new Vector2f (10, 420), new Vector2f(600, 20)));
		objects.add(new TexturedBody(new Vector2f (10, 0), new Vector2f(20, 440)));
		//objects.add(new TexturedBody(new Vector2f (330, 100), new Vector2f(100, 30)));
		//objects.add(new TexturedBody(new Vector2f (280, 130), new Vector2f(20, 160)));
		//objects.add(new TexturedBody(new Vector2f (460, 130), new Vector2f(20, 160)));
		objects.add(new TexturedBody(new Vector2f (30, 320), groundTexture));
		objects.add(new TexturedBody(new Vector2f (30, 220), groundTexture));
		objects.add(new TexturedBody(new Vector2f (130, 320), groundTexture));
		
		Player player = new Player(new Vector2f(340, 100), jockerTexture);
		
		player.scale(2);
		player.setObjectManager(objects);
		//player.setBounded(false);
		objects.add(player);
		
		while(window.isOpen()) {
		    
		    final float SPEED = 200;
		    
		    if (Keyboard.isKeyPressed(Keyboard.Key.UP)) {
		    	player.jump();
		    	//player.move(0, -1);
		    } else if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) {
		    	//player.move(0,1);
		    }
		    
		    if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) {
		    	player.run(-SPEED);
		    }  else if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) {
		    	player.run(SPEED);
		    }
		    
		    deltaTime = frameClock.restart();
		    deltaSeconds = deltaTime.asSeconds();
		    objects.updateAll(deltaSeconds);
		    
		    fps = 1/deltaSeconds;
			
		    if (fpsCounter++ > 10) {
				fpsText.setString("FPS: " + Integer.toString((int) fps));
		    	fpsCounter = 0;
		    }
			
			
			
		    window.clear(bgColor);
		    
		    camera.setActorPos(player.getPosition());
		    window.setView(cam);
		    circle.setRotation(deltaSeconds * 50 + circle.getRotation());
		    window.draw(circle);
		    
		    objects.drawAll();
		    
		    window.setView(window.getDefaultView());
		    window.draw(fpsText);
		    
		    window.display();

		    // [Handle events]
		    for(Event event : window.pollEvents()) {
		        //FIXME switch-case
		    	if(event.type == Event.Type.CLOSED) {
		            window.close();
		        } else if (event.type == Event.Type.KEY_PRESSED) {
		        	KeyEvent keyEvent = event.asKeyEvent();
		        	if (keyEvent.key == Keyboard.Key.ESCAPE) {
		        		System.out.println("Have a nice day!");
		        		System.exit(0);
		        	} else if (keyEvent.key == Keyboard.Key.SPACE) {
		        		player.unlock();
		        	}
		        	
		        }
		    }
		
		}
	}
}