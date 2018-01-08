package com.tremblar;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

class Timer {

	private float interval;
	private double lastTime;
	private float accumulation = 0F;
	int updates;
	
	Timer(float UPS){
		this.lastTime = glfwGetTime();
		this.interval = 1 / UPS;
	}
	
	private float getDelta(){
		float delta = (float) (glfwGetTime() - lastTime);
		lastTime = glfwGetTime();
		return delta;
	}
	
	public void updateTime(){
		float delta = getDelta();
		accumulation += delta;
		
		updates = (int)(accumulation / interval);
		accumulation -= (updates * interval);
	}
	
	public float getAlpha(){
		float delta = getDelta();
		accumulation += delta;
		
		return accumulation / interval;
	}

}
