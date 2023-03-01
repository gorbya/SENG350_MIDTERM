package main;
import java.util.Scanner;

class Main
{
    public static void main(String args[])
    {
    	Scanner in = new Scanner(System.in);
        // create objects for testing
        AverageScoreDisplay averageScoreDisplay =
                          new AverageScoreDisplay();
        CurrentScoreDisplay currentScoreDisplay =
                          new CurrentScoreDisplay();
        System.out.println("Welcome to the Basketball Score Software!");
        System.out.println("Who will be playing today?");
        String teamA = in.nextLine();
        System.out.println("Wonderful! Who will they be up against?");
        String teamB = in.nextLine();
        System.out.println("Alright! Today's match will be " + teamA + " vs " + teamB + "!");
        // pass the displays to Cricket data
        BasketballData basketballData = new BasketballData(teamA,teamB);
  
        // register display elements
        basketballData.registerObserver(averageScoreDisplay);
        basketballData.registerObserver(currentScoreDisplay);
  
        // in real app you would have some logic to
        // call this function when data changes
        basketballData.dataChanged();
  
        //remove an observer
        basketballData.unregisterObserver(averageScoreDisplay);
  
        // now only currentScoreDisplay gets the
        // notification
        //basketballData.dataChanged();
    }
}