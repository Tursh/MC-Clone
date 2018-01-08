package com.tremblar.shader;

import java.nio.FloatBuffer;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.sun.javafx.geom.Vec3f;
import com.tremblar.toolBox.Matrix4f;

public abstract class ShaderProgram {

	private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
     
    public ShaderProgram(String vertexFile,String fragmentFile){
        vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
    }
    
    protected int getUniformLocation(String uniformName){
        int location = GL20.glGetUniformLocation(programID,uniformName);
        if(location < 0){
        	throw new RuntimeException(uniformName + " is not a uniform");
        }
        return location;
    }
    
    protected void loadFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }
     
    protected void loadVector(int location, Vec3f vector){
        GL20.glUniform3f(location,vector.x,vector.y,vector.z);
    }
     
    protected void loadBoolean(int location, boolean value){
        float toLoad = 0;
        if(value){
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }
    
    protected void loadInt(int location, int value){
    	GL20.glUniform1i(location, value);
    }
    
    protected void loadMatrix(int location, Matrix4f mat4){
    	FloatBuffer buffer = mat4.toFloatBuffer();
    	GL20.glUniformMatrix4fv(location, false, buffer);
    }
     
    public void start(){
        GL20.glUseProgram(programID);
    }
     
    public void stop(){
        GL20.glUseProgram(0);
    }
     
    public void cleanUp(){
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }
     
    protected abstract void bindAttributes();
     
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }
     
    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        Scanner scan =  new Scanner(ShaderProgram.class.getResourceAsStream(file));
		while(scan.hasNext()){
		    shaderSource.append(scan.nextLine()).append("//\n");
		}
		scan.close();
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }
	
}
