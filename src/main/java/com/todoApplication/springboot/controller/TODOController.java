package com.todoApplication.springboot.controller;

import com.todoApplication.springboot.beans.TODO;
import com.todoApplication.springboot.service.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

//@Controller
@Tag(name = "TodoList Controller", description = "TodoList Controller")
@SessionAttributes("name")
public class TODOController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TodoService todoService;

    @RequestMapping("list-todo")
    @Operation(summary = "Fetch To-do List by ID")
    public String allTodo(ModelMap modelMap) {

        String username = (String) modelMap.get("name");
        logger.debug("Inside list-todo username"+username);
        List<TODO> todos = todoService.getListTodo(username);
        modelMap.addAttribute("todos", todos);

        return "listTodo";
    }
/*
when you are not using the MOdelAttribute
    @GetMapping("add-todo")
    public String addTodoPage() {
        logger.debug("Inside the add-todo function");
        return "addTodo";
    }

    @PostMapping("add-todo")
    public String addTodo(@RequestParam String description,
     @RequestParam("targetDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate targetDate,
                          ModelMap model) {

        String name = (String)model.get("name");
        System.out.println("Desc:"+description+"targetDate:"+targetDate+"name:"+ name );
        todoService.addTodo(description, name, targetDate);
        return "redirect:list-todo";
    }
*/
    //when you are using the model attribute
    @PostMapping("add-todo")
    public String addTodo(ModelMap modelMap, @Valid @ModelAttribute("todos") TODO todos, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "addTodo";
        }

        String name = (String)modelMap.get("name");
        todoService.addTodo(todos.getDescription(), name, todos.getLocalDate(),-1);
        return "redirect:list-todo";
    }

    @GetMapping("add-todo")
    public String addTodoPage(ModelMap modelMap) {
        String name = (String)modelMap.get("name");
        TODO todos = new TODO(0,name,"",LocalDate.now().plusYears(1),false);
        modelMap.put("todos",todos);
        return "addTodo";
    }

    @GetMapping("delete-todo")
    public String DeleteTodo(@RequestParam int id){
        todoService.deleteTodo(id);
        return "redirect:list-todo";
    }

    @GetMapping("update-todo")
    public String updateTodo(@RequestParam int id, ModelMap modelMap){
        TODO todos = todoService.findByID(id);
        modelMap.addAttribute("todos",todos);
        return "addTodo";
    }

    @PostMapping("update-todo")
    public String updateTodoFinal( ModelMap modelMap, @Valid @ModelAttribute("todos") TODO todo,
                                   BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addTodo";
        }

        todoService.deleteTodo(todo.getId());

        String name = (String)modelMap.get("name");
        todoService.addTodo(todo.getDescription(),name,todo.getLocalDate(), todo.getId());
        return "redirect:list-todo";
    }

    private String getLoggedInUsername(ModelMap modelmap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
