package ru.mirea.bykonyaia.retrofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("todos")
    Call<List<Todo>> getTodos();
    @PUT("todos/{id}")
    Call<Void> updateTodo(@Path("id") int id, @Body Todo todo);
}
