import org.newdawn.slick.*;


public class GameObject {
	
	private int value = 5;
	private Image image;
	private BlockType type;
	private boolean isMovingObject = true;
	
	
	public GameObject(Image img, BlockType type){
		this.image = img;
		this.type = type;
	}
	
	public int getValue(){
		return value;
	}
	
	public Image getImage() {
		return image;
	}
	
	public int testColour(BlockType type){
		
		switch(type) {
		case JOKERTOP:
			return 0;
		case JOKERBOT:
			return 1;
		case HEART :
			return 2;
		case SPADE :
			return 3;
		case CLUBS :
			return 4;
		case DIAMOND :
			return 5;
		}
		return -1; //Should never happen.
	}
	
	public void setMovingObject(){
		isMovingObject = false;
	}
	
	public boolean isMovingObject(){
		return isMovingObject;
	}
		
}
