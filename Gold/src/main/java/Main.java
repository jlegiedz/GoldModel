import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        List<GoldModel> responseList = query.execute(api, 8);
        if (!responseList.isEmpty()) {
            printGold(responseList);
        }
        System.out.print("Srednia cena zlota: ");
        System.out.println(average(responseList));

        writeCSVFile(responseList, "nazwa.csv");

        double averageOfthreeDays =averageOfLastThreeDays(responseList);
        System.out.println("Srednia z 3 dni " + averageOfthreeDays);
        if(averageOfthreeDays) {

            double percentage = (diff/average(responseList))*100;
            System.out.println(percentage);
             diff = average(responseThreeDays)- average(responseList);
             percentage = (diff/average(responseList))*100;
            System.out.println(percentage);


            if (averageOfthreeDays > average(responseList)) {
                System.out.println("Sprzedawaj");
            }
            if (averageOfthreeDays < average(responseList)) {
                System.out.println("Kupuj");
            }
        }

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
        csvFilePrinter.printRecords(priceList.subList(1, priceList.size()));

        csvFilePrinter.close();

    }


    public static double averageOfLastThreeDays(List<GoldModel> responseThreeDays){
        return (responseThreeDays.get(0).getCena() +responseThreeDays.get(1).getCena()+ responseThreeDays.get(2).getCena())/3;
    }

//    public static List readCSV() throws FileNotFoundException, IOException {
//        List prices = new ArrayList<>();
//
//        BufferedReader br = new BufferedReader(new FileReader("nazwa.csv"));
//
//        String line = br.readLine();
//        while ((line = br.readLine()) != null && !line.isEmpty()) {
//            String[] lineSplitted = line.split(",");
//            for (int i = 0; i <lineSplitted.length ; i++) {
//                String firstPrice = lineSplitted[i];
//                prices.add(firstPrice);
//            }
//        }
//        br.close();
//
//        return prices;
//    }


}
