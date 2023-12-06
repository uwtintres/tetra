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
		
		for (int i = 10; i <= 10; i += 10 )
		{
			// base case - FCFS
			FirstFitSimulationFiltrationRandom exp = new FirstFitSimulationFiltrationRandom(3,i,1,0.4);
			
			// base case 2 - Best Fit
			BestWorstFitSimulationFiltration exp2 = new BestWorstFitSimulationFiltration(3,i,1,0.4,"best_fit");
			
			// base case 3 - Worst Fit
			BestWorstFitSimulationFiltration exp3 = new BestWorstFitSimulationFiltration(3,i,1,0.4,"worst_fit");
			
			
			// optimized scheduler using TOPSIS
			FirstFitSimulation_TopsisVmFiltration exp4 = new FirstFitSimulation_TopsisVmFiltration(3,i,1,0.4);
			
		}			
	}
}
  