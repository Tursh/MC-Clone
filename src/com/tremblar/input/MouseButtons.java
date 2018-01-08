package com.tremblar.input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButtons extends GLFWMouseButtonCallback{

	public static int LEFT_BUTTON = 0, 
			RIGHT_BUTTON = 1; 
	
	boolean[] buttons = new boolean[10];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		buttons[button] = action != 0;
	}

}
