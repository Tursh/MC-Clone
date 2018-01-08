package com.tremblar.models;

import com.tremblar.loaders.Texture;

public class TexturedMesh {

	Mesh mesh;
	Texture texture;

	public TexturedMesh(Mesh mesh, Texture texture) {
		this.mesh = mesh;
		this.texture = texture;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Texture getTexture() {
		return texture;
	}
	
}
