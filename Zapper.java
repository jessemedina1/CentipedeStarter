
import objectdraw.*;

import java.awt.*;


public class Zapper {
	//variables for zapper height, width
	private static final double ZAPPERHEIGHT = 15;
	private static final double ZAPPERWIDTH = 20;
	private static final double GUNWIDTH = 3;
	private static final double GUNHEIGHT = 10;
	//move zapper by mushroom width
	private static final double MUSHROOMWIDTH = 16;
	//variables for areas to start drawing of zapper and gun
	private static final Location ZAPPERTOPLEFT = new Location (190,485);
	private static final Location GUNTOPLEFT = new Location (198,475);
	
	//filled rects that make up the zapper
	private FilledRect zapperBody;
	private FilledRect gun;
	
	//boolean that determines whether or not the zapper has been touched by centipede
	private boolean movable;
	
	private DrawingCanvas canvas;
	//variable for lost game message
	private Text lossMessage;
	//variable to use zapper
	private Zapper theZapper;
	//variable for theMissile
	private Missile theMissile;
/*
 * @param zapperBody - body of zapper
 * @param gun - gun of zapper
 * @param aCanvas - canvas  
 * @param loseMessage - message that shows the game is lost
 */
	public Zapper( DrawingCanvas aCanvas, Text loseMessage){
		lossMessage = loseMessage;
		canvas = aCanvas;
		
		
		gun = new FilledRect(GUNTOPLEFT,GUNWIDTH, GUNHEIGHT,canvas  );
		gun.setColor(Color.YELLOW);
		
		zapperBody = new FilledRect(ZAPPERTOPLEFT,ZAPPERWIDTH,ZAPPERHEIGHT,canvas);
		zapperBody.setColor(Color.YELLOW);
		movable = true;
	}
	/*
	 * Determine whether or not the zapper has come into contact with the centipede
	 * @param segmentImage - any segment of centipede to check against
	 * @return true if centipede and gun overlap
	 */
	public boolean overlaps (VisibleImage segmentImage){
		return gun.overlaps(segmentImage);
	}
	
	/*
	 * determine whether or not the game is ongoing, and the zapper is still movable
	 * @return true if the zapper is still movable
	 */
	/*public  boolean isMovable(){
		return movable = true;
	}*/
	
	/*
	 * Killing the zapper stops it from moving, and displays the lost game message
	 */
	public boolean isDead(){
		return movable = false;
	}
	/*
	 * move the zapper to the left
	 * @param xChange is the zapperWidth
	 * @param yChange
	 */
	public void moveLeft(){
		if (zapperBody.getX() > 0){
		zapperBody.move(-MUSHROOMWIDTH,0);
		gun.move(-MUSHROOMWIDTH, 0);
		}
	}
	
	/*
	 * move the zapper to the right
	 * @param xChange is the zapperWidth
	 * @param yChange
	 */
	public void moveRight (){
		if(zapperBody.getX() + ZAPPERWIDTH < canvas.getWidth()){
			zapperBody.move(MUSHROOMWIDTH,0);
			gun.move(MUSHROOMWIDTH, 0);
		}
		
	}
	/*
     * method to make the zapper disappear
     */
	public void byeZapper(){
		zapperBody.hide();
		gun.hide();
	}
	
	/**
	 * Move the zapper towards the specified point.
	 * 
	 * @param point is the point where mouse is clicked
	 *           
	 */
	public void mouseToward(Location point) {

		if (point.getX() < zapperBody.getX()) {
			zapperBody.move(-MUSHROOMWIDTH, 0);
			gun.move(-MUSHROOMWIDTH, 0);
		} else if (point.getX() > zapperBody.getX()) {
			zapperBody.move(MUSHROOMWIDTH, 0);
			gun.move(MUSHROOMWIDTH, 0);		
		}
	}
    /*
     * create a missile when you shoot is invoked at the gun point of x
     * @param theField - passing theField to the missile to ask if a shroom has been hit
     * @param theCentipede - passing theCentipede to the missile to ask if a segment has been hit
     */
	public void shoot(double x, double y, Field theField, Centipede theCentipede){
		new Missile(x,y,theField, theCentipede, canvas);	
	}
	/*
	 * method to determine where to begin the missile active object by getting gunpoint x location
	 */
	public double getGunPointX() {
		return gun.getX();
	}
	/*
	 * method to determine where to begin missile active object by getting gunpoint y location
	 */
	public double getGunPointY(){
		return gun.getY();
		
	}
}