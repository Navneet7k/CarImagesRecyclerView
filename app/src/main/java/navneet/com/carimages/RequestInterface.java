package navneet.com.carimages;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Navneet Krishna on 16/12/18.
 */
interface RequestInterface {
    @GET("cars_list.json")
    Call<List<CarItem>> getJson();
}
