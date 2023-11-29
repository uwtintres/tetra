package excel.reader;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import data.models.HostModel;
import data.models.VmModel;

/**
 * Organized reader class to read VM and Host data from excel files
 * and model the data according to the Host and VM required by CloudSimPlus
 *
 * @author Sri Vibhu Paruchuri
 *
 */

public class excelReaderHostVM {

	/**
	 * This function parses an Excel sheet containing information about VM specifications,
	 * such as MIPS, pes (# cores), ram, bw (bandwidth), image size, TDP, and corresponding host pes. It extracts and returns the relevant VM data model (VmModel)
	 * for further processing in the program.
	 * 
	 * @param filePath The file path to the .csv file containing VM specifications.
	 * @param desiredSheet Index of the sheet to be processed.
	 * @param mipsColumn Index of the column containing mips data of the VMs.
	 * @param pesColumn Index of the column containing pes data of the VMs.
	 * @param ramColumn Index of the column containing ram (memory) data of the VMs.
	 * @param bwColumn Index of the column containing bw data of the VMs.
	 * @param imageSizeColumn Index of the column containing image size data of the VMs.
	 * @param tdpColumn Index of the column containing TDP data of the VMs.
	 * @param hostPesColumn Index of the column containing the corresponding Host pes data of the VMs.
	 * 
	 * @return A data model representing the VM specifications (VmModel).
	 */
	public VmModel readVmData(String filePath,int desiredSheet, int mipsColumn,int pesColumn,int ramColumn, int bwColumn, int imageSizeColumn, int tdpColumn, int hostPesColumn)
	{
		
		VmModel vmData = new VmModel();
		
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

        	Sheet sheet = workbook.getSheetAt(desiredSheet); //Assuming the first sheet is the one to read
        	boolean isFirstRow = true; // Flag to track the first row
        	
            int[] cells = new int[]{ mipsColumn,pesColumn,ramColumn,bwColumn,imageSizeColumn,tdpColumn,hostPesColumn}; 
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                
                for (int i = 0; i < cells.length; i++)
                {
                    Cell cell = row.getCell(cells[i]);

                    if (cell != null) {
                    	
                    	if (i == 0)
                    	{
                    		vmData.addMips((int) cell.getNumericCellValue());
                    	}
                    	else if(i == 1)
                    	{
                    		vmData.addPes((int) cell.getNumericCellValue());
                    	}
                    	else if (i == 2)
                    	{
                    		vmData.addRam((int) cell.getNumericCellValue()*1024);
                    	}
                    	else if (i ==3)
                    	{
                    		vmData.addBw((int) cell.getNumericCellValue()*1024);
                    	}
                    	else if (i == 4)	
                    	{
                    		vmData.addImageSize((int) cell.getNumericCellValue()*1024);
                    	}
                    	else if (i == 5){
                    		vmData.addTdp((int)cell.getNumericCellValue());
                    	}
                    	else
                    	{
                    		vmData.addHostPes((int)cell.getNumericCellValue());
                    	}
                    }
                } 

            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return vmData;
	}
	
	/**
	 * This function parses an Excel sheet containing information about Host specifications,
	 * such as pes (# cores), ram, mips, storage size, bw (bandwidth), and TDP. It extracts and returns the relevant Host data model (HostModel)
	 * for further processing in the program.
	 * 
	 * @param filePath The file path to the .csv file containing Host specifications.
	 * @param desiredSheet Index of the sheet to be processed.
	 * @param pesColumn Index of the column containing pes data of the VMs.
	 * @param ramColumn Index of the column containing ram data of the VMs.
	 * @param mipsColumn Index of the column containing mips data of the VMs.
	 * @param storageColumn Index of the column containing storage data of the VMs.
	 * @param bwColumn Index of the column containing bw data of the VMs.
	 * @param tdpColumn Index of the column containing tdp data of the VMs.
	 * 
	 * @return A data model representing the VM specifications (HostModel).
	 */
	public HostModel readHostData(String filePath,int desiredSheet, int pesColumn,int ramColumn,int mipsColumn, int storageColumn, int bwColumn, int tdpColumn)
	{
		
		HostModel hostData = new HostModel();
		
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

        	Sheet sheet = workbook.getSheetAt(desiredSheet); //Assuming the first sheet is the one to read
        	boolean isFirstRow = true; // Flag to track the first row
        	
            int[] cells = new int[]{ pesColumn,ramColumn,mipsColumn,storageColumn,bwColumn,tdpColumn}; 
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                
                for (int i = 0; i < cells.length; i++)
                {
                    Cell cell = row.getCell(cells[i]);

                    if (cell != null) {
                    	
                    	if (i == 0)
                    	{
                    		hostData.addPes((int) cell.getNumericCellValue());
                    	}
                    	else if(i == 1)
                    	{
                    		hostData.addRam((int) cell.getNumericCellValue()*1024);
                    	}
                    	else if (i == 2)
                    	{
                    		hostData.addMips((int) cell.getNumericCellValue());
                    	}
                    	else if (i ==3)
                    	{
                    		hostData.addStorage((int) cell.getNumericCellValue()*1024);
                    	}
                    	else if (i == 4)
                    		
                    	{
                    		hostData.addBw((int) cell.getNumericCellValue()*1024);
                    	}
                    	else
                    	{
                    		hostData.addTdp((int) cell.getNumericCellValue());
                    	}
                    }
                } 

            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // remove any trailing zeroes
        hostData.deleteCorruptedData();
        return hostData;
	}
	
}
