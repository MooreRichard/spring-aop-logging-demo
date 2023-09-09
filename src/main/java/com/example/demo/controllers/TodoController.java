package com.example.demo.controllers;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.handler.TodoItemException;
import com.example.demo.models.TodoItem;
import com.example.demo.repositories.TodoRepository;

@RestController
@RequestMapping(value = "/api")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/generate")
    public ResponseEntity<List<TodoItem>> generateTodoItems() {
        try {
            for (int i = 0; i < 10; i++) {
                TodoItem item = new TodoItem();
                String title = StringUtils.split(UUID.randomUUID().toString(), "-")[0];
                String description = "Not so random description";

                Date created = new Date(Instant.now().toEpochMilli());
                item.setCreated(created);
                item.setTitle(title);
                item.setDescription(description);
                todoRepository.save(item);
            }
            List<TodoItem> savedItems = new ArrayList<TodoItem>();
            todoRepository.findAll().forEach(savedItems::add);
            return new ResponseEntity<List<TodoItem>>(savedItems, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new TodoItemException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<TodoItem>> getAllTodoItems() {
        try {
            List<TodoItem> items = new ArrayList<TodoItem>();
            todoRepository.findAll().forEach(items::add);
            return new ResponseEntity<List<TodoItem>>(items, HttpStatus.OK);

        } catch (Exception e) {
            throw new TodoItemException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/items")
    public ResponseEntity<TodoItem> createTodoItem(@RequestBody TodoItem item) {

        try {
            TodoItem savedItem = todoRepository.save(item);
            return new ResponseEntity<TodoItem>(savedItem, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new TodoItemException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable("id") Long id) {
        try {
            Optional<TodoItem> item = todoRepository.findById(id);
            return new ResponseEntity<TodoItem>(item.get(), HttpStatus.OK);
        } catch (Exception e) {
            throw new TodoItemException("Item does not exist", HttpStatus.NOT_FOUND);
        }
    }
}
