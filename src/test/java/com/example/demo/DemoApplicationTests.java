package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controllers.TodoController;
import com.example.demo.exception.handler.TodoItemException;
import com.example.demo.models.TodoItem;
import com.example.demo.repositories.TodoRepository;

@SpringBootTest
@Configuration
class DemoApplicationTests {

	@Autowired
	TodoController todoController;

	@Autowired
	TodoRepository todoRepository;

	private final int numItems = 15;

	@BeforeEach
	void SetupEnvironment(){
		
		todoController.generateTodoItems(numItems);
		List<TodoItem> items = todoRepository.findAll();
		assertEquals(numItems,items.size());
	}

	@Test
	void deleteItem(){
		long itemId = 4;
		todoController.deleteTodoItemById(itemId);
		List<TodoItem> items = todoRepository.findAll();
		assertEquals(numItems-1,items.size());
	}

	@Test
	void deleteMissingItem(){
		assertThrows(TodoItemException.class, ()->{
			long nonExistingItemId = numItems+1;
			todoController.deleteTodoItemById(nonExistingItemId);
		});
	}

}
