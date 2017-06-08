package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.burnt_toast.monster_generator.Pool;
import com.burnt_toast.monster_generator.Poolable;

public abstract class Monster extends Character{

	protected int level;
	protected float meleeDamage;
	protected char direction;
	protected float attackTimer;
	protected float animationTimer;
	protected float patienceTime;//the default of when it will give up.
	protected float giveUpTimer; //the amount of time this goes back to being a placeholder
	protected float hitPause;
	protected float hitPauseTimer;
	@SuppressWarnings("rawtypes")
	protected Pool parentPool;
	//these next 3 are for moving more smoothly. actually these next 5.
	protected float totalDistance;
	protected float distanceX;
	protected float distanceY;
	protected float velocityX;
	protected float velocityY;
	
	
	@SuppressWarnings("rawtypes")
	public Monster(TextureRegion[] passFrames, Pool parentPool) {
		super(passFrames);
		this.isMoving = true;
		this.parentPool = parentPool;
		hitPause = 1.0f;
		this.setDirection('n');//n for nunya business
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(hitPauseTimer > 0){
			//batch.setColor(150, 150, 150, 100);
		}
		if(flipped == true){
			batch.draw(this.frames[this.animationIndex], this.getX(), this.getY(),
					this.frameSizeX * -1, this.frameSizeY);
		}
		else{
			batch.draw(this.frames[this.animationIndex], this.getX(), this.getY(),
					this.frameSizeX, this.frameSizeY);
		}
		batch.setColor(Color.WHITE);
		
	}
	public int attack(){
		return (int)(Math.random()*5);
	}

	public void setGiveUpTime(float passPatience){
		patienceTime = passPatience;
		giveUpTimer = passPatience;
	}
	public boolean update(Player currentPlayer){
		
		if(this.health <=0){
			//retire object
			this.toggleInUse();
		}
		move(currentPlayer.getX(), currentPlayer.getY());
		update();
		if(hitPauseTimer > 0){
			//color change when I'mhit
		}
		if(PlayScreen.distForm(currentPlayer.getX()+currentPlayer.getRectangle().getWidth()/2,
				currentPlayer.getY()+currentPlayer.getRectangle().getHeight()/2,
				getX()+collisionRect.getWidth()/2, getY()+collisionRect.getHeight()/2) <= 10){
			hitPauseTimer -= Gdx.graphics.getDeltaTime();
			//if center of player is a certain distance away, then hit it
			if(hitPauseTimer <= 0){
			currentPlayer.hit(this.attack());
			hitPauseTimer = hitPause;
			}
		}
		else
			hitPauseTimer = hitPause;
		
		if(giveUpTimer > 0){
			giveUpTimer -= Gdx.graphics.getDeltaTime();
		}
		else{
			giveUpTimer = patienceTime;
			return true;
		}

		return false;
	}
	public void move(float playerX, float playerY){
		/*	protected float totaldistance;
		protected float distanceX;
		protected float distanceY;
		protected float velocityX;
		protected float velocityY;
		 */
		distanceX = this.getX() - playerX;
		distanceY = this.getY() - playerY;
		totalDistance = Math.abs(distanceX) + Math.abs(distanceY);
		velocityX = (distanceX / totalDistance) * movementSpeed;
		velocityY = (distanceY / totalDistance) * movementSpeed;
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
	
	@Override
	public void hit(float damage){
		super.hit(damage);
		this.hitPauseTimer = hitPause;
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
		this.meeleeDamage[0] = 1;
		this.meeleeDamage[1] = 3;
		//set all the waling speed and stuff based off floor level
	}
	@Override
	public void retire() {
		// TODO Auto-generated method stub
		this.inUse = false;
	}
	
	

}
