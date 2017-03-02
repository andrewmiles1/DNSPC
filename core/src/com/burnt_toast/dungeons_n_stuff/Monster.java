package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.burnt_toast.monster_generator.Poolable;

public abstract class Monster extends Character{
	public Monster(TextureRegion[] passFrames) {
		super(passFrames);
		this.isMoving = true;
		// TODO Auto-generated constructor stub
	}
	protected float health;
	protected int level;
	protected float meleeDamage;
	protected char direction;
	protected float attackTimer;
	protected float animationTimer;
	protected float patienceTime;//the default of when it will give up.
	protected float giveUpTimer; //the amount of time this goes back to being a placeholder
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
<<<<<<< Updated upstream
	public void setGiveUpTime(float passPatience){
		patienceTime = passPatience;
		giveUpTimer = passPatience;
	}
	public boolean update(float playerX, float playerY){
=======
	public void update(float playerX, float playerY){
		if(this.health <=0){
			//retire object
			this.inUse = false;
		}
>>>>>>> Stashed changes
		move(playerX, playerY);
		if(giveUpTimer > 0){
			giveUpTimer -= Gdx.graphics.getDeltaTime();
		}
		else{
			giveUpTimer = patienceTime;
			return true;
		}
		update();
		return false;
	}
	public void move(float playerX, float playerY){
		if(playerX < this.getX()){
			this.move('l');
			//this.collisionRect.x -= this.movementSpeed * Gdx.graphics.getDeltaTime();
		}
		else if(playerX > this.getX()){
			this.move('r');
			//this.collisionRect.x += this.movementSpeed * Gdx.graphics.getDeltaTime();
		}
		if(playerY < this.getY()){
			this.move('d');
			//this.collisionRect.y -= this.movementSpeed * Gdx.graphics.getDeltaTime();
		}
		else if(playerY > this.getY()){
			this.move('u');
			//this.collisionRect.y += this.movementSpeed * Gdx.graphics.getDeltaTime();
		}
		//this.move("slime");
		//System.out.println("trying to move" + this.collisionRect.height);
		//collision and stuff math
		
	}
	/**
	 * calculates the health for the current level of floor and such.
	 * @return
	 */
	public abstract float calcHealth();
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.inUse = true;
		this.health = this.calcHealth();
		//set all the waling speed and stuff based off floor level
	}
	@Override
	public void retire() {
		// TODO Auto-generated method stub
		this.inUse = false;
	}
	

}
