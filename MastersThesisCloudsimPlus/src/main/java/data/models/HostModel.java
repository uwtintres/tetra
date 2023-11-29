package data.models;

import java.util.ArrayList;

/**
 * Data Model for Host Specifications
 *
 * @author Sri Vibhu Paruchuri
 */

public class HostModel {
	
	private  ArrayList<Integer> mips = new ArrayList<Integer> ();
    private  ArrayList<Integer> pes = new ArrayList<Integer> ();
    private ArrayList<Integer> ram = new ArrayList<Integer> ();
    private ArrayList<Integer> bw = new ArrayList<Integer> ();
    private ArrayList<Integer> storage = new ArrayList<Integer> ();
    private ArrayList<Integer> tdp = new ArrayList<Integer> ();
    
    
    public ArrayList<Integer> getMips()
    {
    	return this.mips;
    }
    
    public ArrayList<Integer> getPes()
    {
    	return this.pes;
    }
    
    public ArrayList<Integer> getRam()
    {
    	return this.ram;
    }
    
    public ArrayList<Integer> getBw()
    {
    	return this.bw;
    }
    
    public ArrayList<Integer> getStorage()
    {
    	return this.storage;
    }
    
    public ArrayList<Integer> getTdp()
    {
    	return this.tdp;
    }
    
    public void addMips(int mips)
    {
    	this.mips.add(mips);
    }
    
    public void addPes(int pes)
    {
    	this.pes.add(pes);
    }
    
    public void addRam(int ram)
    {
    	this.ram.add(ram);
    }
    
    public void addBw(int bw)
    {
    	this.bw.add(bw);
    }
    
    
    public void addStorage(int storage)
    {
    	this.storage.add(storage);
    }
    
    public void addTdp(int tdp)
    {
    	this.tdp.add(tdp);
    }
    
    
    public void deleteCorruptedData() {
    	
        while (pes.get(pes.size()-1) == 0) {
            pes.remove(pes.size()-1);
        }
        
        while (mips.get(mips.size()-1) == 0) {
            mips.remove(mips.size()-1);
        }
        
        while (ram.get(ram.size()-1) == 0) {
            ram.remove(ram.size()-1);
        }
        
        while (bw.get(bw.size()-1) == 0) {
            bw.remove(bw.size()-1);
        }
        
        while (storage.get(storage.size()-1) == 0) {
            storage.remove(storage.size()-1);
        }
        
        while (tdp.get(tdp.size()-1) == 0) {
            tdp.remove(tdp.size()-1);
        }
    }
    
    
    
    
}
