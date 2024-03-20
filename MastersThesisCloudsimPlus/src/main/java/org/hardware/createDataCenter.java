package org.hardware;

import java.util.ArrayList;

import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.Pe;
import org.cloudsimplus.resources.PeSimple;
import org.cloudsimplus.schedulers.vm.VmSchedulerTimeShared;
import org.tetra.data.models.HostModel;
import org.tetra.excel.reader.excelReaderHostVM;

public class createDataCenter {
	
	/**
	 *	This function processes the Host data returned by excelReaderHostVM.readHostData() to create a data center with a variable number of Host machines.
	 *	 
	 * @param readData An instance of the reader class used to extract Host information from a .csv file. 
	 * @param tdp An ArrayList of TDP values representing the thermal power limits of Hosts.
	 * @param hostList An ArrayList of all the hosts in the data center. 
	 * @param factor This parameter determines how many Hosts are included in the data center. The total count will be 73 * factor.
	 */
    public static void createDatacenterHelper(excelReaderHostVM readData,ArrayList<Integer> tdp,ArrayList<Host> hostList, int factor)
    {
    	
    	final int HOST_SIZE = 73;
    	
    	// excel sheet that contains the details of all the hosts in the data center
        String filePath = "src/main/resources/host.xlsx";

        int desiredSheet = 2; // Index of the desired cell value (starting from 0)
        
        
        HostModel hostData = readData.readHostData(filePath,desiredSheet,2,5,8,9,10,4);
        
    	ArrayList<Integer> pes = new ArrayList<>();
        ArrayList<Integer> ram = new ArrayList<>();
        ArrayList<Integer> mips = new ArrayList<>();
        ArrayList<Integer> storage = new ArrayList<>();
        ArrayList<Integer> bw = new ArrayList<>();
        
        pes.addAll(hostData.getPes());
        ram.addAll(hostData.getRam());
        mips.addAll(hostData.getMips());
        storage.addAll(hostData.getStorage());
        bw.addAll(hostData.getBw());
        tdp.addAll(hostData.getTdp());
        
        for(int i = 0; i < HOST_SIZE*factor; i ++)
        {
        	int currPe = pes.get(i);
        	int currRam = ram.get(i);
        	int currMips = mips.get(i);
        	int currStorage = storage.get(i);
        	int currBw = bw.get(i);
        	 	
        	ArrayList<Pe> peList = new ArrayList<Pe>();
        	
        	for (int j = 0 ; j < currPe; j ++)
        	{
        		peList.add(new PeSimple(currMips));
        	}
        	
        	hostList.add(new HostSimple(currRam,currBw,currStorage,peList).setVmScheduler(new VmSchedulerTimeShared()));
        	 	
        }
          
    }
}
