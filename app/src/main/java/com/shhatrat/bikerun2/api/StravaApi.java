package com.shhatrat.bikerun2.api;

import com.sweetzpot.stravazpot.authenticaton.model.LoginResult;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
