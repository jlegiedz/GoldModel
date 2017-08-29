import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.text.html.parser.Parser;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GOLD_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NBP api = retrofit.create(NBP.class);

        GoldQuery query = new GoldQuery();

        List<GoldModel> responseList = query.execute(api, 3);
        if (!responseList.isEmpty()) {
            printGold(responseList);
        }
        System.out.print("Srednia cena zlota: ");
        System.out.println(average(responseList));

        File csv = Paths.get("C:\\Exercism\\java\\gold\\", "nazwa.csv").toFile();
//        Reader reader = new FileReader(csv);



        writeCSVFile(responseList, "nazwa.csv");

    }

    public static void printGold(List<GoldModel> list) {
        list.forEach(goldModel -> System.out.println(goldModel.getCena()));
    }

    public static double average(List<GoldModel> list) {
        double average = 0;
        double suma = 0;
        double num = list.size();
        List<Double> priceList = new ArrayList<>();
        list.forEach(goldModel -> priceList.add(goldModel.getCena()));
        for (int i = 0; i < priceList.size(); i++) {
            suma = suma + priceList.get(i);
        }
        average = suma / priceList.size();
        return average;
    }

    public static void writeCSVFile(List<GoldModel> responseList, String fileName) throws IOException {

        String csv = "C:\\Exercism\\java\\gold";
        FileWriter fileWriter = null;

        fileWriter = new FileWriter(fileName);
        CSVPrinter csvFilePrinter = CSVFormat.RFC4180.print(fileWriter);

        List<Double> priceList = new ArrayList<>();
        csvFilePrinter.printRecord(Constants.FILE_HEADER);
        responseList.forEach(goldModel -> priceList.add(goldModel.getCena()));
        csvFilePrinter.printRecord(priceList.get(0), average(responseList));
        csvFilePrinter.printRecords(priceList.subList(1,priceList.size()));

        csvFilePrinter.close();
    }
    public static void readCSVFile(Reader reader){

    }

}
