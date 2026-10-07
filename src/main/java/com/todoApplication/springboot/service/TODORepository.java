package com.todoApplication.springboot.service;

import com.todoApplication.springboot.beans.TODO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TODORepository extends JpaRepository<TODO,Integer> {

    public List<TODO> getTODOListByUsername(String username);
}
