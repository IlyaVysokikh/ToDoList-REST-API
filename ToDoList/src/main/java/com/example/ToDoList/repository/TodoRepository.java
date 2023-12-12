package com.example.ToDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ToDoList.entity.ToDo;

public interface TodoRepository extends JpaRepository<ToDo, Long> {

}
