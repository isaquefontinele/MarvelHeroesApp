package com.example.isaqu.marvelheroes.data;

import com.example.isaqu.marvelheroes.model.CharacterListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {

    String URL = "https://gateway.marvel.com/";
    String API_KEY = "1f54bd990f1cdfb230adb312546d765d";


    @GET("/v1/public/characters?orderBy=name")
    Observable<CharacterListResponse> listCharacters(
            @Query("ts") String ts,
            @Query("apikey") String apiKey,
            @Query("hash") String hash);
}
