package com.example.ToDoList.service;

import java.util.HashSet;
import java.util.List;


import com.example.ToDoList.entity.ToDo;
import com.example.ToDoList.repository.TodoRepository;
import com.example.ToDoList.exceptions.NotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {
    
    private final TodoRepository toDoRepository;

    public ToDoService(TodoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public ToDo createToDo(ToDo todo) {
        if(todo.getTitle() == null || todo.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Todo title cannot be empty");
        }

        if(todo.getCompleteByDate() == null) {
            throw new IllegalArgumentException("Todo date cannot be empty");
        }

        return toDoRepository.save(todo);
    }

    public ToDo updateToDo(Long toDoId, ToDo partialToDo) {
        ToDo existingToDo = getToDoByIdOrThrow(toDoId);
        
        BeanUtils.copyProperties(partialToDo, existingToDo, getNullPropertyNames(partialToDo));

        return toDoRepository.save(existingToDo);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        HashSet<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {

            if (src.getPropertyValue(pd.getName()) == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public void deleteToDo(Long toDoId) {
        ToDo exsistingToDo = getToDoByIdOrThrow(toDoId);

        toDoRepository.delete(exsistingToDo);
    }

    public ToDo getToDoByIdOrThrow(Long toDoId) {
        ToDo existingToDo = toDoRepository.findById(toDoId)
            .orElseThrow(() -> new NotFoundException(toDoId));
        return existingToDo;
    }

    public List<ToDo> getAllToDoOrThrow() {
        return toDoRepository.findAll();
    }


    // public ToDo markToDoCompleted(Long toDoId) {
    //     ToDo existingToDo
    // }


}
