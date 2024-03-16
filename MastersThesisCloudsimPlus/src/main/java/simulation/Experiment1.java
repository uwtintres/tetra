package simulation;

/**
 * Experiment: Algorithms: FFA + BFA + GFA + FFTA
 * Cloudlets : (100,1000,100)
 * Competition: LRC + MRC + HRC
 * Post Fitration Percentage:60%
 * 
 * @author Sri Vibhu Paruchuri
 * 
 */

public class Experiment1 {
	/**
	 * Run scheduling simulations for varying numbers
	 * of cloudlets (increments of 100 from 100 to 1000). 
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		
		for (int i = 100; i <= 1000; i += 100 )
		{
			// Experiment 1 - LRC - FFA
			FirstFitSimulationFiltrationRandom exp = new FirstFitSimulationFiltrationRandom(1,i,4,0.6);
			
			// Experiment 1 - LRC - BFA
			BestWorstFitSimulationFiltration exp2 = new BestWorstFitSimulationFiltration(1,i,4,0.6,"best_fit");
			
			// Experiment 1 - LRC - GFA
			BestWorstFitSimulationFiltration exp3 = new BestWorstFitSimulationFiltration(1,i,4,0.6,"worst_fit");
			
			// Experiment 1 - LRC - FFTA
			FirstFitSimulation_TopsisVmFiltration exp4 = new FirstFitSimulation_TopsisVmFiltration(1,i,4,0.6);
			
			// Experiment 2 - MRC - FFA
			FirstFitSimulationFiltrationRandom exp5 = new FirstFitSimulationFiltrationRandom(2,i,2,0.6);
			
			// Experiment 2 - MRC - BFA
			BestWorstFitSimulationFiltration exp6 = new BestWorstFitSimulationFiltration(2,i,2,0.6,"best_fit");
			
			// Experiment 2 - MRC - GFA
			BestWorstFitSimulationFiltration exp7 = new BestWorstFitSimulationFiltration(2,i,2,0.6,"worst_fit");
			
			// Experiment 2 - MRC - FFTA
			FirstFitSimulation_TopsisVmFiltration exp8 = new FirstFitSimulation_TopsisVmFiltration(2,i,2,0.6);
			
			// Experiment 3 - HRC - FFA
			FirstFitSimulationFiltrationRandom exp9 = new FirstFitSimulationFiltrationRandom(3,i,1,0.6);
			
			// Experiment 3 - HRC - BFA
			BestWorstFitSimulationFiltration exp10 = new BestWorstFitSimulationFiltration(3,i,1,0.6,"best_fit");
			
			// Experiment 3 - HRC - GFA
			BestWorstFitSimulationFiltration exp11 = new BestWorstFitSimulationFiltration(3,i,1,0.6,"worst_fit");
			
			// Experiment 3 - HRC - FFTA
			FirstFitSimulation_TopsisVmFiltration exp12 = new FirstFitSimulation_TopsisVmFiltration(3,i,1,0.6);
		}			
	}
}
  