import org.newdawn.slick.*;

import java.util.Random;

public class GameBoard {

	private GameObject[][] gameboard = new GameObject[4][9];
	private Image playingfield;
	private Random random;
	private boolean hasMovingObjects;
	private int rightHand;
	private int leftHand;
	
	
	
	/**
	 * Ritar upp spelbrï¿½det samt objekten pï¿½ spelbrï¿½det.
	 * Itererar ï¿½ver hela spelbrï¿½det och tittar pï¿½ varje objekt,
	 * om objektet inte ï¿½r null sï¿½ ritar den upp bilden pï¿½ den positionen
	 * som objektet ï¿½r pï¿½.
	 * 
	 * att b = 1 i den inre for loopen ger oss en rad ï¿½ver denna som kan agera som en "spawn" punkt,
	 * fï¿½r nya objekt. Dï¿½r objekten sitter och vï¿½ntar tills inga objekt ï¿½r i rï¿½relse lï¿½ngre, och i sï¿½dana fall
	 * sï¿½tter den de nya objekten i rï¿½relse.
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
	
	public void Insert(int row, int col, GameObject object) throws SlickException{
		gameboard[col][row] = object;
	}
	
	/**
	 * Skapar 2 figurer och ser till att de inte är på varandra.
	 * @throws SlickException
	 */
	public void spwanObjects() throws SlickException{
		random = new Random();
		int col = 0;
		int row1 = random.nextInt(4);
		int row2 = random.nextInt(4);
		
			while(row1 == row2){
			row2 = random.nextInt(4);
			}
		
		
		GameObject obj1 = randomObject();
		GameObject obj2 = randomObject();
		
		Insert(col, row1, obj1);
		Insert(col, row2, obj2);
		
		
		
	}
	/**
	 * Itererar ï¿½ver hela spelbrï¿½dets matris och ser vilka objekt som finns och ifall
	 * de har objekt under sig, ï¿½r det ett null objekt under ett icke null objekt sï¿½ flyttar den det objektet
	 * en rad nerï¿½t. Den gï¿½r detta tills dess att den kommit till slutet pï¿½ brï¿½det, eller den stï¿½ter ihop
	 * med ett annat objekt. Detta ï¿½r kollisionshanteraren i detta program.
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
	
	public void swapColumns(int leftHand, int rightHand) {
		int a = leftHand;
		int b = rightHand;
		
		GameObject[] temp1 = new GameObject[gameboard[a].length];
		GameObject[] temp2 = new GameObject[gameboard[b].length];
		
		for(int i = 0; i < gameboard[a].length; i++){
			temp1[i] = gameboard[a][i];
			temp1[i] = gameboard[a][i];
		}
		
		for(int i = 0; i < gameboard[b].length; i++){
			gameboard[b][i] = temp1[i];
			gameboard[a][i] = temp2[i];
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

