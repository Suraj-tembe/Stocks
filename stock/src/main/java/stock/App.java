package stock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;


public class App {

    public static void main(String args[]) throws IOException {

        if (args.length < 2){
            help();
            return;
        }
        Map<String, Map<String, Float>> stocks = new HashMap<String, Map<String, Float>>();

        File inputDir = new File(args[0]);
        File[] csvFiles = inputDir.listFiles();
        List<List<CSVRecord>> csvRescords = new ArrayList<>();

        for (File csvFile : csvFiles) {
            csvRescords.add(CSVReaderWriter.read(csvFile.getPath()));
        }

        for (List<CSVRecord> csvRecord : csvRescords) {
            for (CSVRecord record : csvRecord) {
                if (record.getRecordNumber() != 1) {
                    String symbol = record.get(Constants.SYMBOL);
                    float high = Float.parseFloat(record.get(Constants.HIGH));
                    float low = Float.parseFloat(record.get(Constants.LOW));
                    float dart = high - low;
                    float days = 1;
                    Map<String, Float> existingStock = stocks.get(symbol);
                    Map<String, Float> newStock = new HashMap<String, Float>();
                    if (existingStock != null) {
                        dart += existingStock.get(Constants.DART_TOTAL);
                        days += existingStock.get(Constants.DAYS);
                    }
                    newStock.put(Constants.DART_TOTAL, dart);
                    newStock.put(Constants.DAYS, days);
                    stocks.put(symbol, newStock);
                }
            }
        }
        CSVReaderWriter.write(stocks, args[1]);
    }

    public static void help(){
        System.out.println("Usage: java -jar [jarfile] [paramters]");
        System.out.println("Paramters:");
        System.out.println("\tInput Directory");
        System.out.println("\tOutput filePath");
        System.out.println("Example: java -jar [jarfile] ~/input/ ~/output/output.csv");
    }
}
