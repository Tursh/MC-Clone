package com.tremblar.renderers;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.tremblar.entities.Camera;
import com.tremblar.entities.Entity;
import com.tremblar.models.Mesh;
import com.tremblar.shader.BasicShader;
import com.tremblar.toolBox.MatrixCalculator;

public class Renderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
	
	private BasicShader shader;

	public Renderer() {
		shader = new BasicShader();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(1, 0, 0, 1);
		shader.start();
		shader.loadProjectionMatrix(MatrixCalculator.createProjectionMatrix(FOV, NEAR_PLANE, FAR_PLANE));
		shader.stop();
	}

	public void clearDisplay() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	private void prepare(Entity entity, Camera camera) {
		Mesh model = entity.getTexturedMesh().getMesh();
		shader.loadTransformationMatrix(MatrixCalculator.createTransformationMatrix(entity.getPosition(),
				entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale()));
		shader.loadViewMatrix(camera);
		GL30.glBindVertexArray(model.getVao());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		entity.getTexturedMesh().getTexture().bind();
	}

	private void unbindTextureModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

	public void render(Entity entity, Camera camera) {
		shader.start();
		prepare(entity, camera);
		GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getTexturedMesh().getMesh().getVertecesCount(), GL11.GL_UNSIGNED_INT, 0);
		unbindTextureModel();
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
