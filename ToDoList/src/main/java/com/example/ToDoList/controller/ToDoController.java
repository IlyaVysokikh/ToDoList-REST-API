package com.example.ToDoList.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ToDoList.service.ToDoService;
import com.example.ToDoList.entity.ToDo;

import java.util.List;


@RestController
@RequestMapping("api/v1/todos")
public class ToDoController {
    
    private final ToDoService toDoService;
    
    ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    } 

    @GetMapping
    public List<ToDo> list(
            @RequestParam(required = false, defaultValue = "false") boolean onlyNotCompleted,
            @RequestParam(required = false) String dateFilter
    ) {
        return toDoService.getFilteredToDos(onlyNotCompleted, dateFilter);
    }

    @GetMapping("/{id}")
    public ToDo getById(@PathVariable Long id) {
        return toDoService.getToDoByIdOrThrow(id);
    }

    @PostMapping
    ToDo newToDo(@RequestBody ToDo newToDo) {
        return toDoService.createToDo(newToDo);
    }    

    @PutMapping("/{id}")
    ToDo updateToDO(@RequestBody ToDo newToDo, @PathVariable Long id) {

        return toDoService.updateToDo(id, newToDo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
    }
}
