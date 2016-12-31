package homeworkisdead.blurt;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    private AlertDialog malertDialog;
    private EditText emailTextView2;
    private Button submitButton;
    private EventBus mBus;
    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private String email;
    private ArrayList<MyResponse> responses;
    private Retrofit mRetrofit;
    private MainService mainService;
    public static final String API_BASE_URL = "https://haveibeenpwned.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBus = EventBus.getDefault();
        if (!mBus.isRegistered(this)) {
            mBus.register(this);
        }

        emailTextView2 = (EditText) findViewById(R.id.emailEditText);
        submitButton = (Button) findViewById(R.id.submit_button);
        String baseURL = API_BASE_URL;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.create();
        mRetrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBus.post(new MainRequestEvent(emailTextView2.getText().toString()));
            }
        });

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Subscribe
    public void onMainResponseEvent(MainResponseEvent event) {
        responses = event.getmResponses();
        // use a linear layout manager



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(false);


        // specify an adapter (see also next example)


        //dialog displaying the result, then adjust what is displayed next based on user selection
        mAdapter = new MyAdapter(responses);

        mRecyclerView.setAdapter(mAdapter);
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
                   String y =response.body().get(0).breachDate;
                    mBus.post(new MainResponseEvent(response.body()));

                }
            }

            @Override
            public void onFailure(Call<ArrayList<MyResponse>> call, Throwable t) {
                String x="";
            }
        });

    }
}


// mBus.post(new MainResponseEvent(response.body().title,response.body().domain, response.body().pwnCount,response.body().breachDate,response.body().getDescription()));
