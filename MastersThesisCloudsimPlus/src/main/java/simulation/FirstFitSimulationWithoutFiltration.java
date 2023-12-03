package simulation;

import org.cloudsimplus.allocationpolicies.VmAllocationPolicyFirstFit;
import org.cloudsimplus.brokers.DatacenterBroker;
import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.Pe;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.schedulers.cloudlet.CloudletSchedulerSpaceShared;
import org.cloudsimplus.schedulers.vm.VmSchedulerTimeShared;
import org.cloudsimplus.utilizationmodels.UtilizationModelDynamic;
import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;
import data.models.HostModel;
import data.models.VmModel;
import excel.reader.excelReaderHostVM;
import output.TableBuilderEnergy;
import output.TxtOutput;
import topsis.SortByScore;
import topsis.TopsisHelper;
import metrics.CalculateEnergyMakespanCost;
import hardware.createDataCenter;
import hardware.createVM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A minimal but organized, structured and re-usable CloudSim Plus FCFS 
 * simulation of i Cloudlets on j VMs which are booted on k Hosts using
 * VmAllocationPolicyFirstFit. The number of cloudlets, VMs and Hosts can 
 * be varied to simulate a real-world data center scenario.
 *
 * @author Sri Vibhu Paruchuri
 * 
 */
public class FirstFitSimulationWithoutFiltration {
    //private static final long VM_MIPS = HOST_TOTAL_MIPS_CAPACITY/NUMBER_OF_VMS;
    //private static final long VM_MIPS = 1000;
    private final CloudSimPlus simulation;
    //private static final int NUMBER_OF_CLOUDLETS = 15;
	private static final long CLOUDLET_LENGTH = 1000;
    private List<Cloudlet> cloudletList;
    private List<Vm> vmList;
    private ArrayList<Double> vmTdp;
    private int numberOfCreatedCloudlets = 0;
    private int numberOfCreatedVms = 0;
    private int numberOfCreatedHosts = 0;
    
    excelReaderHostVM readData = new excelReaderHostVM();
    
    private ArrayList<Integer> tdp = new ArrayList<>();

    /**
     * constructor where the simulation is built.
     */
    public FirstFitSimulationWithoutFiltration(Integer experimentNumber,Integer numberOfCloudlets) {
        System.out.println("Starting " + getClass().getSimpleName());
        simulation = new CloudSimPlus();

        this.vmList = new ArrayList<>();
        this.vmTdp = new ArrayList<>();
        this.cloudletList = new ArrayList<>();

        final var datacenter0 = createDatacenter();

        /*Creates a Broker accountable for submission of VMs and Cloudlets
        on behalf of a given cloud user (customer).*/
        final var broker0 = new DatacenterBrokerSimple(simulation);

        createAndSubmitVmsAndCloudlets(broker0,numberOfCloudlets);

        /*Starts the simulation and waits all cloudlets to be executed*/
        simulation.start();
        System.out.println("Simulation completed");

        /*Prints results when the simulation is over
        (you can use your own code here to print what you want from this cloudlet list)*/
        final var cloudletFinishedList = broker0.getCloudletFinishedList();
        new TableBuilderEnergy(cloudletFinishedList,tdp).build();
        System.out.println(getClass().getSimpleName() + " finished!");
        
        // print the Total Energy consumed by the data center to execute all the cloudlets
        ArrayList<Double> Energy_Cost = CalculateEnergyMakespanCost.calculateTotalEnergyCost(tdp,cloudletFinishedList);
        System.out.printf("Total Energy : %.2f kW \n",Energy_Cost.get(0));
        
        // print the Makespan
        double makespan = CalculateEnergyMakespanCost.getMakeSpan(cloudletFinishedList);
        System.out.printf("Makespan : %.5f s \n",makespan);
        
        //print the total energy cost of the datacenter to execute all the cloudlets
        System.out.printf("Total cost: %.5f ¢ \n",Energy_Cost.get(1));
        
        TxtOutput.appendToTextFile("src/main/output/DataVisualization/Experiment" + experimentNumber.toString() + "WithoutF.txt", numberOfCloudlets,Energy_Cost.get(0), makespan,Energy_Cost.get(1));
        
    }
    
    
    // creates VMs and Cloudlets and allocates Cloudlets to VMs and submits the appropriate pairing to the broker
    private void createAndSubmitVmsAndCloudlets(final DatacenterBroker broker0, Integer numberOfCloudlets) {
    	
    	//create VMs
    	createVM.createVmHelper(readData,vmList,numberOfCreatedVms,vmTdp,4);
    	int vmIndex = 0;

        //Allocate cloudlets to the filtered VMs using FCFS algorithm
        for(int i = 0; i < numberOfCloudlets; i++){
            final var cloudlet = createCloudlet(broker0);
            while (vmList.get(vmIndex).getPesNumber() < cloudlet.getPesNumber()) {
            	vmIndex = ++vmIndex % vmList.size();
            	
            }
            cloudlet.setVm(vmList.get(vmIndex));
            vmIndex = ++vmIndex % vmList.size();
            this.cloudletList.add(cloudlet);
        }
        
        // submit the VMs and Cloudlets to the broker
        broker0.submitVmList(vmList);
        broker0.submitCloudletList(cloudletList);
        
    }
    
    private Cloudlet createCloudlet(DatacenterBroker broker) {
        final long fileSize = 300; //Size (in bytes) before execution
        final long outputSize = 300; //Size (in bytes) after execution
        //final long  numberOfCpuCores = vm.getPesNumber(); //cloudlet will use all the VM's CPU cores
        final long  numberOfCpuCores = 4;

        return new CloudletSimple(numberOfCreatedCloudlets++, CLOUDLET_LENGTH, numberOfCpuCores)
            .setFileSize(fileSize)
            .setOutputSize(outputSize)
            .setUtilizationModelCpu(new UtilizationModelDynamic(1.0));
            //.setUtilizationModelRam(new UtilizationModelDynamic(0.5))
            //.setUtilizationModelBw(new UtilizationModelDynamic(0.5));
    }
    
    // allocate the VMs to Host using First Fit Policy
    private DatacenterSimple createDatacenter() {
        final var hostList = new ArrayList<Host>();   
        createDataCenter.createDatacenterHelper(readData,tdp,hostList,4 );
        return new DatacenterSimple(simulation, hostList, new VmAllocationPolicyFirstFit());
    }
    
}

