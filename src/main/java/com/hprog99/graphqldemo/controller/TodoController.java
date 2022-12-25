package com.hprog99.graphqldemo.controller;

import com.hprog99.graphqldemo.model.Todo;
import com.hprog99.graphqldemo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TodoController {

    private TodoRepository todoRepository;

    @QueryMapping
    public List<Todo> todos(){
        return todoRepository.findAll();
    }

    @QueryMapping
    public Todo todo(@Argument("id") Long id){
        return todoRepository.getReferenceById(id);
    }

    @MutationMapping
    public Todo addTodo(@Argument("text") String text){
        return todoRepository.save(new Todo(text));
    }

    @MutationMapping
    public Todo deleteTodo(@Argument("id") Long id){
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isPresent()){
            todoRepository.delete(todoOptional.get());
            return todoOptional.get();
        }
        return new Todo(id);
    }

    @MutationMapping
    public Todo updateTodo(@Argument("id") Long id, @Argument("text") String text, @Argument("completed") Boolean completed){
        Todo todo = todoRepository.getReferenceById(id);
        if(!text.isBlank()){
            todo.setText(text);
        }
        todo.setCompleted(completed);
        return todoRepository.save(todo);
    }

}