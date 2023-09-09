package com.example.demo.models;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="TODO_ITEM")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="Title")
    private String title;

    @Column(name="Description")
    private String description;

    @Column(name="Created")
    private Date created;

    @Override
    public String toString() {
        return "TodoItem [id=" + id + ", title=" + title + ", description=" + description + ", created=" + created
                + "]";
    }
}
