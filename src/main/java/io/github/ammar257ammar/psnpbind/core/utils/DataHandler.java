/**
* Binding Pocket SNPs' effect on Binding Affinity Database Project (PSnpBind)
* 
*Copyright (C) 2019-2021  Ammar Ammar <ammar257ammar@gmail.com> ORCID:0000-0002-8399-8990
*
*This program is free software: you can redistribute it and/or modify
*it under the terms of the GNU Affero General Public License as published by
*the Free Software Foundation, either version 3 of the License, or
*(at your option) any later version.
*
*This program is distributed in the hope that it will be useful,
*but WITHOUT ANY WARRANTY; without even the implied warranty of
*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*GNU Affero General Public License for more details.
*
*You should have received a copy of the GNU Affero General Public License
*along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*/

package io.github.ammar257ammar.psnpbind.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;

/**
 * A utility class to perform operations on datasets of type List (String[])
 * It can print first rows of the dataset, return number of rows and columns, 
 * and write the dataset to TSV file
 * 
 * @author Ammar Ammar
 * 
 */
public class DataHandler {

    public static final int MAX_NUMBER_OF_RECORDS=10;
	
    /**
	 * write a dataset to TSV file.
	 * @param dataset to be written to the filesystem
	 * @param path of the file to write the dataset to it
	 * @param header row for the dataset
	 */
	public static void writeDatasetToTSV(List<String[]> dataset, String path, String[] header){
		
		Writer outputWriter;
		
		try {
			
			outputWriter = new FileWriter(new File(path));

			TsvWriter writer = new TsvWriter(outputWriter, new TsvWriterSettings());
		
			if(header != null){
				writer.writeHeaders(header);
			}
			
			writer.writeStringRowsAndClose(dataset);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Overload writeDatasetToTSV(dataset, path, header) to write datasets without header.
	 * @param dataset to be written to the filesystem
	 * @param path of the file to write the dataset to it
	 */
	public static void writeDatasetToTSV(List<String[]> dataset, String path){
		writeDatasetToTSV(dataset, path, null);
	}
	
	/**
	 * Print the first N rows of the specified dataset to console (to examine the dataset)
	 * @param dataset to print its head
	 * @param num number of rows to be printed
	 */
	public static void printDatasetHead(List<String[]> dataset, int num) {
		
		for(int i=0; i<num; i++) {
			
			String[] row = dataset.get(i);
			
			for(int j=0; j<row.length; j++) {
				System.out.print(row[j]+"\t");
			}
			System.out.println();
		}
	}
	

	/**
	 * Overload the method printDatasetHead(dataset, num) to print always the first 10 rows to console
	 * @see #printDatasetHead(List, int) 
	 * @param dataset to print its head
	 */
	public static void printDatasetHead(List<String[]> dataset) {
		
		if(dataset.size() < MAX_NUMBER_OF_RECORDS) {
			printDatasetHead(dataset, dataset.size());
		}else {
			printDatasetHead(dataset, MAX_NUMBER_OF_RECORDS);
		}
	
	}
	

	/**
	 *  Print the number of columns and rows of the specified dataset to the console
	 * @param dataset to print its stats
	 */
	public static void printDatasetStats(List<String[]> dataset) {
		
		System.out.println("Number of rows: " + dataset.size());
		System.out.println("Number of columns: " + dataset.get(0).length);
	}
	

	/**
	 * A method to write a string to a file
	 * @param data is the input String
	 * @param path where the output file should be written to
	 */
	public static void writeStringToFile(String data, String path) {
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)))){
			
			bw.write(data);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
