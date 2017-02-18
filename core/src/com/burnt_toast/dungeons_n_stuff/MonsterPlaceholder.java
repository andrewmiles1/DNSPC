package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.burnt_toast.monster_generator.Poolable;

public class MonsterPlaceholder extends Poolable{
	private float x;
	private float y;
	private float sightRadius;
	private TextureRegion image;
	private boolean activated;
	private Vector3 tempRect;
	
	public MonsterPlaceholder(float passX, float passY,
			float passSightRadius, TextureRegion passImage){
		image = passImage;
		sightRadius = passSightRadius;
		x = passX;
		y = passY;
		activated = false;
		
	}
	
	public MonsterPlaceholder(){
		this(0, 0, 0, null);
		//doesn't need to set activated, it's set in the other constructor
	}
	
	public void setX(float passX){
		x = passX;
	}
	
	public void setY(float passY){
		y = passY;
	}
	public float getX(){
		return x;
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
		batch.draw(image, x,  y);
	}
	/**
	 * This method is used primarily for hashing the visibilty
	 * of the monster
	 * @return
	 */
	public Vector3  getRect(){
		tempRect.x = this.x - this.sightRadius;
		tempRect.y = this.y - this.sightRadius;
		tempRect.z = sightRadius;//the z holds the size of the square.
		return tempRect;
	}
	public void checkVisibility(float passX, float passY){
		if(Math.sqrt(Math.abs(passX-x) + Math.abs(passY-y)) 
				< sightRadius){//if the distance between the two is less than vis.
			activated = true;//then it saw something.
		}
	}

	@Override
	public void reset() {
		//reset anykthing that needs reseting. 
		
	}

	@Override
	public void retire() {
		// TODO Auto-generated method stub
		activated = false;
		x = 0;
		y = 0;
		image = null;
	}
	
	
}
