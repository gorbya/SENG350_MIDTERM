package main;
	// Java program to demonstrate working of
	// onserver pattern
	import java.util.ArrayList;
	import java.util.Iterator;
import java.util.Random;
	  
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
		Random r = new Random();
		public String teamA;
		public String teamB;
	    int pointsA = 0;
	    int pointsB = 0;
	    int foulsA;
	    int foulsB;
	    int FinalA;
	    int FinalB;
	    
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
	            o.update(pointsA, pointsB, foulsA, foulsB, teamA, teamB);
	            //Add BPoints
	        }
	    }
	  
	    
	    // get latest points from stadium
	    private void getLatestScore()
	    {
	        int score = r.nextInt(2, 21);
	        this.pointsA = this.pointsA + score;
	        
	        score = r.nextInt(2, 21);
	        this.pointsB = this.pointsB + score;
	        
	        return;
	    }
	    
	  
	   		//TODO: Ditto
	    private void getLatestFoulCount()
	    {
	        int fouls = r.nextInt(2, 5);
	        this.foulsA = this.foulsA + fouls;
	        
	        fouls = r.nextInt(2, 5);
	        this.foulsB = this.foulsB + fouls;
	        
	        return;
	    }
	    
	    private int getFinalA() {
	    	return this.pointsA;
	    }
	    
	    private int getFinalB() {
	    	return this.pointsB;
	    }
	  
	    // This method is used update displays
	    // when data changes
	    public void dataChanged()
	    {
	        //get latest data
	        getLatestScore();
	        getLatestFoulCount();
	        notifyObservers();
	        FinalA = getFinalA();
	        FinalB = getFinalB();
	    }
	}
	  
	// This interface is implemented by all those
	// classes that are to be updated whenever there
	// is an update from CricketData
	interface Observer
	{
	    public void update(int pointsA, int pointsB, int foulsA, int foulsB, String teamA, String teamB);
	}
	  
	class PredictedScoreDisplay implements Observer
	{
	    
	    private int predictedScoreA;
	    private int predictedScoreB;
	    
	    public void update(int pointsA, int pointsB, int foulsA, int foulsB, String teamA, String teamB)
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
	    private String teamA;
	    private String teamB;
	  
	    public void update(int pointsA, int pointsB, int foulsA, int foulsB, String teamA, String teamB)
	    {
	        this.pointsA = pointsA;
	        this.foulsA = foulsA;
	        this.pointsB = pointsB;
	        this.foulsB = foulsB;
	        this.teamA = teamA;
	        this.teamB = teamB;
	        
	        display();
	    }
	  
	    public void display()
	    {
	        System.out.println("\nCurrent Score Display:\n"
	                           +teamA+"'s Points: " + pointsA + ",  Fouls: " + foulsA + "\n"+
	                           teamB+"'s Points: " + pointsB +",  Fouls: " + foulsB);
	    }
	}
	
	class FinalScoreDisplay implements Observer
	{
	    private int FinalA, FinalB;
	    private String teamA, teamB;
	  
	    public void update(int pointsA, int pointsB, int foulsA, int foulsB, String teamA, String teamB)
	    {
	    	FinalA = pointsA;
	    	FinalB = pointsB;
	        display();
	    }
	  
	    public void display()
	    {
	        System.out.println("\nFinal Score:\n" + 
	        		FinalA +" - "+ FinalB );
	    }

	}
	
	class SelectTeams
	{
		String[] teams = {
				"Net Rippers",
				"Basket Hounds",
				"D-Fence",
				"The Ball Boyz",
				"Travelers",
				"Jazz Me Up",
				"Droolers and Dribblers",
				"Shattered Backboards",
				"Dunkin’ Dads",
				"Lay Up Lay Down",
				"Tipped Off",
				"Slammed Dunk",
				"Ball Hogz"};
		
		public String[] SelectTeams() {
			String a = "",b = "";
			
			Random r = new Random();
			
			while (a == b) {
				a = teams[r.nextInt(12)];
				b = teams[r.nextInt(12)];
			}
			String[] rTeams = {a,b};
			
			return rTeams;
			
		}
	}

