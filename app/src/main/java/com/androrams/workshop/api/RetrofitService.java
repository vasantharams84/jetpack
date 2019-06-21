package com.androrams.workshop.api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    static String BASE_URL = "https://hiringworkshop.herokuapp.com/api/workshop/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <T> T cteateService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}