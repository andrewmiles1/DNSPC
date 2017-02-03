package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Monster extends Character{
	public Monster(TextureRegion[] frames) {
		super(frames);
		// TODO Auto-generated constructor stub
	}
	protected float health;
	protected int level;
	protected float meleeDamage;
	protected float moveSpeed;
	protected char direction;
	protected float attackTimer;
	protected float animationTimer;
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(flipped == true){
			batch.draw(this., x, y, width, height, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
		}
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	

}
