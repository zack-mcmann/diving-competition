//********************************************************************* 
//*
//*     CIS340               Spring 2022           Zack McMann 
//*
//*                     Program Assignment PA04
//*
//*                    Diving Competition Program 
//*
//*                    Date Created: 02.22.2022
//*                    Saved in:  ZackMcMannPA0401.java
//*
//********************************************************************* 

import javax.swing.*;
import java.io.*;

public class ZackMcMannPA0401 {

	public static DivingCompetitor[] sortCompetitorArray(DivingCompetitor[] arr) {
		int max = 0;
		DivingCompetitor temp;
		
		for (int i = 0; i < arr.length; i++) {
			max = i;
			
			// Find next max value
			for (int ii = i; ii < arr.length; ii++) {
				if (arr[ii].getFinalScore() > arr[max].getFinalScore()) max = ii;
			}
			
			// Swap
			temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
		}
		
		return arr;
	}
	
	public static void writeToFile(DivingCompetitor[] arr) throws FileNotFoundException {
		// Output to file
		File f = new File("Scores.txt");
					
		PrintWriter pw = new PrintWriter(f);
		
		for (DivingCompetitor d : arr) {	
			// Format -> Diver name: Final score
			pw.write(d.getFullName() + ": " + d.getFinalScore() + "\n");
		}
		
		pw.close();
	}
	
	public static void showError(Exception ex) {
		JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Add error handling
		DivingCompetitor[] competitorsArr = null;
		int numDivers = 0;
		int difficulty = 0;
		String firstName = "";
		String lastName = "";
		
		// Get number of divers, also instantiates the competitorsArr array given valid input
		while (true) {
			try {
				numDivers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of divers:"));
				if (numDivers < 1) throw new Exception("Number of divers must be at least 1.");
				
				competitorsArr = new DivingCompetitor[numDivers];
				
				break;
			} catch (Exception ex) {
				showError(ex);
				continue;
			}
		}
		
		// Get difficulty
		while (true) {
			try {
				difficulty = Integer.parseInt(JOptionPane.showInputDialog("What is the difficulty?"));
				if (difficulty < 1 || difficulty > 5) throw new Exception("Difficulty must be between 1 and 5.");
				
				break;
			} catch (Exception ex) {
				showError(ex);
				continue;
			}
		}
			
		
		// Get first and last name and create diver object
		for (int i = 0; i < numDivers; i++) {
			// TODO Add error checking
			while (true) {
				try {
					firstName = JOptionPane.showInputDialog("What is diver #" + (i + 1) + "'s first name?");
					lastName = JOptionPane.showInputDialog("What is diver #" + (i + 1) + "'s last name?");
					
					break;
				} catch (Exception ex) {
					showError(ex);
					continue;
				}
			}
			
			competitorsArr[i] = new DivingCompetitor(difficulty);
			competitorsArr[i].computeScores();
			competitorsArr[i].setFullName(firstName, lastName);
		}
		
		// Sort in descending order
		competitorsArr = sortCompetitorArray(competitorsArr);
		
		// Add diver info to output
		String output = "Diver name\t\tFinal Score\n";
		for (DivingCompetitor d : competitorsArr) {
			output += d.getFullName() + "\t\t" + d.getFinalScore() + "\n";
		}
		
		// Show output
		JOptionPane.showMessageDialog(null, new JTextArea(output));
		
		// Write to Scores.txt
		writeToFile(competitorsArr);
	}

}
