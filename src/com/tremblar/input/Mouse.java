package com.tremblar.input;

import java.awt.Point;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import com.tremblar.renderers.DisplayManager;

public class Mouse extends GLFWCursorPosCallback{

	private static Point mousePos = new Point();
	private static Point lastMousePos = new Point();
	private static boolean grabbed = false;
	
	public static void grab(){
		if(!grabbed){
			GLFW.glfwSetInputMode(DisplayManager.getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
			grabbed = true;
		}
	}
	
	public static void ungrab(){
		if(grabbed){
			GLFW.glfwSetInputMode(DisplayManager.getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
			grabbed = false;
		}
	}

	@Override
	public void invoke(long window, double xpos, double ypos) {
		mousePos.setLocation(xpos, ypos);
	}
	
	public static Point getMove(){
		Point move = new Point(mousePos.x - lastMousePos.x, mousePos.y - lastMousePos.y);
		lastMousePos = (Point) mousePos.clone();
		return move;
		
	}
	
}
