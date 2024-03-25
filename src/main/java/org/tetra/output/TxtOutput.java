package org.tetra.output;
import java.io.*;
import java.nio.file.*;
/**
 * Writes output data to a .txt file. 
 *
 * @author Sri Vibhu Paruchuri
 * 
 */
public class TxtOutput {

	/**
	 * Writes the output parameters to a .txt file.
	 * 
	 * @param textFilePath The name of the .txt file
	 * @param numberOfCloudlets This signifies the number of cloudlets/tasks for 
	 * the current simulation
	 * @param totalEnergy This signifies the total energy consumed by the data center 
	 * to execute all the cloudlets.
	 * @param makespan This signifies the makespan of the current simulation to 
	 * execute all the cloudlets.
	 * @param totalCost This signifies the total cost to execute all the cloudlets.
	 */
    public static void appendToTextFile(String textFilePath, Integer numberOfCloudlets, Double totalEnergy, Double makespan, Double totalCost) {
        try {
        	
            boolean isHeaderRequired = !new File(textFilePath).exists();

            FileWriter fileWriter = new FileWriter(textFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            if (isHeaderRequired) {
                // Add column names with right alignment
                String header = String.format("%-19s %-19s %-19s %-19s", "Number of Cloudlets", "Total Energy", "Makespan", "Total Cost");
                bufferedWriter.write(header);
                bufferedWriter.newLine();
            }

            // Format and append the data with right alignment
            String data = String.format("%-19s %-19s %-19s %-19s", numberOfCloudlets, totalEnergy, makespan, totalCost);
            bufferedWriter.write(data);
            bufferedWriter.newLine();

            System.out.println("Data appended successfully to the text file.");

            // Close the writer
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
