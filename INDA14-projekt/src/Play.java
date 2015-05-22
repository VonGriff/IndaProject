import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.FadeInTransition;

import java.util.Random;
import java.util.Timer;
public class Play extends BasicGameState{

	Image playingfield;
	Image scorefigure;
	Timer timer = new Timer();
	
	int speed = 750;
	int levelScore = 100;
	private int timeSinceMove = 0;
	
	Player player;
	
	Random random;
	private static GameBoard gameboard = new GameBoard();
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		player = new Player();
		scorefigure = new Image("pics/harley_score.png");
		
	}
	
	/**
	 * Ritar upp spelbrädet, spelaren, bilden till höger som håller i poängen,
	 * poängen självt och kontrollerar om figurerna ska sluta röra på sig
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gameboard.draw(g);
		player.draw(gc, g);
		scorefigure.draw(400, 250);
		
		g.drawString("Score: " + gameboard.getScore(), 500, 250);
		
		gameboard.stopMoving();
		
	}
	
	//Uppdaterar
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		timeSinceMove += delta;

		//Sätter speed till 750 då man trycker retry efter GameOver
		if (gameboard.getRetry()) {
			speed = 750;
		}
		
		//Kollar om spelbrädet behöver rensas
		gameboard.clear();

		//Ökar hastigheten vid vissa intervall
		speed = incLevel();
		
		
		if(gameboard.hasMovingObjects() != true){
			gameboard.spawnObjects();
		}

		//Hanterar delay på när figurerna ska röra sig
		if(timeSinceMove >= speed) {
			timeSinceMove = 0;
			gameboard.moveObjects();
		}
		//Uppdaterar spelarens koordinater
		if(player.movePlayer(gc)) {
			gameboard.swapColumns(player.getLeftHand(), player.getRightHand());
		}
		
		//Kollar om det spelet är slut
		if(gameboard.areRowsFull()) {
			sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
	}

	
	public int getID(){
		return 1;
	}
	
	public static GameBoard getBoard()  {
		return gameboard;
	}
	
	//Ökar hastigheten var hundrade poäng
	public int incLevel() {
		int score = gameboard.getScore();
		if (score >= levelScore && !(speed < 0)) {
			levelScore += 100;
			return speed -= 100;
		}
		return speed;
	}
}
