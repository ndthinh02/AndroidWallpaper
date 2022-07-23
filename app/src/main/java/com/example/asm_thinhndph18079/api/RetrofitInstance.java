package com.example.asm_thinhndph18079.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    //https://www.flickr.com/services/rest/?method=flickr.photos.getFavorites&api_key=9de89dfc23651cb5bed2366a3e8edcc2&photo_id=1253576&page=1&per_page=40&format=json&nojsoncallback=1
    public static String BASE_URL = "https://www.flickr.com/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static ApiService getPhoto() {
        ApiService apiService = getRetrofit().create(ApiService.class);
        return apiService;
    }
}
