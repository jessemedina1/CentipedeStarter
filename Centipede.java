import java.awt.*;

import objectdraw.*;

public class Centipede extends ActiveObject {
    
    // offset between segments when they are created
    static private final int SEGMENT_OFFSET_X = 12;
    // pause time between moving all of the segments
    static private final int CENTIPEDE_PAUSE = 25;
    //number of segments in centipede
    private static final int CENTIPEDE_LENGTH = 15;
    //variable for the visibleImage of the segment
    VisibleImage segmentImage;
    //create array of Segments
    private Segment[] theSegments;
    //variable to the canvas
    private DrawingCanvas canvas;
    //variable for the zapper
    private Zapper theZapper;
    //variable to refer to the keeper
    private ScoreKeeper keeper;
    //variable for current location of theSegments
    private Location currentLocation;
    //variable for missile
    private Missile theMissile;
    /*
     * 
     */
    public Centipede(Segment[] theSegments, Zapper theZapper, Image segmentPic, Field theField, DrawingCanvas canvas) {
    	
    	this.theZapper = theZapper;
    	this.canvas = canvas;
    	//create the  array to store segments, of size 25
    	theSegments = new Segment[CENTIPEDE_LENGTH];
    	//for loop to fill entire array with Segment Images
    	for(int x=0; x<CENTIPEDE_LENGTH; x++){
    		//initialize each instance of array with segments
			theSegments[x]= new Segment(segmentPic,x*SEGMENT_OFFSET_X,0,theField,theZapper,keeper, canvas);
			
			
			
    	}	this.theSegments = theSegments;
    	
        start();
    }
    /*
     * run method for the centipede that calls step for every element in array of segments
     */
    public void run() {
    	
    	while(!theZapper.isDead()){
    		//traverse the array of segments, making each segment step
    		for (int a=0;a<CENTIPEDE_LENGTH;a++) {
    			//step each segment
    		theSegments[a].step();	
    		//get location of the segments
    		currentLocation = new Location (theSegments[a].segmentLoc());
    		
    		
    				
    		}pause(CENTIPEDE_PAUSE/3);
 
    	}
    	
    	
    	}
    
    public void hasBeenHit(){
    	
    }
    	
    public Location getLoc(){
    	return currentLocation;
    }
 
    
    
   
}
