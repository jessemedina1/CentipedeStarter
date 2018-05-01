import java.awt.*;
import objectdraw.*;

public class Missile extends ActiveObject {
	// Distance missile should move after each iteration
	private static final int DISTANCETOMOVE = -16;
	//variable for delay time
	private static final int DELAYTIME = 10;
	//variables for missile size
	private static final int MISSILEWIDTH = 4;
	private static final int MISSILEHEIGHT = 8;
	
	//the filled rect that is the missile
	private FilledRect theMissile;
	//variable for the drawing canvas
	//private DrawingCanvas canvas;
	//variable to refer to the field of mushrooms
	private Field theField;
	//variable to refer to centipede
	private Centipede theCentipede;
	//variable for the scorekeeper
	private ScoreKeeper keeper;
	//boolean that determines if missile is still in flight
	private boolean inFlight;
	//currentLocation of the missile 
	private Location currentLocation;
	
	/*
	 * the constructor of the missile enables it to communicate with the zapper that shoots it, 
	 * the field of mushrooms,
	 * the segments of the centipede
	 * @param - theZapper is the zapper
	 * @param - theField is the field of mushrooms
	 * @param - theSegment is the segment of the caterpillar
	 */
	
    public Missile(double x, double y, Field theField, Centipede theCentipede,DrawingCanvas canvas) {
    	
    	this.theField = theField;
    	this.theCentipede = theCentipede;
    	
    	//create the missile
    	theMissile = new FilledRect(x,y,MISSILEWIDTH, MISSILEHEIGHT,canvas );
    	theMissile.setColor(Color.WHITE);
    	inFlight = true;
        start();
    }
    /*
     * the missile moves upwards until it hits something in the playing area
     * when it hits something or reaches the top of playing area, remove from canvas
     * 
     */
    public void run() {
    	//as long as the boolean inFlight is true, meaning the missile has not hit anything, and it is also below the top of the canvas, move it up
    	while(inFlight && theMissile.getY()>0){
    		theMissile.move(0,DISTANCETOMOVE);
    		
    				//pause for smooth movement
    				pause(DELAYTIME);
    				this.missileLoc();
    		
    		if(theMissile.getY()<=0){
    			theMissile.hide();
    		}else if (this.missileLoc() == theCentipede.getLoc() ){
    			
    			theMissile.hide();
    		}
    	
    	
    	
    	//ask the field if a shroom is visible at the coordinates of the missile
    	if(theField.shroomIsVisible(theMissile.getX(),theMissile.getY())){  
    		theField.hideShroom(theMissile.getX(),theMissile.getY());
    		//call in flight to be false and hide the missile
    		inFlight = false;
    		theMissile.hide();
    		
    	}
    	}
    }
    
    /*
     * method to see if the missile has been hidden yet in zapper class
     */
    public boolean isHidden(){
    	return theMissile.isHidden();
    }
    
    /*
     * method to verify location of missile
     */
    public Location missileLoc(){
    	return theMissile.getLocation();
    }
}

