package main;
	// Java program to demonstrate working of
	// onserver pattern
	import java.util.ArrayList;
	import java.util.Iterator;
	  
	// Implemented by Cricket data to communicate
	// with observers
	interface Subject
	{
	    public void registerObserver(Observer o);
	    public void unregisterObserver(Observer o);
	    public void notifyObservers();
	}
	  
	class BasketballData implements Subject
	{
		String teamA;
		String teamB;
	    int pointsA;
	    int pointsB;
	    int fouls;
	    ArrayList<Observer> observerList;
	  
	    public BasketballData(String tA, String tB) {
	    	this.teamA = tA;
	    	this.teamB = tB;

	        observerList = new ArrayList<Observer>();
	    }
	  
	    @Override
	    public void registerObserver(Observer o) {
	        observerList.add(o);
	    }
	  
	    @Override
	    public void unregisterObserver(Observer o) {
	        observerList.remove(observerList.indexOf(o));
	    }
	  
	    @Override
	    public void notifyObservers()
	    {
	        for (Iterator<Observer> it =
	              observerList.iterator(); it.hasNext();)
	        {
	            Observer o = it.next();
	            o.update(pointsA,fouls);
	            //Add BPoints
	        }
	    }
	  
	    // get latest points from stadium
	    private int getLatestScore()
	    {
	        // return 90 for simplicity
	        return 18;
	    }
	  
	    // get latest fouls from stadium
	    private int getLatestFoulCount()
	    {
	        // return 2 for simplicity
	        return 2;
	    }
	  
	    // get latest overs from stadium

	    // This method is used update displays
	    // when data changes
	    public void dataChanged()
	    {
	        //get latest data
	        pointsA = getLatestScore();
	        fouls = getLatestFoulCount();
	  
	        notifyObservers();
	    }
	}
	  
	// This interface is implemented by all those
	// classes that are to be updated whenever there
	// is an update from CricketData
	interface Observer
	{
	    public void update(int points, int fouls);
	}
	  
	class AverageScoreDisplay implements Observer
	{
	    
	    private int predictedScore;
	  
	    public void update(int points, int fouls)
	    {
	        //the predicted score estimates 23 more points minus 2 per foul.
	    	
	    	int predictedMissedPoints = (23-2*fouls);
	    	
	    	if (predictedMissedPoints < 0){
	    		predictedMissedPoints = 0;
	    	}
	    	
	        this.predictedScore = (points + predictedMissedPoints);
	        display();
	    }
	  
	    public void display()
	    {
	        System.out.println("\nAverage Score Display: \n"
	                           + "Run Rate: " + "___" +
	                           "\nPredictedScore: " +
	                           predictedScore);
	    }
	}
	  
	class CurrentScoreDisplay implements Observer
	{
	    private int points, fouls;
	    
	  
	    public void update(int points, int fouls)
	    {
	        this.points = points;
	        this.fouls = fouls;
	        
	        display();
	    }
	  
	    public void display()
	    {
	        System.out.println("\nCurrent Score Display:\n"
	                           + "Points: " + points +
	                           "\nFouls: " + fouls +
	                           "\nOvers: " + "___" );
	    }
	}

