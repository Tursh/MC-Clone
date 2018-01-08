package com.tremblar.entities;

import java.awt.Point;

import org.lwjgl.glfw.GLFW;

import com.sun.javafx.geom.Vec3f;
import com.tremblar.input.Keyboard;
import com.tremblar.input.Mouse;

public class Camera {

	private Vec3f  position = new Vec3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(){}
	
	public void move(){
		if(Keyboard.isKeyDown(87)){
			position.z -= 0.02f;
		}
		if(Keyboard.isKeyDown(68)){
			position.x += 0.02f;
		}
		if(Keyboard.isKeyDown(65)){
			position.x -= 0.02f;
		}
		if(Keyboard.isKeyDown(83)){
			position.z += 0.02f;
		}
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)){
			position.y += 0.02f;
		}
		if(Keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)){
			position.y -= 0.02f;
		}
		Point move = Mouse.getMove();
		yaw -= move.getX() / 2;
		pitch += move.getY() / 2;
	}

	public Vec3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
}
