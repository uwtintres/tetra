package topsis;

import java.util.ArrayList;
import java.util.Comparator;

public class SortByScore implements Comparator<ArrayList<Double>> {
    public int compare(ArrayList<Double> a, ArrayList<Double> b)
    {
        if (a.get(1) < b.get(1))
        {
            return -1;
        }
        else if (a.get(1) > b.get(1))
        {
            return 1;
        }
        else if (a.get(1) == a.get(1))
        {
            return 0;
        }
        else {
        	return 0;
        }
    }
}
