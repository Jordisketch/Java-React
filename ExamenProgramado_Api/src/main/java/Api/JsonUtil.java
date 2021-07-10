package Api;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtil implements ResponseTransformer {
    private final Gson gson = new Gson();

    @Override
    public String render(Object object) throws Exception{
        return  gson.toJson(object);
    }
}
