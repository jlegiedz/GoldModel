import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

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

        GoldQuery query =  new GoldQuery();

        List<GoldModel> responseList = query.execute(api,3);
        if(!responseList.isEmpty()){
            printGold(responseList);
        }
        System.out.println(average(responseList));


    }

    public static void printGold(List<GoldModel> list){
        list.forEach(goldModel -> System.out.println(goldModel.getCena()));
    }
    public static double average(List<GoldModel> list){
        double average = 0;
        double suma = 0;
        double num = list.size();
        List<Double> priceList = new ArrayList<>();
        list.forEach(goldModel ->  priceList.add(goldModel.getCena()));
        for (int i =0; i < priceList.size(); i++) {
        suma = suma + priceList.get(i);
        }
        average = suma/priceList.size();
        System.out.print("Srednia cena zlota: ");
        return average;
    }

}
