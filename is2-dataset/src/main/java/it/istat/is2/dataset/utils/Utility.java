package it.istat.is2.dataset.utils;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Utility {
	public static void writeObjectToCSV(PrintWriter writer, Map<String, List<String>> dataMap) throws IOException {
		ArrayList<String> header = new ArrayList<String>();
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL);
		try {
			String wi = "";
			for (Iterator<String> iterator = dataMap.keySet().iterator(); iterator.hasNext();) {
				wi = iterator.next();
				header.add(wi);
			}

			csvPrinter.printRecord(header);

			int size = dataMap.get(wi).size();

			for (int i = 0; i < size; i++) {
				List<String> data = new ArrayList<>();
				for (Iterator<String> iterator = dataMap.keySet().iterator(); iterator.hasNext();) {
					wi = iterator.next();
					data.add(dataMap.get(wi).get(i));

				}

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
			csvPrinter.close();
		} catch (Exception e) {
			csvPrinter.flush();
			csvPrinter.close();

		}
	}
}
