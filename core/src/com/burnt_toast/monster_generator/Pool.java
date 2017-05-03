package com.burnt_toast.monster_generator;

import java.util.LinkedList;
import com.burnt_toast.dungeons_n_stuff.Monster;
import com.burnt_toast.dungeons_n_stuff.MonsterPlaceholder;
import com.burnt_toast.dungeons_n_stuff.MonsterPlaceholder.MonsType;


public  class Pool<G extends Poolable> {
	
		private LinkedList<G> retiredStuff;
		G temp;
		
		public Pool(){
			retiredStuff = new LinkedList<G>();
			
		}
		
		public void retireObject(G obj){
			retiredStuff.add(obj);
		}
		
		public G getObject(){
			if(retiredStuff.size() == 0)return null;
			return retiredStuff.removeFirst();
		}
		
//		public <T extends Poolable> T makeAnObject(MonsType type){
//			switch(type){
//			case SLIME:
//				return new Slime();
//				break;
//			}
//		}
		

	
}
