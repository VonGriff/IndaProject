import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{

	Image playingfield;
	private GameObject previous = null;
	
	public Play(int state){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playingfield = new Image("pics/playingfield.png");
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(playingfield, 200, 400);
		for(int i = 0; i<4; i++){ 
		for(int b =0; b<9; b++){
		g.drawRect(230, 400, 60 + 60*i, 50 + 50*b);
		GameObject node = new GameObject(i,b);
		if(previous != null){
			previous.next = node;
			previous = node;
		}else
		previous = node; //Should only happen once per column. 
		}
		}
		//pixelstorlek för de små rutorna x = 60 y = 50.
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
	}
	
	public void createBackendField() {
		
	}
	
	public int getID(){
		return 1;
	}
}
