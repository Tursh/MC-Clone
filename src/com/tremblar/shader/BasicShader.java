package com.tremblar.shader;

import com.tremblar.entities.Camera;
import com.tremblar.toolBox.Matrix4f;
import com.tremblar.toolBox.MatrixCalculator;

public class BasicShader extends ShaderProgram{

	private static final String VERTEX_FILE = "/vertex_shader.glsl";
    private static final String FRAGMENT_FILE = "/fragment_shader.glsl";
 
    private int transformationMatrix_location;
    private int projectionMatrix_location;
    private int viewMatrix_location;
    
    public BasicShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
        transformationMatrix_location = super.getUniformLocation("transformationMatrix");
        projectionMatrix_location = super.getUniformLocation("projectionMatrix");
        viewMatrix_location = super.getUniformLocation("viewMatrix");
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "texCoords");
    }
    
    public void loadTransformationMatrix(Matrix4f mat4){
    	super.loadMatrix(transformationMatrix_location, mat4);
    }

    public void loadProjectionMatrix(Matrix4f mat4){
    	super.loadMatrix(projectionMatrix_location, mat4);
    }
    
    public void loadViewMatrix(Camera camera){
    	super.loadMatrix(viewMatrix_location, MatrixCalculator.createViewMatrix(camera));
    }
    
}
