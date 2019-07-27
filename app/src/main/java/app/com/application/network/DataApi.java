package app.com.application.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import app.com.application.AppConstants;
import app.com.application.model.Beer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by admin on 09/09/17.
 */

public class DataApi {
    private static ApiInterface sApiInterface;

    public static ApiInterface getApi() {
        if (sApiInterface == null) {

            Gson gson = new GsonBuilder()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            sApiInterface = retrofit.create(ApiInterface.class);
        }
        return sApiInterface;
    }

    public interface ApiInterface {

        @GET("beercraft")
        Call<List<Beer>> getBeer();

    }

}
