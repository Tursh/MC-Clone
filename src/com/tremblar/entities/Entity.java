package com.tremblar.entities;

import com.sun.javafx.geom.Vec3f;
import com.tremblar.models.TexturedMesh;

public class Entity {

	private TexturedMesh texturedMesh;
    private Vec3f position;
    private float rotX, rotY, rotZ;
    private float scale;
 
    public Entity(TexturedMesh texturedMesh, Vec3f position, float rotX, float rotY, float rotZ,
            float scale) {
        this.texturedMesh = texturedMesh;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }
 
    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }
 
    public void increaseRotation(float dx, float dy, float dz) {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }
 
    public TexturedMesh getTexturedMesh() {
        return texturedMesh;
    }
 
    public void setTexturedMesh(TexturedMesh texturedMesh) {
        this.texturedMesh = texturedMesh;
    }
 
    public Vec3f getPosition() {
        return position;
    }
 
    public void setPosition(Vec3f position) {
        this.position = position;
    }
 
    public float getRotX() {
        return rotX;
    }
 
    public void setRotX(float rotX) {
        this.rotX = rotX;
    }
 
    public float getRotY() {
        return rotY;
    }
 
    public void setRotY(float rotY) {
        this.rotY = rotY;
    }
 
    public float getRotZ() {
        return rotZ;
    }
 
    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }
 
    public float getScale() {
        return scale;
    }
 
    public void setScale(float scale) {
        this.scale = scale;
    }
	
}
