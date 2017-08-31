import com.sun.prism.shader.Solid_TextureSecondPassLCD_Loader;
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
        List<GoldModel> model = Collections.emptyList();
        if (topCount <= 365 && topCount > 3) {
            Response<List<GoldModel>> response = api.goldByDays(topCount).execute();
            if (response.isSuccessful()) {
                model = response.body();
            } else {
                System.out.println(response.errorBody());
                model = Collections.emptyList();
            }
        }
        else {
            System.out.println("Top count cannot be higher than 365 days and should be higher than 3 to give the recommendation.");
        }
        return model;
    }
}

