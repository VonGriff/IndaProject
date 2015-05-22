import org.newdawn.slick.*;

public class Player {
	
	Image player;
	Image playerFront = new Image("pics/batman_1.0.png");
	Image playerBack = new Image("pics/batman_1.0_back.png");
	boolean faceBack;
	
	private int leftHand = 0;
	private int rightHand = 1;
	float playerX = 65;


	
	public Player() throws SlickException {
		
	}
	
	//Ritar spelaren och kollar ifall ansiktet eller ryggen ska vara vänd utåt
	public void draw(GameContainer gc, Graphics g) throws SlickException {
		if (faceBack) {
			player = playerBack;
		}else {
			player = playerFront;
		}

		player.draw(playerX, 540);
	}
	
	/**
	 * Flyttar spelaren höger och vänster
	 * Satt som boolean för att draw ska veta om ryggen eller fronten ska visas
	 * @param gc
	 * @return
	 */
	public boolean movePlayer(GameContainer gc) {
		Input input = gc.getInput();
		int moveDistance = 80;
		
		//Flyttar batman till höger
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			playerX += moveDistance;
			leftHand += 1;
			rightHand +=1;
			if (playerX > 245) {
				playerX -= moveDistance;	
				leftHand -= 1;
				rightHand -=1;
			}
			
		}
		
		//Flyttar batman till vänster
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			playerX -= moveDistance;
			leftHand -= 1;
			rightHand -=1;
			if (playerX < 65) {
				playerX += moveDistance;
				leftHand += 1;
				rightHand +=1;
			}
		}
		
		//Vid swap får batman att vända sig 180 grader
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			if (faceBack) {
				faceBack = false;
			} else {
				faceBack = true;
			}
			return true;
		}
		
		return false;
	}
	
	public float getPlayerX() {
		return playerX;
	}
	
	public void setPlayerX(float posX) {
		playerX = posX;
	}
	
	public int getRightHand(){
		return rightHand;
	}
	
	public int getLeftHand(){
		return leftHand;
	}
}
