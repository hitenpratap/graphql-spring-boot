package com.hprog99.graphqldemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    private String text;
    private boolean completed;

    public Todo(String text){
        this.text = text;
    }

    public Todo(Long id){
        this.id = id;
    }

}
