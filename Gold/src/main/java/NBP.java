import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

public interface NBP {
    @GET("cenyzlota/last/{topCount}/?format=json")
    Call<List<GoldModel>> goldByDays (
            @Path("topCount") int topCount
    ) throws IOException;
}
