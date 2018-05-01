
import objectdraw.*;
import java.awt.*;

public class Segment {

    // width of segment image 
    static private final int SEGMENT_WIDTH = 16;  

    // number of pixels a segment should travel in the X direction 
    static private final int SEGMENT_STEP_X = 4;
    
    // number of pixels a segment should travel in the Y direction
    static private final int SEGMENT_STEP_Y = 16;
 
    //variable for the drawing canvas on which the segment appears
    private DrawingCanvas canvas;
    //variable for the 'visibleImage' of the segment
    VisibleImage segmentImage;
    //variable for the zapper
    private Zapper theZapper;
    //variable for the field
    private Field theField;
    //variable for the missile
    private Missile theMissile;
    //variable for the scorekeeper
    private ScoreKeeper keeper;
   //booleans to manipulate which way the segment should move
    private boolean movingRight = true;
    private boolean movingLeft = false ;
    private boolean movingDown = false;
    
    
    /*
     * constructor for the segments
     * @param - theZapper is the zapper at the bottom of screen
     * @param - x the x coordinate of the segment
     * @param - y the y coordinate of the segment
     * @param theField is the mushrrom field
     * @param canvas is the drawing canvas
     */
    public Segment(Image segmentPic, double x, double y, Field theField, Zapper theZapper, ScoreKeeper keeper,DrawingCanvas canvas){
    	this.keeper = keeper;
    	this.canvas = canvas;
    	this.theZapper = theZapper;
    	this.theField = theField;
    	//create the visible image
    	segmentImage = new VisibleImage(segmentPic,x,y,canvas);	
    }
    /*
     * method to move the segment by steps of size of the mushroom 
     */
    public void step(){
    	if(!theZapper.isDead()){
    	this.enableDirectionChange();
    	
    	if(movingRight){
    		segmentImage.move(SEGMENT_STEP_X, 0);
    	}else if(movingLeft){
    		segmentImage.move(-SEGMENT_STEP_X,0);
    		}else if(movingDown){
    			segmentImage.move(0,SEGMENT_STEP_Y);
    		}
    	}
    }
    /*
     * this method enables the direction change of the segment
     */
    public void enableDirectionChange(){
    	//if the segment is on the right edge of the screen and moving right
    	//or the segment is moving left and near the left edge of the screen
    	//stop it from moving right or left and move it down
    	if (segmentImage.getX()==(canvas.getWidth()-SEGMENT_WIDTH) && movingRight || segmentImage.getX()==0 && movingLeft){
    		movingRight = false;
    		movingLeft = false;
    		movingDown = true;
    	//if the segment is moving down and it is near the right edge of the screen	
    	//stop it from moving down and the right and move it to the left
    	}else if (movingDown && segmentImage.getX()==(canvas.getWidth() - SEGMENT_WIDTH)){
    		movingRight = false;
    		movingDown = false;
			movingLeft = true;
		//if it is moving down and the segment is near the left edge
		//tell it to move to the right and stop it from moving down
    	}else if((movingDown && segmentImage.getX()==0)){
	    	//movingLeft = false;
    		movingDown = false;
	    	movingRight = true;
	    //if it moving right and it sees a mushroom, move it left	
	    }else if (movingRight && theField.shroomIsVisible(segmentImage.getX(),segmentImage.getY())){
	    	
	    	movingRight = false;
	    	movingLeft = true;
	    	//if it is moving left and sees a mushroom move it right
	    }else if (movingLeft && theField.shroomIsVisible(segmentImage.getX(),segmentImage.getY())){
	    	movingLeft = false;
	    	movingRight = true;
	    }
    }
    
    /*
     * method to kill the segments
     */
    public void killZapper(){
    	if(theZapper.overlaps(segmentImage)){
    		theZapper.isDead();//tell zapper it is dead by setting movable to false in zapper class
    		theZapper.byeZapper();//remove Zapper in insteresting way
    	}
    }
     
    /*
     * method to remove segments from canvas
     */
    public void kill(){
    	keeper.hitSegment();
    	segmentImage.removeFromCanvas();
	   }
 
    /*
     * method to get Segment Location
     * 
     */
    public Location segmentLoc(){
    	return segmentImage.getLocation();
    }
   
}