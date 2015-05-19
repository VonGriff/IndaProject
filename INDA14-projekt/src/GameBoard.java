import org.newdawn.slick.*;

import java.util.Random;

public class GameBoard {

	private GameObject[][] gameboard = new GameObject[4][9];
	private Image playingfield;
	private Random random;
	GameObject movingobj1 = null;
	GameObject movingobj2 = null;
	int place1 = 0;
	int place2 = 0;
	private boolean rowsAreFull;
	private int score = 0;
	
	private boolean retry;
	
	
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
	public void spawnObjects() throws SlickException {
		random = new Random();
		int col = 0;
		int row1 = random.nextInt(4);
		int row2 = random.nextInt(4);
		
		while(row1 == row2) {
			row2 = random.nextInt(4);
		}
		
		GameObject obj1 = randomObject();
		GameObject obj2 = randomObject();
		
		if(gameboard[row1][0] != null || gameboard[row2][0] != null ) {
			rowsAreFull = true;
		}else {
			Insert(col, row1, obj1);
			Insert(col, row2, obj2);
		}
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
				else if (current != null) {
					if(current.getBlockType() == BlockType.JOKERTOP && gameboard[c][i+1] != null){
						 if(gameboard[c][i+1].getBlockType() == BlockType.JOKERBOT){
							gameboard[c][i] = null;
							gameboard[c][i+1] = null;
							score += 40;
						 }else
							 if(gameboard[c][i+1].getBlockType() != BlockType.JOKERBOT){
								 gameboard[c][i] = null; 
							 }
					} else
					if(current.getBlockType() == gameboard[c][i+1].getBlockType()){
						gameboard[c][i] = null;
						gameboard[c][i+1] = null;
						score += 10;
					}else 
					current.stop();
				}
				else {
					if(gameboard[c][i+1] != null)
						if(gameboard[c][i+1].getBlockType() == BlockType.JOKERTOP)
							gameboard[c][i+1] = null;
						else
					gameboard[c][i+1].stop();
				}
			}
		}
	}
	
	public void swapColumns(int leftHand, int rightHand) throws SlickException {
		int a = leftHand;
		int b = rightHand;
		
		for(int i = gameboard[a].length - 1; i >= 0; i--){
			GameObject tempA = gameboard[a][i];
			GameObject tempB = gameboard[b][i];
			
			if (tempA == null && tempB == null) {
				continue;
			}else if (tempA == null && tempB != null) {
				if (tempB.isMovingObject()) {
					continue;
				}
			}else if (tempA != null && tempB == null) {
				if (tempA.isMovingObject()) {
					continue;
				}
			}else if (tempA != null && tempB != null) {
				if (tempA.isMovingObject() && tempB.isMovingObject()) {
					continue;
				}
			}
			gameboard[a][i] = gameboard[b][i];
			gameboard[b][i] = tempA;
		}
	}
	
	public boolean hasMovingObjects(){
		for(int c = 0; c < gameboard.length; c++) {
			for(int i = gameboard[c].length-2; i >= 0; i--) {
				GameObject current = gameboard[c][i];
				if(current != null && gameboard[c][i+1] == null) {
					return true;
				}
					
			}
		}
		return false;
	}
	
	public void stopMoving() {
		for(int c = 0; c < gameboard.length; c++) {
			for(int i = gameboard[c].length-2; i >= 0; i--) {
				GameObject current = gameboard[c][i];
				if(current != null && gameboard[c][i+1] != null) {
					current.stop();
					gameboard[c][i+1].stop();
				}		
			}
		}
		
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
	
	public boolean areRowsFull() {
		return rowsAreFull;
	}
	
	public void setRowsEmpty() {
		rowsAreFull = false;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean getRetry() {
		return retry;
	}
	
	public void setRetry() {
		retry = true;
	}
	
	public void clear() {
		if (retry) {
			for(int c = 0; c < gameboard.length; c++) {
				for(int i = gameboard[c].length-1; i >= 0; i--) {
					gameboard[c][i] = null;
				}
			}
			score = 0;
		}
		retry = false;
	}
}


