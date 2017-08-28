import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GoldQuery implements Query {

    public int topCount;

    public GoldQuery() {
        this.topCount = topCount;
    }
    public void validate(){
        if(topCount <=0){
            System.out.println("topCount can't be less or even zero");
        }

    }

    @Override
    public List<GoldModel> execute(NBP api, int topCount ) throws IOException {
        Response<List<GoldModel>> response = api.goldByDays(topCount).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            System.out.println(response.errorBody());
            return Collections.emptyList();
        }
    }
}

