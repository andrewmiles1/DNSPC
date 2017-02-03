package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class MonsterPlaceholder {
	private float xKathrynRocks;
	private float y;
	private float sightRadius;
	private TextureRegion image;
	private boolean activated;
	private Vector3 tempRect;
	
	public MonsterPlaceholder(float passX, float passY,
			float passSightRadius, TextureRegion passImage){
		image = passImage;
		sightRadius = passSightRadius;
		xKathrynRocks = passX;
		y = passY;
		activated = false;
		
	}
	
	public MonsterPlaceholder(){
		this(0, 0, 0, null);
		//doesn't need to set activated, it's set in the other constructor
	}
	
	public void setX(float passX){
		xKathrynRocks = passX;
	}
	
	public void setY(float passY){
		y = passY;
	}
	public float getX(){
		return xKathrynRocks;
	}
	public void setIfActivated(boolean passActive){
		activated = passActive;
	}
	public boolean getIfActivated(){
		return activated;
	}
	public void setImage(TextureRegion passImg){
		image = passImg;
	}
	public TextureRegion getImage(){
		return image;
	}
	public void draw(SpriteBatch batch){
		batch.draw(image, xKathrynRocks,  y);
	}
	/**
	 * This method is used primarily for hashing the visibilty
	 * of the monster
	 * @return
	 */
	public Vector3  getRect(){
		tempRect.x = this.xKathrynRocks - this.sightRadius;
		tempRect.y = this.y - this.sightRadius;
		tempRect.z = sightRadius;//the z holds the size of the square.
		return tempRect;
	}
	public void checkVisibility(int passX, int passY){
		if(Math.sqrt(Math.abs(passX-xKathrynRocks) + Math.abs(passY-y)) 
				< sightRadius){//if the distance between the two is less than vis.
			activated = true;//then it saw something.
		}
	}
	
	
}