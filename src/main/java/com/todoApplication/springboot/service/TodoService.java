package com.todoApplication.springboot.service;

import com.todoApplication.springboot.beans.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static List<TODO> todos= new ArrayList<>();
    private static int todosCount=0;
    static{
        todos.add(new TODO(++todosCount,"ankish","Learn Spring Boot",
                LocalDate.now().plusYears(1),true));
        todos.add(new TODO(++todosCount,"ankish","Learn AWS",
                LocalDate.now().plusYears(1),false));
        todos.add(new TODO(++todosCount,"ankish","Learn React",
                LocalDate.now().plusYears(1),false));
    }

    public List<TODO> getListTodo(String username) {

        Predicate <? super TODO> predicate =todo -> todo.getUsername().equalsIgnoreCase(username);
        logger.debug("todos by username"+todos.stream().filter(predicate).toList());
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String description, String name, LocalDate targetDate, int id){

        if(id > 0){
            todos.add(new TODO(id, name ,description,targetDate,false));
            return;
        }
        System.out.println("todosCount:"+todosCount+"name:"+name+"targetDate:"+ targetDate );
        todos.add(new TODO(++todosCount, name ,description,targetDate,false));
    }

    public void deleteTodo(int id) {
        Predicate<? super TODO> predicate = todos -> todos.getId() == id;
        todos.removeIf(predicate);
    }

    public TODO findByID(int id) {
        Predicate<? super TODO> predicate = todos -> todos.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }
}