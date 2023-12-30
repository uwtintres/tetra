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
		
		for (int i = 100; i <= 1000; i += 100 )
		{
			// Experiment 1 - base case - FCFS
			FirstFitSimulationFiltrationRandom exp = new FirstFitSimulationFiltrationRandom(1,i,4,0.4);
			
			// Experiment 1 - base case 2 - Best Fit
			BestWorstFitSimulationFiltration exp2 = new BestWorstFitSimulationFiltration(1,i,4,0.4,"best_fit");
			
			// Experiment 1 - base case 3 - Worst Fit
			BestWorstFitSimulationFiltration exp3 = new BestWorstFitSimulationFiltration(1,i,4,0.4,"worst_fit");
			
			// Experiment 1 - optimized scheduler using TOPSIS
			FirstFitSimulation_TopsisVmFiltration exp4 = new FirstFitSimulation_TopsisVmFiltration(1,i,4,0.4);
			
			// Experiment 2 - base case - FCFS
			FirstFitSimulationFiltrationRandom exp5 = new FirstFitSimulationFiltrationRandom(2,i,2,0.4);
			
			// Experiment 2 - base case 2 - Best Fit
			BestWorstFitSimulationFiltration exp6 = new BestWorstFitSimulationFiltration(2,i,2,0.4,"best_fit");
			
			// Experiment 2 - base case 3 - Worst Fit
			BestWorstFitSimulationFiltration exp7 = new BestWorstFitSimulationFiltration(2,i,2,0.4,"worst_fit");
			
			// Experiment 2 - optimized scheduler using TOPSIS
			FirstFitSimulation_TopsisVmFiltration exp8 = new FirstFitSimulation_TopsisVmFiltration(2,i,2,0.4);
			
			// Experiment 3 - base case - FCFS
			FirstFitSimulationFiltrationRandom exp9 = new FirstFitSimulationFiltrationRandom(3,i,1,0.4);
			
			// Experiment 3 - base case 2 - Best Fit
			BestWorstFitSimulationFiltration exp10 = new BestWorstFitSimulationFiltration(3,i,1,0.4,"best_fit");
			
			// Experiment 3 - base case 3 - Worst Fit
			BestWorstFitSimulationFiltration exp11 = new BestWorstFitSimulationFiltration(3,i,1,0.4,"worst_fit");
			
			// Experiment 3 - optimized scheduler using TOPSIS
			FirstFitSimulation_TopsisVmFiltration exp12 = new FirstFitSimulation_TopsisVmFiltration(3,i,1,0.4);
		}			
	}
}
  