package com.burnt_toast.dungeons_n_stuff;

import java.util.HashMap;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Character {
//used for the super class of archer and warrior and stuff. Just in case you forget.
	protected float health;
	protected float exp;
	protected int level;
	protected float nextLevelExp;
	protected float meeleePause;
	protected float[] meeleeDamage;//range of 2 numbers
	protected float[] rangedDamage;//range of 2 numbers
	protected boolean isMoving;
	protected char direction;//facing direction
	protected boolean flipped;//used when flipping the character.
	protected float movementSpeed;
	protected float frameSizeX;
	protected float frameSizeY;
	protected float meleeSizeX;
	protected float meleeSizeY;
	protected TextureRegion[] frames;
	
	
	protected Rectangle collisionRect;
	protected Rectangle meleeRect;
	protected float attackMaxTime;//how long the player attacks for,
									//or how long Melee box is active
	protected float attackTimer;//counts down how long meleeRect is active
	
	protected float animationSpeed;
	protected int animationIndex;//used for animation, which frame. 0 or 1.
	protected float animationTimer;
	
	public Character(TextureRegion[] passedFrames){//i pass the frames for sizes
		movementSpeed = 32;//32 pixels per second.
		animationSpeed = 0.25f;//animates every fourth a second.
		collisionRect = new Rectangle(0, 0, frames[1].getRegionWidth(), frames[1].getRegionHeight() / 2);
		meleeRect = new Rectangle(0, 0, frames[1].getRegionWidth(), frames[1].getRegionHeight());
		frameSizeX = frames[1].getRegionWidth();
		frameSizeY = frames[1].getRegionHeight();
		meleeSizeX = frames[2].getRegionWidth();
		meleeSizeY = frames[2].getRegionHeight();
		frames = passedFrames;
		
	}
	
	public void setIfMoving(boolean passMoving){isMoving = passMoving;}
	public boolean getIfMoving(){return isMoving;}
	public char getDirection(){return direction;}
	public void setDirection(char passDirection){
		direction = passDirection;
		if(passDirection == 'l')flipped = true;
		else if (passDirection == 'r') flipped = false;
	}
	public void move(){
		if(direction == 'u'){
			//collision goes here.
			if(!PlayScreen.checkCollisionAt(collisionRect.x, collisionRect.y + (movementSpeed* Gdx.graphics.getDeltaTime()),
					collisionRect.getWidth(), collisionRect.getHeight())){//if no collision.
				collisionRect.y += movementSpeed * Gdx.graphics.getDeltaTime();
				meleeRect.y = collisionRect.y + frameSizeY;
				meleeRect.x = collisionRect.x;
			}
		}
		else if(direction == 'd'){
			if(!PlayScreen.checkCollisionAt(collisionRect.x, collisionRect.y - (movementSpeed* Gdx.graphics.getDeltaTime()),
					collisionRect.getWidth(), collisionRect.getHeight())){//if no collision.
				collisionRect.y -= movementSpeed * Gdx.graphics.getDeltaTime();
				meleeRect.y = collisionRect.y - meleeSizeY;//places below character, subtract meelee not just frame size
				meleeRect.x = collisionRect.x;
			}
		}
		else if(direction == 'r'){
			if(!PlayScreen.checkCollisionAt(collisionRect.x + (movementSpeed* Gdx.graphics.getDeltaTime()), collisionRect.y,
					collisionRect.getWidth(), collisionRect.getHeight())){//if no collision.
				collisionRect.x += movementSpeed * Gdx.graphics.getDeltaTime();
				meleeRect.x = collisionRect.x + frameSizeX;
				meleeRect.y = collisionRect.y;
			}
		}
		else if(direction == 'l'){
			if(!PlayScreen.checkCollisionAt(collisionRect.x - (movementSpeed* Gdx.graphics.getDeltaTime()), collisionRect.y,
					collisionRect.getWidth(), collisionRect.getHeight())){//if no collision.
				collisionRect.x -= movementSpeed * Gdx.graphics.getDeltaTime();
				meleeRect.x = collisionRect.x - meleeSizeX;//sets to left, so subtract melee size
				meleeRect.y = collisionRect.y;
			}
		}
	}
	public abstract void draw(SpriteBatch batch);
	public void update(){
		if(isMoving){
			animationTimer += Gdx.graphics.getDeltaTime();
			if(animationTimer >= animationSpeed){
				animationTimer = 0;
				animationIndex = animationIndex == 0? 1:0;//if it's 0, than make it one, if not, then 0
			}
			move();
		}
		if(attackTimer > 0){
			attackTimer -= Gdx.graphics.getDeltaTime();
			if(attackTimer <=0) attackTimer = 0;//if overshot
		}
	}
	
	public abstract void attack();
	
	public void hit(float damage){health -= damage;}
	public void heal(float amount){health += amount;}
	public void expPickup(float expAmount){
		if(exp + expAmount >= nextLevelExp){
			//levelUp()
			exp = (exp + expAmount) - nextLevelExp;//make exp equal to whatever is left over after levelUp
		}
	}
	protected float calculateNextLvExp(int currLevel){
		return (10^currLevel+100);
	}
	public void levelUp(){
		level++;//level up yeah
		//change stats like health and stuff EXPONENTIALLY WOAOAOOAHOAHOH
	}
	public void setPosition(float passX, float passY){
		collisionRect.x = passX;
		collisionRect.y = passY;
	}
	public float getX(){return collisionRect.x;}
	public float getY(){return collisionRect.y;}
	
	
}
