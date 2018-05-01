import java.awt.*;

import objectdraw.*;

public class Field {
    
    // dimensions of the canvas
    static private final int CANVAS_WIDTH = 400;
    static private final int CANVAS_HEIGHT = 512;
    
    // size of mushroom pictures
    static private final int SHROOM_SIZE = 16;  
    
    // dimensions of the mushroom array
    static private final int NUM_ROWS = CANVAS_HEIGHT / SHROOM_SIZE;
    static private final int NUM_COLS = CANVAS_WIDTH / SHROOM_SIZE;
    
    //2D array of visible images
    private VisibleImage [][] theField;
   
    //boolean for whether or not a shroom is visible
    private static boolean isVisible;
  
    private RandomIntGenerator rowGen;
	//random generators for random mushroom locations to be chosen
    private RandomIntGenerator colGen;
   
    //variable to refer to the segment
    private Segment aSegment;
    //variable to refer to the Scorekeeper
    private ScoreKeeper keeper;
    
    /*
     *field of mushrooms, comprised of a 2D array of visible images
     */
    public Field(ScoreKeeper keeper, Image shroomPic, DrawingCanvas canvas){
    	this.keeper = keeper;
    	//create the array of fields
    	theField = new VisibleImage[NUM_ROWS][NUM_COLS];
    	//create random number generators used to choose which mushrooms are initially shown
    	rowGen = new RandomIntGenerator(3,25);
    	colGen = new RandomIntGenerator(0,24);
    	//fill all entries in each row with visible image of mushroom
    	for(int row = 0; row<NUM_ROWS; row++){
    		//fill all entries in each column with visible image of mushroom
    		for(int col=0; col<NUM_COLS; col++){
    			//initialize each instance of array with shroomImage
    			theField[row][col] = new VisibleImage(shroomPic,col*SHROOM_SIZE,row*SHROOM_SIZE,canvas);;
    		}
    	}
    	this.hideAll();
    	
    	//create while loop to show randoms
    	int shroomCount = 65;
    	while(shroomCount>0){
    		theField[rowGen.nextValue()][colGen.nextValue()].show();
    		shroomCount--;
    	}
    	
    	
    	
    }
    //method to hide all mushrooms
    private void hideAll() {
    	//traverse all rows and columns with nested for loop
    	for(int row = 0; row<NUM_ROWS; row++){
    		for(int col=0; col<NUM_COLS; col++){
		theField[row][col].hide();
    		}
    	}
	}
    
    /*method to hide particular mushroom
     * @param x - the x coordinate of mushroom
     * @param y - the y coordinate of mushroom
     */
    
    public void hideShroom(double x, double y){
    	theField[getRow(y)][getColumn(x)].hide();
    	
    	
    	
    	
    	
    }
    /*
     * method to show mushroom
     * @param x - xCoord of shroom
     * @param y - yCoord of shroom
     */
    public void showShroom(double x, double y){
    		theField[getRow(y)][getColumn(x)].show();
    		keeper.hitShroom();
    		
    	}
    /*
     * method for segments to ask if a shroom is visible
     * @param x - the x coordinate of the shroom
     * @param y - the y coordinate of the shroom
     */
   public boolean shroomIsVisible(double x, double y){
	   if(y<0 || y> CANVAS_HEIGHT || x<0 || x>CANVAS_WIDTH){
		   return false;
	   }
		return !theField[getRow(y)][getColumn(x)].isHidden();
	   
   }
 
    ////// Helper methods to convert between (x,y) coordinates 
    ////// on the canvas and row and columns in the mushroom 
    ////// 2D array.
	// Convert a y coordinate in pixels to the corresponding
    //   row in the mushroom array
    public int getRow(double y) {
       
    	return (int)(y / SHROOM_SIZE);
    }
    
    // Convert a x coordinate in pixels to the corresponding
    //   column in the mushroom array
    public int getColumn(double x) {
        return (int)(x / SHROOM_SIZE);
    }
    
    /*
     * method to see if mushroom in field was hit
     */
   public boolean isHit(double x, double y){
	   return theField[getRow(y)][getColumn(x)].isHidden();
   }
    	
    	
    
    	
}
