import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.FileWriter;
import java.io.IOException;
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

        List<GoldModel> responseList = query.execute(api, 4);
        if (!responseList.isEmpty()) {
            printGold(responseList);
        }
        System.out.println("The average price of gold is: " + average(responseList));
        writeCSVFile(responseList, "nazwa.csv");
        recommend(responseList);
    }

    private static void recommend(List<GoldModel> responseList) {
        double averageOfthreeDays = averageOfLastThreeDays(responseList);
        double averageAll = average(responseList);
        double diff ,percentage;
        System.out.println("The average price from last three days is: " + averageOfthreeDays);
        if (averageOfthreeDays > averageAll) {
            diff = (averageAll / averageOfthreeDays);
            percentage = 1-diff;
            if (percentage <= 0.01) {
                System.out.println("Keep it!");
            } else {
                System.out.println("Sell it!");
            }
        }
        if (averageOfthreeDays < averageAll) {
            diff = (averageOfthreeDays / averageAll);
            percentage = 100 -diff;
            if (percentage >= 0.01) {
                System.out.println("Keep it!");
            } else {
                System.out.println("Buy more!");
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


    public static double averageOfLastThreeDays(List<GoldModel> responseThreeDays) {
        return (responseThreeDays.get(0).getCena() + responseThreeDays.get(1).getCena() + responseThreeDays.get(2).getCena()) / 3;
    }




}
