import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Query {
    List<GoldModel> execute(NBP api, int topCount) throws IOException;
}
