package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Monster extends Character{
	public Monster(TextureRegion[] frames) {
		super(frames);
		this.isMoving = true;
		// TODO Auto-generated constructor stub
	}
	protected float health;
	protected int level;
	protected float meleeDamage;
	protected char direction;
	protected float attackTimer;
	protected float animationTimer;
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(flipped == true){
			batch.draw(this.frames[this.animationIndex], this.getX(), this.getY(),
					this.frameSizeX * -1, this.frameSizeY);
		}
		else{
			batch.draw(this.frames[this.animationIndex], this.getX(), this.getY(),
					this.frameSizeX, this.frameSizeY);
		}
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	public void move(float playerX, float playerY){
		if(playerX < this.getX()){
			this.setPosition(this.getX()-this.movementSpeed, this.getY());
		}
		else if(playerX > this.getX()){
			this.setPosition(this.getX()+this.movementSpeed, this.getY());
		}
		if(playerY < this.getX()){
			this.setPosition(this.getX(), this.getY() - this.movementSpeed);
		}
		else if(playerY > this.getY()){
			this.setPosition(this.getX(), this.getY() + this.movementSpeed);
		}
	}
	

}
