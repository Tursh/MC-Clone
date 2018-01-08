package com.tremblar.renderers;

import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.Dimension;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class DisplayManager {

	public static final int WIDTH = 720, HEIGHT = 480;
	
	private static long window;
	
	public static void createDisplay(){
		/* Initialize GLFW */
		if(!GLFW.glfwInit()){
			throw new IllegalStateException("Could not initialize GLFW!");
		}
		
		/* Create OpenGL context (3.2) */
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE);
		
		/* Create window */
		window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Minecraft", NULL, NULL);
		if(window == NULL){
			System.out.println("Your computer doesn't support openGL 3.2");
			/* Create OpenGL context (3.0) */
			GLFW.glfwDefaultWindowHints();
			GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
			GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 0);
			window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "Minecraft", NULL, NULL);
			
			if(window == NULL){
				GLFW.glfwTerminate();
				throw new IllegalStateException("Could not create window!");
			}
		}
		//create openGL capabilities
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		GLFW.glfwSwapInterval(0);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
		
	}
	
	public static long getWindow(){
		return window;
	}
	
	public static Dimension getWindowDimension(){
		IntBuffer width = BufferUtils.createIntBuffer(1), height = BufferUtils.createIntBuffer(1);
		GLFW.glfwGetWindowSize(window, width, height);
		return new Dimension(width.get(), height.get());
	}
	
}
