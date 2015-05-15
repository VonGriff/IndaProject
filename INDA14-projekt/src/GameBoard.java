import org.newdawn.slick.*;

import java.util.Random;


public class GameBoard {

	private GameObject[][] gameboard = new GameObject[4][9];
	private Image playingfield;
	
	public GameBoard(){
		
	}
	/**
	 * Ritar upp spelbrädet samt objekten på spelbrädet.
	 * Itererar över hela spelbrädet och tittar på varje objekt,
	 * om objektet inte är null så ritar den upp bilden på den positionen
	 * som objektet är på.
	 * 
	 * att b = 1 i den inre for loopen ger oss en rad över denna som kan agera som en "spawn" punkt,
	 * för nya objekt. Där objekten sitter och väntar tills inga objekt är i rörelse längre, och i sådana fall
	 * sätter den de nya objekten i rörelse.
	 * @param g
	 * @throws SlickException
	 */
	public void draw(Graphics g) throws SlickException{
		playingfield = new Image("pics/playingfield.png");
		g.drawImage(playingfield, 40, 80);
		
		for(int i = 0; i<gameboard.length; i++) { 
<<<<<<< HEAD
			for(int b =0; b<gameboard[0].length; b++) { //ï¿½ndra b till 1 fï¿½r att ha en rad som fï¿½rblir orï¿½rd.
=======
			for(int b =1; b<gameboard[0].length; b++) { //Ändra b till 1 för att ha en rad som förblir orörd
>>>>>>> f2c493e7d37deba7e214a1e0c59fea11be094680
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
	 * Itererar över hela spelbrädets matris och ser vilka objekt som finns och ifall
	 * de har objekt under sig, är det ett null objekt under ett icke null objekt så flyttar den det objektet
	 * en rad neråt. Den gör detta tills dess att den kommit till slutet på brädet, eller den stöter ihop
	 * med ett annat objekt. Detta är kollisionshanteraren i detta program.
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

