package ru.mirea.bykonyaia.retrofitapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mirea.bykonyaia.retrofitapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(binding.getRoot());

        var retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        var todosApiService = retrofitBuilder.create(ApiService.class);

        todosApiService.getTodos().enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    binding.recyclerView.setAdapter(new TodoAdapter(MainActivity.this, response.body(), new String[]{
                            "https://media.geeksforgeeks.org/wp-content/uploads/geeksforgeeks-24.png",
                            "https://media.geeksforgeeks.org/wp-content/uploads/geeksforgeeks-25.png"
                    }, todosApiService));
                } else {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                    Log.i("HE_HE", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("HE_HE", throwable.getMessage());
            }
        });
    }
}