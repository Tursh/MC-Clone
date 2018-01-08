package com.tremblar.loaders;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

public class Texture {

	private int ID;
	private int width, height;
	
	private Texture(Loader loader, int ID, int width, int height){
		this.ID = ID;
		loader.addTextureID(ID);
		this.width = width;
		this.height = height;
	}
	
	public void bind(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ID);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
    public void uploadData(int width, int height, ByteBuffer data) {
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data);
    }
	
	public static Texture createTexture(int width, int height, ByteBuffer image, Loader loader){
		int textID = GL11.glGenTextures();
		Texture texture = new Texture(loader, textID, width, height);
		texture.bind();
		
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		
		texture.uploadData(width, height, image);
		
		return texture;
	}

	public static Texture loadTexture(Loader loader, String path){
		//create variables for STB_Image to load a texture
		ByteBuffer image;
		IntBuffer w = BufferUtils.createIntBuffer(1), 
				h = BufferUtils.createIntBuffer(1), 
				comp = BufferUtils.createIntBuffer(1);
		
		STBImage.stbi_set_flip_vertically_on_load(false);
		image = STBImage.stbi_load(path, w, h, comp, 4);
        if (image == null) {
            throw new RuntimeException("Failed to load a texture file!"
                                       + System.lineSeparator() + STBImage.stbi_failure_reason());
        }
        
        int width = w.get();
        int height = h.get();
        
        return createTexture(width, height, image, loader);
		
	}
	
}
