package redspark.io.miguelopolis.data.api;

import okhttp3.OkHttpClient;
import redspark.io.miguelopolis.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by infra on 10/05/17.
 */

public class WebServiceClient {

    private OkHttpClient httpClient;

    public WebServiceClient build() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WebServiceClient.class);
    }
}
