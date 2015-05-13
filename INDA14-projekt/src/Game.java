import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{
	
	public static final String gameName = "Joker!";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int options = 2;

	public Game(String gameName){
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Options(options));
	}
	public void initStatesList(GameContainer gc)throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(options).init(gc, this);
		this.enterState(menu);
	}
	public static void main(String[] args){
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gameName));
			appgc.setDisplayMode(1920, 1080, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}