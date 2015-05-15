import org.newdawn.slick.*;

import java.util.Random;


public class GameBoard {

	private GameObject[][] gameboard = new GameObject[4][9];
	private Image playingfield;
	
	public GameBoard(){
		
	}
	/**
	 * Ritar upp spelbr�det samt objekten p� spelbr�det.
	 * Itererar �ver hela spelbr�det och tittar p� varje objekt,
	 * om objektet inte �r null s� ritar den upp bilden p� den positionen
	 * som objektet �r p�.
	 * 
	 * att b = 1 i den inre for loopen ger oss en rad �ver denna som kan agera som en "spawn" punkt,
	 * f�r nya objekt. D�r objekten sitter och v�ntar tills inga objekt �r i r�relse l�ngre, och i s�dana fall
	 * s�tter den de nya objekten i r�relse.
	 * @param g
	 * @throws SlickException
	 */
	public void draw(Graphics g) throws SlickException{
		playingfield = new Image("pics/playingfield.png");
		g.drawImage(playingfield, 40, 80);
		
		for(int i = 0; i<gameboard.length; i++) { 

			for(int b =1; b<gameboard[0].length; b++) { //�ndra b till 1 f�r att ha en rad som f�rblir or�rd

				GameObject currentObject = gameboard[i][b];
				if(currentObject != null) {
					g.drawImage(currentObject.getImage(), 60+i*60, 80+b*50);
				}
			}
		}
	}
	
	public void Insert(int row, int col, GameObject object){
		gameboard[col][row] = object;
	}
	/**
	 * Itererar �ver hela spelbr�dets matris och ser vilka objekt som finns och ifall
	 * de har objekt under sig, �r det ett null objekt under ett icke null objekt s� flyttar den det objektet
	 * en rad ner�t. Den g�r detta tills dess att den kommit till slutet p� br�det, eller den st�ter ihop
	 * med ett annat objekt. Detta �r kollisionshanteraren i detta program.
	 */
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

