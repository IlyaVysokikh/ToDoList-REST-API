package com.example.ToDoList.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Entity(name="todos")
public class ToDo {
    
    private 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String title;

    private String description;
    
    private boolean isComplete;
    
    private LocalDate completeByDate;


    public ToDo(String title, String description, LocalDate completeByDate) {
        this.title = title;
        this.description = description;
        this.completeByDate = completeByDate;
    }

    public ToDo() {}


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return description; }

    public boolean getIsComplete() { return isComplete; }
    
    public void setIsComplete(boolean isComplete) { this.isComplete = isComplete; }

    public LocalDate getCompleteByDate() { return completeByDate; }

    public void setCompleteByDate(LocalDate newDate) { completeByDate = newDate; }


    @Override
    public String toString() {
        return "Todo{" + "id=" + id + ", title: " + title + "description: " + description + "isComplete=" + isComplete + "}";

    }

    
}
