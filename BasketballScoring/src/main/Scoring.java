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
		public String teamA;
		public String teamB;
	    int pointsA;
	    int pointsB;
	    int foulsA;
	    int foulsB;
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
	            o.update(pointsA, pointsB, foulsA, foulsB);
	            //Add BPoints
	        }
	    }
	  
	    // get latest points from stadium
	    private int getLatestScore()
	    {
	        //TODO: Make this random?
	        return 18;
	    }
	  
	   		//TODO: Ditto
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
	        foulsA = getLatestFoulCount();
	        pointsB = getLatestScore();
	        foulsB = getLatestFoulCount();
	  
	        notifyObservers();
	    }
	}
	  
	// This interface is implemented by all those
	// classes that are to be updated whenever there
	// is an update from CricketData
	interface Observer
	{
	    public void update(int pointsA, int pointsB, int foulsA, int foulsB);
	}
	  
	class AverageScoreDisplay implements Observer
	{
	    
	    private int predictedScoreA;
	    private int predictedScoreB;
	    
	    public void update(int pointsA, int pointsB, int foulsA, int foulsB)
	    {
	        //the predicted score estimates 23 more points minus 2 per foul.
	    	
	    	int predictedMissedPointsA = (23-2*foulsA);
	    	int predictedMissedPointsB = (23-2*foulsB);
	    	
	    	if (predictedMissedPointsA < 0){
	    		predictedMissedPointsA = 0;
	    	}
	    	
	    	if (predictedMissedPointsB < 0){
	    		predictedMissedPointsB = 0;
	    	}
	    	
	        this.predictedScoreA = (pointsA + predictedMissedPointsA);
	        this.predictedScoreB = (pointsB + predictedMissedPointsB);
	        display();
	    }
	  
	    public void display()
	    {
	        System.out.println("\nPredictedFinalScore: " +
	                           predictedScoreA +" | "+predictedScoreB);
	    }
	}
	  
	class CurrentScoreDisplay implements Observer
	{
	    private int pointsA, pointsB, foulsA, foulsB;
	    
	  
	    public void update(int pointsA, int pointsB, int foulsA, int foulsB)
	    {
	        this.pointsA = pointsA;
	        this.foulsA = foulsA;
	        this.pointsB = pointsB;
	        this.foulsB = foulsB;
	        
	        display();
	    }
	  
	    public void display()
	    {
	        System.out.println("\nCurrent Score Display:\n"
	                           +"TeamA Points: " + pointsA +
	                           "\nTeamA Fouls: " + foulsA +
	                           "\nTeamB Points: " + pointsB +
	                           "\nTeamB Fouls: " + foulsB);
	    }
	}

