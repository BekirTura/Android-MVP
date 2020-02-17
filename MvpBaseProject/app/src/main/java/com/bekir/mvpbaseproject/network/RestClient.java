package com.bekir.mvpbaseproject.network;



import com.bekir.mvpbaseproject.model.response.bizclub.EventListResponse;

import retrofit2.Call;

public class RestClient implements BaseService {


    private static Environment CURRENT_ENVIRONMENT = Environment.PROD;


    private static final String PRE_PROD_KEY = "/preprod";
    private static final String API_KEY = "/api";


    private static final String PROD_HOST = "*****";
    private static final String DEVELOPMENT_HOST = "*****";

    private static final String PRE_PROD_HOST = String.format("%s%s", PROD_HOST, PRE_PROD_KEY);

    public static String getApiUrl() {
        if(CURRENT_ENVIRONMENT == Environment.DEV) {
            return String.format("%s%s", DEVELOPMENT_HOST, API_KEY);
        } else if (CURRENT_ENVIRONMENT == Environment.PRE_PROD) {
            return String.format("%s", PRE_PROD_HOST);
        } else {
            return String.format("%s%s", PROD_HOST, API_KEY);
        }
    }


    private enum Environment {
        DEV, PRE_PROD, PROD
    }


    private static RestClient ourInstance = new RestClient();

    private BaseService baseService;

    public static RestClient getInstance() {
        return ourInstance;
    }

    private RestClient() {
        baseService = BaseServiceFactory.makeBaseService(getApiUrl(), BaseServiceFactory.makeOkHttpClient(BaseServiceFactory.makeLoggingInterceptor()));
    }


    public Call<EventListResponse> getEventList(){
        return baseService.getEventList();
    }
}