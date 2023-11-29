package hardware;

import java.util.ArrayList;
import java.util.List;

import org.cloudsimplus.schedulers.cloudlet.CloudletSchedulerSpaceShared;
import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;

import data.models.VmModel;
import excel.reader.excelReaderHostVM;

public class createVM {
	
	/**
	 * This function processes the VM data returned by `excelReaderHostVM.readVmData()` to create a variable number of VMs for allocation to the Host machines in the data center.
	 * 
	 * @param readData An instance of the reader class used to extract Host information from a .csv file.
	 * @param vmList An ArrayList of all the VMs in the data center.
	 * @param numberOfCreatedVms A counter that keeps track of the number of VMs created.
	 * @param vmTdp An ArrayList of TDP values representing the thermal power limits of VMs.
	 * @param factor This parameter determines how many VMs are included in the data center. The total count will be 153 * factor.
	 */
	public static void createVmHelper(excelReaderHostVM readData, List<Vm> vmList, int numberOfCreatedVms, ArrayList<Double> vmTdp,int factor)
    {
    	
		final int VM_SIZE = 153;
		
    	// excel sheet that contains the details of VMs
        String filePath = "src/main/resources/VM.xlsx";

        int desiredSheet = 4; // Index of the desired cell value (starting from 0)
        
        VmModel vmData = readData.readVmData(filePath,desiredSheet,3,4,5,6,7,10,11);
        
    	ArrayList<Integer> mips = new ArrayList<>();
        ArrayList<Integer> pes = new ArrayList<>();
        ArrayList<Integer> ram = new ArrayList<>();
        ArrayList<Integer> bw = new ArrayList<>();
        ArrayList<Integer> imagesize = new ArrayList<>();
        ArrayList<Integer> tdp = new ArrayList<>();
        ArrayList<Integer> hostPes = new ArrayList<>();
        
        pes.addAll(vmData.getPes());
        ram.addAll(vmData.getRam());
        mips.addAll(vmData.getMips());
        imagesize.addAll(vmData.getImageSize());
        bw.addAll(vmData.getBw());
        tdp.addAll(vmData.getTdp());
        hostPes.addAll(vmData.getHostPes());
        
        for (int i = 0; i < VM_SIZE*factor; i ++)
        {
        	
        	vmList.add(new VmSimple(numberOfCreatedVms++,mips.get(i),pes.get(i))
        			.setRam(ram.get(i)).setBw(bw.get(i)).setSize(imagesize.get(i))
        			.setCloudletScheduler(new CloudletSchedulerSpaceShared()));
        	
        	vmTdp.add(((double)tdp.get(i)/hostPes.get(i))*pes.get(i));
        }

    }
}
