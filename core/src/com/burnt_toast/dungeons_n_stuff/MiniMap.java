package com.burnt_toast.dungeons_n_stuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MiniMap {
	private int[][] textMap;
	boolean visible;
	int[][] visibilityMap;
	TextureRegion seenTilePic;
	TextureRegion unseenTilePic;
	TextureRegion youAreHerePic;
	
	private Vector2 playerXnY;
	
	float midOfScreenX;
	float midOfScreenY;
	
	float areHereTimer;
	int blockSightDistance;//how many blocks can be seen
	float miniTileSize;
	float mapAlphaLevel;
	
	public MiniMap(TextureRegion passSeenPixel, TextureRegion passUnseenPixel, TextureRegion youAreHerePixel,
			float passMidOfScreenX, float passMidOfScreenY){
		seenTilePic = passSeenPixel;
		unseenTilePic = passUnseenPixel;
		youAreHerePic = youAreHerePixel;
		midOfScreenY = passMidOfScreenY;
		midOfScreenX = passMidOfScreenX;
		//if map is below a certain size, 5 pixels is a good size
		miniTileSize = 5;
		playerXnY = new Vector2();
		blockSightDistance = 4;//how many blocks visible in any direction
		
	}
	
	/**
	 * if verbose is true, it will display the new map
	 *  in the console before setting it in the object.
	 * @param newTextMap
	 * @param verbose
	 */
	public void setMapVerbose(int[][] newTextMap, boolean verbose){
		for(int i = 0; i < newTextMap.length; i++){
			for(int k = 0; k < newTextMap[0].length; k++){
				System.out.print(newTextMap[i][k]);
			}
			System.out.println();
		}
		setMap(newTextMap);
	}
	public void setMap(int[][] newTextMap){
		textMap = newTextMap;
		visibilityMap = newTextMap;
		visibilityMap[1][1] = 9;
		}
	public void setMapAlphaLevel(float passAlphaLevel){
		mapAlphaLevel = passAlphaLevel;
	}
	public boolean getIfOnScreen(){
		return visible;
	}
	public void setIfOnScreen(boolean isVisible){
		visible = isVisible;
	}
	public boolean toggleIfOnScreen(){
		if(visible){
			visible = false;
			return false;
		}
		else{
			visible = true;
			return true;
		}
	}
	public void setMidOfScreen(float passMidOfScreenX, float passMidOfScreenY){
		this.midOfScreenX = passMidOfScreenX;
		this.midOfScreenY = passMidOfScreenY;
	}
	public void activateBlock(int x, int y){
		if(playerXnY.x == x && playerXnY.y == y){
			return;
		}
		playerXnY.x = x;
		playerXnY.y = y;
		System.out.println("Passed : " + x + " to activate as X");
		System.out.println("Passed : " + y + " to activate as Y");
		if(textMap[x][y+1] == 0){//if UP is not a wall
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x][y+1+i] == 0){
					visibilityMap[x][y+1+i] = 9;//set visible
				}
				else if(textMap[x][y+1+i] == 1){//if I reached a wall
					break;
				}
			}
		}//end if UP is available
		if(textMap[x][y-1] == 0){//if DOWN is not a wall or already seen
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x][y-1-i] == 0){
					visibilityMap[x][y-1-i] = 9;//set visible
				}
				else if(textMap[x][y-1-i] == 1){
					break;
				}
			}
		}//end if DOWN is available
		if(textMap[x+1][y] == 0){//if RIGHT is not a wall or already seen
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x+1+i][y] == 0){
					visibilityMap[x+1+i][y] = 9;//set visible
				}
				else if(textMap[x+1+i][y] == 1){
					break;
				}
			}
		}//end if RIGHT is available
		if(textMap[x-1][y] == 0){//if LEFT is not a wall or already seen
			for(int i = 0; i < blockSightDistance; i++){
				if(textMap[x-1-i][y] == 0){
					visibilityMap[x-1-i][y] = 9;//set visible
				}
				else if(textMap[x-1-i][y] == 1){
					break;
				}
			}
		}//end if LEFT is available
	}//end activate tile method
	
	public void update(){
		if(!visible)return;//don't do the math if I'm not on screen
		this.areHereTimer += Gdx.graphics.getDeltaTime();
		if(areHereTimer > 1){
			this.areHereTimer = 0;
		}
	}
	
	public void draw(SpriteBatch batch){
		if(!visible)return;
		for(int i = textMap.length-1; i >=0; i --){
			for( int k = 0; k < textMap[0].length; k++){//textMap[0] because the width & height is same
				if(visibilityMap[i][k] == 1 || visibilityMap[i][k] == 0){
					batch.draw(this.unseenTilePic,
							midOfScreenX - (textMap[0].length * miniTileSize / 2) + miniTileSize * i,
							midOfScreenY - (textMap[0].length * miniTileSize / 2) + miniTileSize * k,
							miniTileSize, miniTileSize);
				}//end if wall or unseen
				if(visibilityMap[i][k] == 9){//if it's visible
					batch.draw(this.seenTilePic,
							midOfScreenX - (textMap[0].length * miniTileSize / 2) + miniTileSize * i,
							midOfScreenY - (textMap[0].length * miniTileSize / 2) + miniTileSize * k,
							miniTileSize, miniTileSize);
				}//end if visible
				if(i == playerXnY.x && k == playerXnY.y && areHereTimer <= 0.5f){
					batch.draw(this.youAreHerePic,
							midOfScreenX - (textMap[0].length * miniTileSize / 2) + miniTileSize * i,
							midOfScreenY - (textMap[0].length * miniTileSize / 2) + miniTileSize * k,
							miniTileSize, miniTileSize);
				}
			}//end for k
		}//end for i
	}//end draw
}
