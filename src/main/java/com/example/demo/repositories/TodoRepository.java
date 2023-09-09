package com.example.demo.repositories;

import com.example.demo.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
    
}
