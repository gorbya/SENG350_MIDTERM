package main;
import java.util.Scanner;

class Main
{
    public static void main(String args[])
    {
    	Scanner in = new Scanner(System.in);
        // create objects for testing
        PredictedScoreDisplay predictedScoreDisplay =
                          new PredictedScoreDisplay();
        CurrentScoreDisplay currentScoreDisplay =
                          new CurrentScoreDisplay();
        
        FinalScoreDisplay finalScoreDisplay =
        				  new FinalScoreDisplay();
        
        SelectTeams st = new SelectTeams();
        
        String[] teams = st.SelectTeams();
        
        System.out.println("Welcome to the Basketball Score Software!");

        System.out.println("\nToday's match will be " + teams[0] + " vs " + teams[1] + "!");
        // pass the displays to Cricket data
        BasketballData basketballData = new BasketballData(teams[0],teams[1]);
  
        // register display elements
        
        basketballData.registerObserver(currentScoreDisplay);
  
        // in real app you would have some logic to
        // call this function when data changes
        basketballData.dataChanged();
        
        System.out.println("\nEnd of the First Quarter, press return to continue.");
        in.nextLine();
        basketballData.registerObserver(predictedScoreDisplay);
        basketballData.dataChanged();
        
        basketballData.unregisterObserver(predictedScoreDisplay);
        
        System.out.println("\nEnd of the Second Quarter, press return to continue.");
        in.nextLine();
        
        basketballData.dataChanged();
        System.out.println("\nEnd of the Third Quarter, press return to continue.");
        in.nextLine();
        basketballData.registerObserver(predictedScoreDisplay);
        basketballData.registerObserver(finalScoreDisplay);
        
        basketballData.dataChanged();
        
        System.out.println("\nEnd of the Fourth Quarter");
        
        //remove an observer
        //basketballData.unregisterObserver(averageScoreDisplay);
        
        // now only currentScoreDisplay gets the
        // notification
        //basketballData.dataChanged();
    }
}