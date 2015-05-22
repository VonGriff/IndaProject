import org.newdawn.slick.*;


public class GameObject {
	
	private Image image;
	private BlockType type;
	private boolean isMovingObject = true;
	
	
	public GameObject(Image img, BlockType type){
		this.image = img;
		this.type = type;
	}
	
	/**
	 * Returns the image of the object.
	 * @return
	 */
	public Image getImage() {
		return image;
	}
	
	
	public void stop(){
		isMovingObject = false;
	}
	/**
	 * Returns the isMovingObject variable.
	 * @return
	 */
	public boolean isMovingObject(){
		return isMovingObject;
	}
	/**
	 * Returns the enuem of the object.
	 * @return
	 */
	public BlockType getBlockType(){
		return type;
	}
		
}
