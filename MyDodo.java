import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 29-07-2017
 */
public class MyDodo extends Dodo
{

    public MyDodo() {
        super( EAST );
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
        }
    }

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();
        
        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }

    public void practiceWithListsOfSurpriseEggs( ){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }
    /**
     * Makes a list of 10 surprise eggs by calling the
     * generateListOfSurpriseEggs method in SurpriseEgg.
     */
    public List<SurpriseEgg> makeListOfSurpriseEggs() {
        return SurpriseEgg.generateListOfSurpriseEggs( 10, getWorld() );
    }
    /**
     * Prints the coordinates and the value of a given egg.
     */
    public void printCoordinatesOfEgg( Egg egg ) {
        System.out.println( "Egg at (" + egg.getX() + ", " + egg.getY()
            + ") with value " + egg.getValue() );
    }
    /**
     * Makes a list of 10 surprise eggs and prints the coordinates of each egg.
     */
    public void makeListOfSurpriseEggsAndPrintCoordinates() {
        List<SurpriseEgg> listOfSurpriseEggs = makeListOfSurpriseEggs();
        for ( SurpriseEgg egg : listOfSurpriseEggs ) {
            printCoordinatesOfEgg( egg );
        }
    }
    /**
     * Determines which egg in the list is the most valuable.
     */
    public SurpriseEgg findMostValuableEgg( List<SurpriseEgg> listOfEggs ) {
        if ( listOfEggs.isEmpty() ) {
            return null;
        }
        SurpriseEgg mostValuable = listOfEggs.get( 0 );
        for ( SurpriseEgg egg : listOfEggs ) {
            if ( egg.getValue() > mostValuable.getValue() ) {
                mostValuable = egg;
            }
        }
        return mostValuable;
    }
    /**
     * Fills the world with 10 surprise eggs, prints the coordinates and value
     * of every egg, and then determines and prints the most valuable egg.
     */
    public void findAndPrintMostValuableEgg() {
        List<SurpriseEgg> listOfSurpriseEggs = makeListOfSurpriseEggs();
        // print coordinates and value of all eggs to check the result
        for ( SurpriseEgg egg : listOfSurpriseEggs ) {
            printCoordinatesOfEgg( egg );
        }
        SurpriseEgg mostValuable = findMostValuableEgg( listOfSurpriseEggs );
        if ( mostValuable != null ) {
            System.out.println( "Most valuable egg:" );
            printCoordinatesOfEgg( mostValuable );
        }
    }
    /**
     * Calculates the average value of all eggs in the given list using a foreach.
     */
    public double calculateAverageValue( List<SurpriseEgg> listOfEggs ) {
        if ( listOfEggs.isEmpty() ) {
            return 0.0;
        }
        int totaal = 0;
        for ( SurpriseEgg egg : listOfEggs ) {
            totaal = totaal + egg.getValue();
        }
        return (double) totaal / listOfEggs.size();
    }
    /**
     * Fills the world with 10 surprise eggs and prints the average egg value.
     */
    public void makeListOfSurpriseEggsAndPrintAverageValue() {
        List<SurpriseEgg> listOfSurpriseEggs = makeListOfSurpriseEggs();
        double average = calculateAverageValue( listOfSurpriseEggs );
        System.out.println( "Average value: " + average );
    }
    /**
     * Moves the dodo in random directions for at most MAXSTEPS steps.
     * Each step a random direction is chosen, the dodo only steps when it can
     * move, so it never walks into the border of the world or into a fence.
     */
    public void moveRandomly() {
        int myNrOfStepsTaken = 0;
        // show the full number of moves left at the start
        getScore( Mauritius.MAXSTEPS - myNrOfStepsTaken, 0 );
        while ( myNrOfStepsTaken < Mauritius.MAXSTEPS ) {
            setDirection( randomDirection() );
            if ( canMove() ) {
                step();
            }
            myNrOfStepsTaken++;
            // a value has changed, so update the scoreboard right away
            getScore( Mauritius.MAXSTEPS - myNrOfStepsTaken, 0 );
        }
    }
    /**
     * Adjusts the scoreboard with the two given values.
     */
    public void getScore( int score1, int score2 ) {
        ( (Mauritius) getWorld() ).updateScore( score1, score2 );
    }
    /**
     * Walks Mimi to the nearest egg in the world and picks it up.
     */
    public void pickUpNearestEggInList() {
        // get a list of eggs
        List<Egg> listOfEggs = getListOfEggsInWorld();
        if ( listOfEggs.isEmpty() ) {
            return;
        }
        // determine destination of nearest egg
        Egg nearestEgg = findNearestEgg( listOfEggs );
        // walk to destination
        walkTo( nearestEgg.getX(), nearestEgg.getY() );
        // pick up egg
        pickUpEgg();
    }
    /**
     * Determines which egg in the list is closest to the dodo,
     * using the Manhattan distance (number of steps needed).
     */
    public Egg findNearestEgg( List<Egg> listOfEggs ) {
        if ( listOfEggs.isEmpty() ) {
            return null;
        }
        Egg nearest = listOfEggs.get( 0 );
        for ( Egg egg : listOfEggs ) {
            if ( distanceTo( egg ) < distanceTo( nearest ) ) {
                nearest = egg;
            }
        }
        return nearest;
    }
    /**
     * Calculates the Manhattan distance between the dodo and a given egg.
     */
    public int distanceTo( Egg egg ) {
        int dx = Math.abs( egg.getX() - getX() );
        int dy = Math.abs( egg.getY() - getY() );
        return dx + dy;
    }
    /**
     * Walks the dodo to the given coordinates, first horizontally and
     * then vertically.
     */
    public void walkTo( int targetX, int targetY ) {
        // walk horizontally
        while ( getX() != targetX ) {
            if ( getX() < targetX ) {
                setDirection( EAST );
            } else {
                setDirection( WEST );
            }
            step();
        }
        // walk vertically
        while ( getY() != targetY ) {
            if ( getY() < targetY ) {
                setDirection( SOUTH );
            } else {
                setDirection( NORTH );
            }
            step();
        }
    }
}
