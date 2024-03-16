package sorting;
import java.util.Comparator;

import org.cloudsimplus.vms.Vm;

/**
 * Comparator class to sort Virtual Machines (VMs) based on their PEs (Processing Elements) number.
 * @author vibhu
 *
 */
public class SortByPes implements Comparator<Vm> {
    public int compare(Vm a, Vm b)
    {
        if (a.getPesNumber() < b.getPesNumber())
        {
            return -1;
        }
        else if (a.getPesNumber() > b.getPesNumber())
        {
            return 1;
        }
        else if (a.getPesNumber() == a.getPesNumber())
        {
            return 0;
        }
        else {
        	return 0;
        }
    }
}
