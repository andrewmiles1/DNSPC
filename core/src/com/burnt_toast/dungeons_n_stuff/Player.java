package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Character{

	protected float specialCharge;//PLAYER
	protected boolean specialCooling;//used to know if the special is used and cooling down or not.
	protected float coolDownIncrement;//how much it cools a second
	protected float[] specialDamage;//range of 2 numbers

	
	public Player(TextureRegion[] frames) {
		super(frames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		super.attack();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retire() {
		// TODO Auto-generated method stub
		
	}
	public float getMovementSpeed(){
		return this.movementSpeed;
	}
	
}
