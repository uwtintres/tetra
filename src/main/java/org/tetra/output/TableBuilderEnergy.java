/*
 * CloudSim Plus: A modern, highly-extensible and easier-to-use Framework for
 * Modeling and Simulation of Cloud Computing Infrastructures and Services.
 * http://cloudsimplus.org
 *
 *     Copyright (C) 2015-2021 Universidade da Beira Interior (UBI, Portugal) and
 *     the Instituto Federal de Educação Ciência e Tecnologia do Tocantins (IFTO, Brazil).
 *
 *     This file is part of CloudSim Plus.
 *
 *     CloudSim Plus is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     CloudSim Plus is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with CloudSim Plus. If not, see <http://www.gnu.org/licenses/>.
 */
package org.tetra.output;

import lombok.Getter;

import org.cloudsimplus.builders.tables.CloudletsTableBuilder;
import org.cloudsimplus.builders.tables.Table;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.core.Identifiable;

import java.util.List;

import java.util.ArrayList;

/**
 * Builds a table for printing simulation results from a list of Cloudlets. The Simulation results include estimated
 * energy consumed by each cloudlet.
 * <p>The basic usage of the class is by calling its constructor,
 * giving a list of Cloudlets to be printed, and then
 * calling the {@link #build()} method.</p>
 *
 * @author Sri Vibhu Paruchuri
 * 
 */
@Getter
public class TableBuilderEnergy extends CloudletsTableBuilder {
	
    public static final String DEF_FORMAT = "%d";
    private static final String SECONDS = "Seconds";
    private static final String CPU_CORES = "CPU cores";
    private static final String ID = "ID";
    private static final String MI = "MI";
    private static final String kW = "kW";
    private static final String cents = "cents (¢)";
    private static final String HOST_TDP = "TDP (W)";
    
    private static ArrayList<Integer> tdp ;

    /**
     * The format for time columns.
     */
    private String timeFormat = "%.1f";
    
    
    /**
     * The format for energy column.
     */
    private String energyFormat = "%.5f";

    /**
     * The format for ID columns.
     */
    private String idFormat = DEF_FORMAT;

    /**
     * The format for cloudlet length columns.
     */
    private String lengthFormat = DEF_FORMAT;

    /**
     * The format for columns indicating number of PEs.
     */
    private String peFormat = DEF_FORMAT;

    /**
     * Instantiates a builder to print the list of Cloudlets using the
     * default {@link MarkdownTable}.
     * To use a different {@link Table}, check the alternative constructors.
     *
     * @param list the list of Cloudlets to print
     */

    public TableBuilderEnergy(final List<? extends Cloudlet> list, ArrayList<Integer> tdp) {
        super(list);
        this.tdp = tdp;
        
    }
    
    @Override
    protected void createTableColumns() {
        addColumn(getTable().newColumn("Cloudlet", ID), Identifiable::getId);

        // 1 extra space to ensure proper formatting
        addColumn(getTable().newColumn(" Status") , cloudlet -> cloudlet.getStatus().name());

        addColumn(getTable().newColumn("DC", ID, idFormat), cloudlet -> cloudlet.getVm().getHost().getDatacenter().getId());

        addColumn(getTable().newColumn("Host", ID, idFormat), cloudlet -> cloudlet.getVm().getHost().getId());
        addColumn(getTable().newColumn("Host PEs ", CPU_CORES, peFormat), cloudlet -> cloudlet.getVm().getHost().getWorkingPesNumber());
        addColumn(getTable().newColumn("Host TDP ", HOST_TDP, peFormat), cloudlet -> tdp.get((int)cloudlet.getVm().getHost().getId()));

        addColumn(getTable().newColumn(" VM", ID, idFormat), cloudlet -> cloudlet.getVm().getId());

        // 3 extra spaces to ensure proper formatting
        addColumn(getTable().newColumn("   VM PEs", CPU_CORES, peFormat), cloudlet -> cloudlet.getVm().getPesNumber());
        addColumn(getTable().newColumn("CloudletLen", MI, lengthFormat), Cloudlet::getLength);
        addColumn(getTable().newColumn("FinishedLen", MI, lengthFormat), Cloudlet::getFinishedLengthSoFar);
        addColumn(getTable().newColumn("CloudletPEs", CPU_CORES, peFormat), Cloudlet::getPesNumber);
        addColumn(getTable().newColumn("StartTime", SECONDS, timeFormat), Cloudlet::getStartTime);
        addColumn(getTable().newColumn("FinishTime", SECONDS, timeFormat), Cloudlet::getFinishTime);
        addColumn(getTable().newColumn("ExecTime", SECONDS, timeFormat), Cloudlet::getTotalExecutionTime);
        
        // Energy in kW
        // 1 extra space to ensure proper formatting
        addColumn(getTable().newColumn(" Energy", kW, energyFormat), cloudlet ->
        ((double) cloudlet.getPesNumber()  / cloudlet.getVm().getHost().getPesNumber()) * tdp.get((int)cloudlet.getVm().getHost().getId()) / 1000.0);  
                
    }

}
