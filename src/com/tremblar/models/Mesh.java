package com.tremblar.models;

import org.lwjgl.opengl.GL30;

public class Mesh {

	private int vao;
	private int vertecesCount;
	
	public Mesh(int vao, int vertecesCount) {
		this.vao = vao;
		this.vertecesCount = vertecesCount;
	}
	public int getVao() {
		return vao;
	}
	public int getVertecesCount() {
		return vertecesCount;
	}
	
	public void bind(){
		GL30.glBindVertexArray(vao);
	}
	
	public void unbind(){
		GL30.glBindVertexArray(0);
	}
	
}
