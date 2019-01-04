package navneet.com.carimages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DataAdapter dataAdapter;
    private ArrayList<CarItem> CarItems=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);
        mRecyclerView=(RecyclerView)findViewById(R.id.cars_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        parseJson();
    }

    private void parseJson() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://navneet7k.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<List<CarItem>> call1=request.getJson();
        call1.enqueue(new Callback<List<CarItem>>() {
            @Override
            public void onResponse(Call<List<CarItem>> call, Response<List<CarItem>> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body()!=null) {
                    CarItems = new ArrayList<>(response.body());
                    dataAdapter=new DataAdapter(CarItems,MainActivity.this);
                    mRecyclerView.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CarItem>> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,"Oops! Something went wrong!",Toast.LENGTH_SHORT).show();
            }

        });
    }
}
