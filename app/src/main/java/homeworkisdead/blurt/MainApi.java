package homeworkisdead.blurt;

/**
 * Created by dcook on 11/1/16.
 */
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by dcook on 9/26/16.
 */
public interface MainApi {
    @GET("api/v2/breachedaccount/{email}")
    Call<ArrayList<MyResponse>> getUsers(@Path("email") String email);
}
