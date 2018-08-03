package com.example.sathya.ifsc_data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IFSCService {
    @GET("/{IFSC_CODE}")
    Call<IFSCData> getIFSC(@Path("IFSC_CODE") String code);
}

