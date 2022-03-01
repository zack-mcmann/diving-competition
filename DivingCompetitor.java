
public class DivingCompetitor {
	private String firstName;
	private String lastName;
	private int[] points = new int[7];
	private static int difficultyLevel;
	private double avgPoints;
	private double finalScore;
	
	public DivingCompetitor() {
		
	}
	
	public DivingCompetitor(int difficultyLevel) {
		DivingCompetitor.difficultyLevel = difficultyLevel;
	}
	
	public void setDifficulty(int difficultyLevel) {
		DivingCompetitor.difficultyLevel = difficultyLevel;
	}
	
	public void setFullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public double computeScores() {
		// Used to mark max and min indexes
		int max = 0;
		int min = 0;
		
		// Get 7 random scores from judges
		for (int i = 0; i < 7; i++) {
			points[i] = (int)Math.ceil(Math.random() * 10);
			
			// Assign max and min indexes as necessary
			if (points[i] > points[max]) max = i;
			if (points[i] < points[min]) min = i;
			
			// Add all scores to finalScore
			finalScore += points[i];
		}
		
		// Take out max and min
		finalScore -= (points[max] + points[min]);
		
		// Compute average score
		avgPoints = finalScore / 5;
		
		finalScore = avgPoints * difficultyLevel;
		
		return finalScore;
	}
	
	public double getFinalScore() {
		return finalScore;
	}
	
	public double getAverage() {
		return avgPoints;
	}
}
