package com.shhatrat.bikerun2.di;

import android.content.Context;

import com.shhatrat.bikerun2.R;
import com.shhatrat.bikerun2.api.StravaApi;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by szymon on 09.04.17.
 */

public class NetImpl {

    @Inject
    public NetImpl(Context context) {
        c= context;
    }
    Context c;

    public Picasso getPicassoInstance()
    {
       return new Picasso.Builder(c).build();
    }

    public StravaApi getStravaApi()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(c.getString(R.string.address)).build();
        return retrofit.create(StravaApi.class);
    }
}
