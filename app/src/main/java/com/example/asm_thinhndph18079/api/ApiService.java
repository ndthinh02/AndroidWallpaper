package com.example.asm_thinhndph18079.api;

import com.example.asm_thinhndph18079.model.ParentPhoto;
import com.example.asm_thinhndph18079.model.Photos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    //https://www.flickr.com/services/rest/?method=flickr.favorites.getList&api_key=9de89dfc23651cb5bed2366a3e8edcc2&user_id=195017181%40N06&extras=description%2C+license%2C+date_upload%2C+date_taken%2C+owner_name%2C+icon_server%2C+original_format%2C+last_update%2C+geo%2C+tags%2C+machine_tags%2C+o_dims%2C+views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&per_page=20&page=1&format=json&nojsoncallback=1


    @GET("services/rest/?method=flickr.favorites.getList&api_key=9de89dfc23651cb5bed2366a3e8edcc2&user_id=195017181%40N06&extras=description%2C+license%2C+date_upload%2C+date_taken%2C+owner_name%2C+icon_server%2C+original_format%2C+last_update%2C+geo%2C+tags%2C+machine_tags%2C+o_dims%2C+views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&per_page=40&page=2&format=json&nojsoncallback=1")
    Call<ParentPhoto> callApiPhoto();
}
