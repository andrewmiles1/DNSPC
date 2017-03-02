package com.burnt_toast.dungeons_n_stuff.monsters;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.burnt_toast.dungeons_n_stuff.MainFrame;
import com.burnt_toast.dungeons_n_stuff.Monster;

public class Slime extends Monster{

	public Slime() {
		super(MainFrame.slimeFrames);
		this.movementSpeed = 5;
		this.health = 2;
		// TODO Auto-generated constructor stub
	}

	@Override
	public float calcHealth() {
		// TODO Auto-generated method stub
		
		return 3;
	}

}
