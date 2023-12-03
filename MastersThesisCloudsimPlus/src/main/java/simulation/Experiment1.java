package simulation;

/**
 * Experiment 1: Compares TOPSIS-based scheduler (with filtration) 
 * to base case (no filtration).
 * 
 * @author Sri Vibhu Paruchuri
 * 
 */

public class Experiment1 {
	/**
	 * Run scheduling simulations for varying numbers
	 * of cloudlets (increments of 10 from 10 to 1000). 
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		
		for (int i = 10; i <= 1000; i += 10 )
		{
			// base case - FCFS
			FirstFitSimulationFiltrationRandom exp1 = new FirstFitSimulationFiltrationRandom(1,i,4,0.4);
			
			// optimized scheduler using TOPSIS
			FirstFitSimulation_TopsisVmFiltration exp = new FirstFitSimulation_TopsisVmFiltration(1,i,4,0.4);
			
		}			
	}
}
  