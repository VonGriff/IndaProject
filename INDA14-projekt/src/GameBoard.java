import org.newdawn.slick.*;

import java.util.Random;

public class GameBoard {

	private GameObject[][] gameboard = new GameObject[4][9];
	private Image playingfield;
	private int objectsInMovement = 0;
	private Random random;
	private boolean hasMovingObjects;
	
	
	
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

			for(int b =0; b<gameboard[0].length; b++) { //�ndra b till 1 f�r att ha en rad som f�rblir or�rd

				GameObject currentObject = gameboard[i][b];
				if(currentObject != null) {
					g.drawImage(currentObject.getImage(), 70+i*70, 80+b*50);
				}
			}
		}
	}
	
	public void Insert(int row, int col, GameObject object) throws SlickException{
		gameboard[col][row] = object;
	}
	
	/**
	 * Skapar 2 figurer och ser till att de inte �r p� varandra.
	 * @throws SlickException
	 */
	public void spawnObjects() throws SlickException{
		random = new Random();
		int col = 0;
		int row1 = random.nextInt(4);
		int row2 = random.nextInt(4);
		
		if(row1 == row2){ //Ser till att inte b�da figurerna skapas p� samma position.
			while(row1 == row2){
			row2 = random.nextInt(4);
			}
		}
		
		GameObject obj1 = randomObject();
		GameObject obj2 = randomObject();
		
		Insert(col, row1, obj1);
		Insert(col, row2, obj2);
		
		
		
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
	
	public boolean hasMovingObjects(){
		for(int c = 0; c < gameboard.length; c++) {
			for(int i = gameboard[c].length-2; i >= 0; i--) {
				GameObject current = gameboard[c][i];
				if(current != null && gameboard[c][i+1] == null) {
					hasMovingObjects = true;
					return hasMovingObjects;
				}
					
			}
		}
		hasMovingObjects = false;
		return hasMovingObjects;
	}
	
	
	
	public GameObject randomObject() throws SlickException {
		GameObject temp = null;
		Image jokerTop = new Image("pics/joker-top.png");
		Image jokerBot = new Image("pics/joker-bot.png");
		Image hearts = new Image("pics/suits-hearts.png");
		Image clubs = new Image("pics/suits-clubs.png");
		Image spades = new Image("pics/suits-spades.png");
		Image diamond = new Image("pics/suits-diamond.png");
		
		int randObj = random.nextInt(10);
		
		if(randObj == 0)
			temp = new GameObject(jokerTop, BlockType.JOKERTOP);
		if(randObj == 1)
			temp = new GameObject(hearts, BlockType.HEART);
		if(randObj == 2)
			temp = new GameObject(hearts, BlockType.HEART);
		if(randObj == 3)
			temp = new GameObject(clubs, BlockType.CLUBS);
		if(randObj == 4)
			temp = new GameObject(clubs, BlockType.CLUBS);
		if(randObj == 5)
			temp = new GameObject(spades, BlockType.SPADE);
		if(randObj == 6)
			temp = new GameObject(spades, BlockType.SPADE);
		if(randObj == 7)
			temp = new GameObject(diamond, BlockType.DIAMOND);
		if(randObj == 8)
			temp = new GameObject(diamond, BlockType.DIAMOND);
		if(randObj == 9)	
			temp = new GameObject(jokerBot, BlockType.JOKERBOT);
	
			return temp;
	}	
}

