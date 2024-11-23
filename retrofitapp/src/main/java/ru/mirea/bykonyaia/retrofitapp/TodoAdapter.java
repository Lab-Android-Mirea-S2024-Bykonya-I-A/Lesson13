package ru.mirea.bykonyaia.retrofitapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    private final String[] imageUris;
    private final ApiService apiService;
    private final LayoutInflater layoutInflater;
    private final List<Todo> todos;
    public TodoAdapter(Context context, List<Todo> todoList, String[] imageUris, ApiService apiService) {
        this.imageUris = imageUris;
        this.apiService = apiService;
        this.layoutInflater = LayoutInflater.from(context);
        this.todos = todoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)	{
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new TodoViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.textViewTitle.setText(todo.getTitle());
        holder.checkBoxCompleted.setChecked(todo.getCompleted());

        try {
            Picasso.get()
                .load(imageUris[new Random().nextInt(imageUris.length)])
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .fit().into(holder.imageView);
        } catch (Exception error) {
            Log.i("HE_HE", "PICASSO ERROR" + error.toString());
        }

        holder.checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            apiService.updateTodo(todo.getId(), new Todo(todo.getUserId(), todo.getId(), todo.getTitle(), isChecked)).
                enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("HE_HE", "UPDATE RESPONSE SUCCESS: " + response.code());
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        Log.i("HE_HE", "UPDATE RESPONSE FAIL: " + throwable);

                    }
                });
        });
    }
    @Override
    public	int	getItemCount()	{
        return	todos.size();
    }
}
