package com.tremblar;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

import com.sun.javafx.geom.Vec3f;
import com.tremblar.entities.Camera;
import com.tremblar.entities.Entity;
import com.tremblar.input.Keyboard;
import com.tremblar.input.Mouse;
import com.tremblar.input.MouseButtons;
import com.tremblar.loaders.Loader;
import com.tremblar.loaders.Texture;
import com.tremblar.models.Mesh;
import com.tremblar.models.TexturedMesh;
import com.tremblar.renderers.DisplayManager;
import com.tremblar.renderers.Renderer;

public class Main implements Runnable{

	private GLFWErrorCallback errorCallback;
	
	private Timer timer;
	private Renderer renderer;
	//Callbacks
	private Keyboard keyboard;
	private MouseButtons mouseButtons;
	private Mouse mouse;
	//Loaders
	private Loader loader;
	private Mesh mesh;
	private TexturedMesh texturedMesh;
	private Entity entity;
	private Camera camera;
	
	private void init(){
		//create window and initialize openGL capacibilities
		DisplayManager.createDisplay();
		long window = DisplayManager.getWindow();
		
		//Setup callbacks
		GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		GLFW.glfwSetKeyCallback(window, keyboard = new Keyboard());
		GLFW.glfwSetMouseButtonCallback(DisplayManager.getWindow(), mouseButtons = new MouseButtons());
		GLFW.glfwSetCursorPosCallback(window, mouse = new Mouse());
		
		Mouse.grab();
		
		//cube vertices informations
		float[] vertices = {            
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                0.5f,-0.5f,0,   
                0.5f,0.5f,0,        
                 
                -0.5f,0.5f,1,   
                -0.5f,-0.5f,1,  
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                0.5f,0.5f,0,    
                0.5f,-0.5f,0,   
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                -0.5f,-0.5f,1,  
                -0.5f,0.5f,1,
                 
                -0.5f,0.5f,1,
                -0.5f,0.5f,0,
                0.5f,0.5f,0,
                0.5f,0.5f,1,
                 
                -0.5f,-0.5f,1,
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,-0.5f,1
        };
         
        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0
        };
         
        int[] indices = {
                0,1,3,  
                3,1,2,  
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,   
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22
 
        };
		//Setup engines
		loader = new Loader();
		renderer = new Renderer();
		timer = new Timer(60F);
		camera = new Camera();
		//load cube
		mesh = loader.loadToVAO(vertices, textureCoords, indices);
		texturedMesh = new TexturedMesh(mesh, Texture.loadTexture(loader, "res/grass.png"));
		entity = new Entity(texturedMesh, new Vec3f(0.0f, 0.0f, -1.0f), 0.0f, 0.0f, 0.0f, 1.0f);

		System.out.println("Game initialized in " + GLFW.glfwGetTime() + " seconds.");
	}
	
	private void loop(){
		//Game loop
		while(!GLFW.glfwWindowShouldClose(DisplayManager.getWindow()) && !Keyboard.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
			timer.updateTime();
			while(timer.updates > 0){
				timer.updates--;
				update();
			}
			render(timer.getAlpha());
		}
	}
	
	private void update(){
		GLFW.glfwPollEvents();
		camera.move();
		//entity.increasePosition(0.02f, 0f, 0f);
	}
	
	private void render(float alpha){
		renderer.clearDisplay();
		renderer.render(entity, camera);
		GLFW.glfwSwapBuffers(DisplayManager.getWindow());
	}
	
	private void close(){
		GLFW.glfwDestroyWindow(DisplayManager.getWindow());
		renderer.cleanUp();
		keyboard.free();
		mouseButtons.free();
		mouse.free();
		System.out.println("Game ran during " + GLFW.glfwGetTime() + " seconds.");
		GLFW.glfwTerminate();
		errorCallback.free();
	}
	
	public void run(){
		init();
		loop();
		close();
	}
	
	public static void main(String[] args) {
		new Thread(new Main(), "Game Loop").start();
	}

}
