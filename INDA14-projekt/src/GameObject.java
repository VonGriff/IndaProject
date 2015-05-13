import org.newdawn.slick.*;


public class GameObject {
	
	public int VALUE = 0;
	public Image image;
	public GameObject next;
	private boolean ockupide;
	private static int xpos;
	private static int ypos;
	private int xcordinate;
	private int ycordinate;
	
	
	
	public GameObject(int posx, int posy){
		xpos = posx;
		ypos = posy;
		xcordinate = 290 + 60*posx;
		ycordinate = 450 + 50*posy;
	}
	
	public boolean isOkcupied(){
		return ockupide;
	}
	
	public GameObject getNext(){
		return next;
	}
	
	private void addImage(int randomInt) {
		
	}
	
	public void move(){
		if(next == null){
			next.image = this.image;
			next.VALUE = this.VALUE;
			this.image = null;
			this.VALUE = 0;
		}
							
	}
	
}
