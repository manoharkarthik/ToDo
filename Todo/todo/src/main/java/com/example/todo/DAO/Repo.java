package com.example.todo.DAO;

import com.example.todo.Model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<ToDo, Long>{

}
