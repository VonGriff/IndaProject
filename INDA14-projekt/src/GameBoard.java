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
	 * Ritar upp spelbrï¿½det samt objekten pï¿½ spelbrï¿½det.
	 * Itererar ï¿½ver hela spelbrï¿½det och tittar pï¿½ varje objekt,
	 * om objektet inte ï¿½r null sï¿½ ritar den upp bilden pï¿½ den positionen
	 * som objektet ï¿½r pï¿½.
	 * 
	 * @param g
	 * @throws SlickException
	 */
	public void draw(Graphics g) throws SlickException{
		playingfield = new Image("pics/playingfield.png");
		g.drawImage(playingfield, 40, 80);
		
		for(int i = 0; i<gameboard.length; i++) { 

			for(int b =0; b<gameboard[0].length; b++) { //ï¿½ndra b till 1 fï¿½r att ha en rad som fï¿½rblir orï¿½rd

				GameObject currentObject = gameboard[i][b];
				if(currentObject != null) {
					g.drawImage(currentObject.getImage(), 70+i*70, 80+b*50);
				}
			}
		}
	}
	/**
	 * Sätter in det inskickade objektet på den angivna platsen.
	 * Platsen fås från row och col variablarna.
	 * @param row
	 * @param col
	 * @param object
	 * @throws SlickException
	 */
	public void Insert(int row, int col, GameObject object) throws SlickException{
		gameboard[col][row] = object;
	}
	
	/**
	 * Skapar 2 figurer och ser till att de inte ï¿½r pï¿½ varandra.
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
	 * Itererar ï¿½ver hela spelbrï¿½dets matris och ser vilka objekt som finns och ifall
	 * de har objekt under sig, om det ett null objekt under ett icke null objekt sï¿½ flyttar den det objektet
	 * en rad nerï¿½t. Den gï¿½r detta tills dess att den kommit till slutet pï¿½ brï¿½det, eller den stï¿½ter ihop
	 * med ett annat objekt. Detta ï¿½r kollisionshanteraren i detta program.
	 * 
	 * Om ett object har "stannat" så kollar denna metod ifall objektet under är av samma typ som det nyligen fallande
	 * objektet. Om det är samma så sätts båda dessa till null och score variabeln ökar. Gör en speciell koll för super
	 * blocken. 
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
							score += 50;
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
	/**
	 * This method takes two integer inputs, the left and the right hand. These
	 * point to different columns in the matrix that is our playing field.
	 * 
	 *  Using these they will swap the rows one by one until it has made it through the entire
	 *  column. If the object that is supposed to be swaped is moving it will be skipped and remain in
	 *  the same place as before. If a falling objects neighboring object (the one it will be swaped with) is not null,
	 *  it will swap the objects. This is so the stacks wont break when you swap with an falling object that has a neighboring
	 *  object that is not null.
	 * @param leftHand
	 * @param rightHand
	 * @throws SlickException
	 */
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
	/**
	 * This method searches through the board and checks if there are objects in motion,
	 * an object is said to be in motion if the current object is not null and the object beneth
	 * it is equal to null.
	 * @return
	 */
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
	/**
	 * This methods scans the board and looks for moving objects and,
	 * more importantly, if an object is supposed to be classed as not moving.
	 * This was sometimes not something that happend directly which resulted in
	 * some weird gameplay bugs.
	 */
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
	
	
	/**
	 * This method returns a randomly made GameObject to be inserted into then
	 * gameboard. The chance for the special figures to be made is smaller than the
	 * chance for the usual once.
	 * @return
	 * @throws SlickException
	 */
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


