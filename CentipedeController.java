import objectdraw.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CentipedeController extends WindowController implements KeyListener {
	private static final Location TOPLEFT = new Location (0,0);
	
	private static final Location MESSAGELOCATION = new Location (200,300);
	//variables to refer to theZapper, theCentipede, theField and segment array
	private Zapper theZapper;
	private Field theField;
	private Centipede theCentipede;
	private Segment[] theSegments;
	private ScoreKeeper keeper;
	private String messages;
	private int points;
    
	private Text lossMessage = new Text("GAME OVER", MESSAGELOCATION,
			canvas);
    public void begin() {
        Container container = getContentPane();
        // create game
        //get images necessary
        Image mushroomPic = getImage("shroom1.gif");
        Image segmentPic = getImage("segment2.gif");
        //create field background
       FilledRect Background = new FilledRect(TOPLEFT, canvas.getWidth(), 500, canvas);
       Background.sendToBack();
        //create the field 
       theField = new Field(keeper, mushroomPic,canvas);
        //create zapper
        theZapper = new Zapper(canvas,lossMessage);
       //create centipede
        theCentipede = new Centipede(theSegments, theZapper, segmentPic, theField, canvas);
        keeper = new ScoreKeeper( canvas);
        // set up key events.
        canvas.addKeyListener(this);
        container.addKeyListener(this);
        container.requestFocus();
        container.validate();
        setFocusable(true);
    }
    
    /**
	 * mouse zapper towards the new cursor position
	 * @param point - mouse coordinates at time of mouse press
	 */
    public void onMouseClick(Location point){
    	  if (!theZapper.isDead()) {
  			theZapper.mouseToward(point);
  		}
    }
    
    public void onMousePress(Location point) {
        requestFocus();
      
    }


    // Fill in this method as necessary.  Make sure nothing
    // happens if the game is over!
    public void keyPressed(KeyEvent event) {
    	if(!theZapper.isDead()){
    		 if (event.getKeyCode() == KeyEvent.VK_LEFT ) {
    	        	theZapper.moveLeft();
    	        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
    	        	theZapper.moveRight();
    	        } else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
    	        	theZapper.shoot(theZapper.getGunPointX(),theZapper.getGunPointY(),theField, theCentipede);

    	        }
    	    }
    	}
    		
    
       
    
    // Part of KeyListener, but we don't need 
    // to do anything here
    public void keyTyped(KeyEvent evt) {  }
    
    // Part of KeyListener, but we don't need 
    // to do anything here
    public void keyReleased(KeyEvent evt) {  }
}
    
    
