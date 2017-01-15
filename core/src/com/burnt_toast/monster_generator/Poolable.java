package com.burnt_toast.monster_generator;

public abstract class Poolable {
	protected boolean inUse;
	
	public abstract void reset();//resets the object
	public abstract void retire();//retires object
	public boolean getIfInUse(){
		return inUse;
	}
	public void toggleInUse(){
		if(inUse)inUse = false;
		else inUse = true;
	}
}
