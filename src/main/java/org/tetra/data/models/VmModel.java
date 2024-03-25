package org.tetra.data.models;

import java.util.ArrayList;

/**
 * Data Model for VM Specifications
 *
 * @author Sri Vibhu Paruchuri
 */
public class VmModel {
	
	private  ArrayList<Integer> mips = new ArrayList<Integer> ();
    private  ArrayList<Integer> pes = new ArrayList<Integer> ();
    private ArrayList<Integer> ram = new ArrayList<Integer> ();
    private ArrayList<Integer> bw = new ArrayList<Integer> ();
    private ArrayList<Integer> imagesize = new ArrayList<Integer> ();
    private ArrayList<Integer> tdp = new ArrayList<Integer> ();
    private ArrayList<Integer> hostPes = new ArrayList<Integer> ();
    
    
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
    
    public ArrayList<Integer> getImageSize()
    {
    	return this.imagesize;
    }
    
    public ArrayList<Integer> getTdp()
    {
    	return this.tdp;
    }
    
    public ArrayList<Integer> getHostPes()
    {
    	return this.hostPes;
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
    
    
    public void addImageSize(int imagesize)
    {
    	this.imagesize.add(imagesize);
    }
    
    public void addTdp(int tdp)
    {
    	this.tdp.add(tdp);
    }
    
    public void addHostPes(int hostPes)
    {
    	this.hostPes.add(hostPes);
    }
      
}
    

