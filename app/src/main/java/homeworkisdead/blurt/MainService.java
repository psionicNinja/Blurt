package homeworkisdead.blurt;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dcook on 11/1/16.
 */
public class MainService {
    private EventBus mBus;
    private Retrofit mRetrofit;
    public String mEmail="";
    public static final String API_BASE_URL = "https://haveibeenpwned.com";

    public MainService(EventBus bus, String email) {
        mBus = bus;
        mBus.register(this);
        mEmail = email;
        String baseURL = API_BASE_URL;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();
        mRetrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create(gson)).build();

    }


    @Subscribe
    public void onMainRequestEvent(final MainRequestEvent event) {
        MainApi mainApi = mRetrofit.create(MainApi.class);
        Call<ArrayList<MyResponse>> call;
        call = mRetrofit.create(MainApi.class).getUsers(event.mEmail);
        String url = call.request().url().toString();

        call.enqueue(new Callback<ArrayList<MyResponse>>() {

            @Override
            public void onResponse(Call<ArrayList<MyResponse>> call, Response<ArrayList<MyResponse>> response) {
                if(!response.isSuccessful())
                {
                    //post failure

                    String x="";
                }
                else
                {
                    //post success
                    String x="";
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MyResponse>> call, Throwable t) {

            }
        });

    }
}
