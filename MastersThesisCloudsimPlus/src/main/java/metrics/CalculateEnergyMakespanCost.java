package metrics;

import java.util.ArrayList;
import java.util.List;

import org.cloudsimplus.cloudlets.Cloudlet;

public class CalculateEnergyMakespanCost {
	
	/**
	 * Calculates the total energy consumption in Joules (J) and the total cost in cents
	 * for executing all the cloudlets in the simulation
	 * 
	 * @param tdp An ArrayList of TDP values representing the thermal power limits of VMs.
	 * @param cloudletFinishedList An ArrayList of all the cloudlets that have finished executing.
	 * 
	 * @return An array where index 0 contains the total energy in Joules and index 1 contains the total cost in cents.
	 */
    public static ArrayList<Double> calculateTotalEnergyCost(ArrayList<Integer> tdp,List<Cloudlet> cloudletFinishedList)
    {
    	double totalEnergyinJoules = 0.0;
    	double totalCost = 0.0;
    	
    	for (int i = 0; i < cloudletFinishedList.size();i++)
    	{
    		Cloudlet cloudlet = cloudletFinishedList.get(i);

    		// calculate energy to execute cloudlet in kWh
    		double currentEnergy = (((double) cloudlet.getPesNumber()  / cloudlet.getVm().getHost().getPesNumber()) * tdp.get((int)cloudlet.getVm().getHost().getId())) / 1000.0;
    		currentEnergy*= cloudlet.getTotalExecutionTime()/3600;
    		
    		// convert kWh to J
    		totalEnergyinJoules += currentEnergy*3600000;
    		
    		//calculate cost to execute cloudlet in cents
    		totalCost += currentEnergy*13.0;
    	}
    	
    	ArrayList result = new ArrayList<Double>();
    	result.add(totalEnergyinJoules);
    	result.add(totalCost);
    	
    	return result;
    	
    }
    
    /**
     * Calculates the makespan (the duration of time between the earliest start time of any task and the latest finish time of any task in the simulation).
     * 
     * @param cloudletFinishedList An ArrayList of all the cloudlets that have finished executing.
     * 
     * @return The makespan of the current simulation
     */
    public static Double getMakeSpan(List<Cloudlet> cloudletFinishedList)
    {
    	double minStartTime = Double.MAX_VALUE;
    	double maxFinishTime = Double.MIN_VALUE;
    	
    	for (int i = 0; i < cloudletFinishedList.size();i++)
    	{
    		double startTime = cloudletFinishedList.get(i).getStartTime();
    		double endTime = cloudletFinishedList.get(i).getFinishTime();
    		if (startTime < minStartTime)
    		{
    			minStartTime = startTime;
    		}
    		
    		if (endTime > maxFinishTime)
    		{
    			maxFinishTime = endTime;
    		}
    	}
    	
    	return maxFinishTime - minStartTime;
    }
    
}
