package stock;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVReaderWriter {

  public static List<CSVRecord> read(String fileName) throws IOException {
    try (CSVParser parser = new CSVParser(new FileReader(fileName),
        CSVFormat.DEFAULT.withHeader(Constants.SYMBOL, Constants.SERIES, Constants.OPEN,Constants.HIGH, Constants.LOW));) {
      return parser.getRecords();
    }
  }

  public static void write(Map<String, Map<String, Float>> stocks, String outputCSVFilePath) throws IOException {
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputCSVFilePath));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(Constants.SYMBOL,
            Constants.DART_TOTAL, Constants.DAYS, Constants.DART_AVG));) {
      for (Map.Entry<String, Map<String, Float>> entry : stocks.entrySet()) {
        Map<String, Float> stock = entry.getValue();
        csvPrinter.printRecord(entry.getKey(), stock.get(Constants.DART_TOTAL), stock.get(Constants.DAYS),
            stock.get(Constants.DART_TOTAL) / stock.get(Constants.DAYS));
      }
      csvPrinter.flush();
    }
  }
}
