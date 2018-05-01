import objectdraw.*;
import javax.swing.*;
import java.awt.*;


public class ScoreKeeper {
	private static final Location txtLocation = new Location (200,500);
	

	
	private String winOrLose = "";
	
			
	
	
	private int points;
	private Text scoreMessage,endMessage;
	private DrawingCanvas canvas;
	
	public ScoreKeeper(DrawingCanvas canvas){
		points = 0;
		endMessage = new Text( winOrLose  , txtLocation,canvas);
		scoreMessage = new Text("Score " + points , 200 + winOrLose.length(),497,canvas);
		
	}
	
	
	/*
	 * method to add to the score when a mushroom is shot
	 */
	public void hitShroom(){
		points = points++;
		scoreMessage = new Text("Score "+ points, txtLocation, canvas);
		
		
	}
	
	
	/*
	 * method to add to the score when a segment is shot
	 */
	public void hitSegment(){
		points = points + 5;
		scoreMessage.setText("Score " + points);
		
	}
	
	/*
	 * method to change endMessage when segments are all gone
	 */
	public void playerWon(){
		winOrLose =("You Win");
		endMessage.setText(winOrLose);
	}
	
	/*
	 * method to change endMessage when zapper dies
	 */
	public void playerLost(){
		winOrLose = ("You LOSE");
		endMessage.setText(winOrLose);
	}
}

