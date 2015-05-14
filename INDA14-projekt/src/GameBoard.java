import org.newdawn.slick.*;

import java.util.Random;


public class GameBoard {

	private GameObject[][] gameboard = new GameObject[4][9];
	private Image playingfield;
	
	public GameBoard(){
		
	}
	
	public void draw(Graphics g) throws SlickException{
		playingfield = new Image("pics/playingfield.png");
		g.drawImage(playingfield, 200, 400);
		
		for(int i = 0; i<gameboard.length; i++) { 
			for(int b =0; b<gameboard[0].length; b++) { //Ändra b till 1 för att ha en rad som förblir orörd.
				GameObject currentObject = gameboard[i][b];
				if(currentObject != null) {
					g.drawImage(currentObject.getImage(), 230+i*60, 400+b*50);
				}
			}
		}
	}
	
	public void Insert(int row, int col, GameObject object){
		gameboard[col][row] = object;
	}
	
	public void moveObjects() {
		for(int c = 0; c < gameboard.length; c++) {
			for(int i = gameboard[c].length-2; i >= 0; i--) {
				GameObject current = gameboard[c][i];
				if(gameboard[c][i+1] == null) {
					gameboard[c][i+1] = current;
					gameboard[c][i] = null;
	
				}
			}
		}
	}
}

