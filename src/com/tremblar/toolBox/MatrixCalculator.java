package com.tremblar.toolBox;

import java.awt.Dimension;

import com.sun.javafx.geom.Vec3f;
import com.tremblar.entities.Camera;
import com.tremblar.renderers.DisplayManager;

public class MatrixCalculator {

	public static Matrix4f createTransformationMatrix(Vec3f translation, float rx, float ry,
            float rz, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vec3f(1,0,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vec3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vec3f(0,0,1), matrix, matrix);
        Matrix4f.scale(new Vec3f(scale,scale,scale), matrix, matrix);
        return matrix;
    }
	
	public static Matrix4f createProjectionMatrix(float FOV, float nearPlane, float farPlane){
		Matrix4f projectionMatrix = new Matrix4f();
		Dimension screen = DisplayManager.getWindowDimension();
        float aspectRatio = (float) screen.getWidth() / (float) screen.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = farPlane - nearPlane;
 
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((farPlane + nearPlane) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * nearPlane * farPlane) / frustum_length);
        projectionMatrix.m33 = 0;
        return projectionMatrix;
    }
	
	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vec3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vec3f(0, 1, 0), viewMatrix, viewMatrix);
		Vec3f cameraPos = camera.getPosition();
		Vec3f negativeCameraPos = new Vec3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;
	}
	
}
