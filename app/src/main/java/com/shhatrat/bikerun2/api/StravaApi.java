package com.shhatrat.bikerun2.api;

import com.sweetzpot.stravazpot.athlete.model.Athlete;
import com.sweetzpot.stravazpot.athlete.model.Stats;
import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by szymon on 09.04.17.
 */

public interface StravaApi {

    @FormUrlEncoded
    @POST("oauth/token")
    Single<LoginResult> getResultLogin(
            @Field("client_id") Integer client_id,
            @Field("client_secret") String client_secret,
            @Field("code") String code);

    @GET("api/v3/athlete")
    Single<Athlete> getCurrentAthlete();

    @GET("api/v3/athlete/{id}/stats")
    Single<Stats> getAthleteStats(
            @Path("id") int id);
}
