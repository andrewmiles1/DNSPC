package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Warrior extends Player{

	public Warrior() {
		super(MainFrame.warriorFrames);
		this.attackMaxTime = 0.25f;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		//if flipped, then it draws, if not, then it doesn't draw flipped. long function holy macaroni.
		batch.draw(MainFrame.warriorFrames[animationIndex],
				flipped? collisionRect.x + MainFrame.warriorFrames[animationIndex].getRegionWidth():collisionRect.x,
				collisionRect.y,
				flipped?
				MainFrame.warriorFrames[animationIndex].getRegionWidth()*-1:MainFrame.warriorFrames[animationIndex].getRegionWidth(),
				MainFrame.warriorFrames[animationIndex].getRegionHeight());
		if(attackTimer > 0 ){//if we're attacking
			/*
			batch.draw(MainFrame.warriorFrames[2],
					meleeRect.x, meleeRect.y, MainFrame.warriorFrames[2].getRegionWidth()*-1,
					MainFrame.warriorFrames[2].getRegionHeight());
					*///THIS ONE ABOVE WORKS RIGHT, but the one below DOES NOT
			
			if(direction == 'r' || direction == 'l'){
			batch.draw(MainFrame.warriorFrames[2],
					flipped? meleeRect.x + meleeSizeX:meleeRect.x,
					meleeRect.y,
					flipped?
					MainFrame.warriorFrames[2].getRegionWidth()*-1:MainFrame.warriorFrames[2].getRegionWidth(),
					MainFrame.warriorFrames[2].getRegionHeight());
			}//end if
			else if(direction == 'u' || direction == 'd'){
			batch.draw(MainFrame.warriorFrames[2], 					
					(direction=='d')? meleeRect.x + meleeSizeX:meleeRect.x,
					meleeRect.y,
					 0, 0,
					MainFrame.warriorFrames[2].getRegionWidth(),
					//(direction == 'u')?if flipped y
					MainFrame.warriorFrames[2].getRegionHeight()*-1, 1, 1, 90);
			}//emd else
			
		}
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		this.attackTimer = this.attackMaxTime;
	}

}
