package app.com.application.network;

import com.google.gson.GsonBuilder;

import app.com.application.AppConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 09/09/17.
 */

public class DataApi {
    private static ApiInterface sApiInterface;

    public static ApiInterface getApi() {
        if (sApiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .excludeFieldsWithoutExposeAnnotation()
                            .create()))
                    .build();

            sApiInterface = retrofit.create(ApiInterface.class);
        }
        return sApiInterface;
    }

    interface ApiInterface {

    }

}
