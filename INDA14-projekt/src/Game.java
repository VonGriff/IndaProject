import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	
	public static final String gameName = "Joker!";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int options = 2;
	public static final int gameOver = 3;

	//Lägger till game states
	public Game(String gameName){
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Options(options));
		this.addState(new GameOver(gameOver));
	}
	//Innehållet för game states
	public void initStatesList(GameContainer gc)throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(options).init(gc, this);
		this.getState(gameOver).init(gc, this);
		this.enterState(menu);
	}
	/*
	 * Main funktionen
	 * Sätter upplösningen till 800x600
	 * Aktiverar Vsync
	 */
	public static void main(String[] args){
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gameName));
			appgc.setDisplayMode(800, 600, false);
			appgc.setShowFPS(false);
			appgc.setAlwaysRender(true);	
			appgc.setVSync(true);
			appgc.start();
			
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}
