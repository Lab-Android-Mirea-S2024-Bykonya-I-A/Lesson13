package ru.mirea.bykonyaia.retrofitapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Todo {
    @SerializedName("userId")
    @Expose
    private	Integer	userId;
    @SerializedName("id")
    @Expose
    private	Integer	id;
    @SerializedName("title")
    @Expose
    private	String	title;
    @SerializedName("completed")
    @Expose
    private	Boolean	completed;

    public Todo() {}
    public Todo(Integer userId, Integer id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }


    public Integer getUserId() {
        return userId;
    }
    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Boolean getCompleted() {
        return completed;
    }
}
