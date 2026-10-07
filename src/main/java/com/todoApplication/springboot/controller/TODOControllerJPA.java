package com.todoApplication.springboot.controller;

import com.todoApplication.springboot.beans.TODO;
import com.todoApplication.springboot.service.TODORepository;
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

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("name")
public class TODOControllerJPA{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TODORepository todoRepository;

    @RequestMapping("list-todo")
    public String allTodo(ModelMap modelMap) {

        String username = getLoggedInUsername(modelMap);
        List<TODO> todos = todoRepository.getTODOListByUsername(username);
        modelMap.addAttribute("todos", todos);

        return "listTodo";
    }

/*
when you are not using the ModelAttribute
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

        String name = getLoggedInUsername(modelMap);
        todos.setUsername(name);
        logger.debug("id of new TODO"+todos.getId());
        todoRepository.save(todos);

        return "redirect:list-todo";
    }

    @GetMapping("add-todo")
    public String addTodoPage(ModelMap modelMap) {
        String name = getLoggedInUsername(modelMap);
        TODO todos = new TODO(2,name,"",null,false);
        modelMap.put("todos",todos);
        return "addTodo";
    }

    @GetMapping("delete-todo")
    public String DeleteTodo(@RequestParam int id){
        todoRepository.deleteById(id);

        return "redirect:list-todo";
    }

    @GetMapping("update-todo")
    public String updateTodo(@RequestParam int id, ModelMap modelMap){
        TODO todos = todoRepository.findById(id).get();
        modelMap.addAttribute("todos",todos);
        return "addTodo";
    }

    @PostMapping("update-todo")
    public String updateTodoFinal( ModelMap modelMap, @Valid @ModelAttribute("todos") TODO todo,
                                   BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "addTodo";
        }

        String name = getLoggedInUsername(modelMap);
        todo.setUsername(name);

        todoRepository.save(todo);
        return "redirect:list-todo";
    }

    private String getLoggedInUsername(ModelMap modelmap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
