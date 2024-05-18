package com.example.todo.Service;


import com.example.todo.DAO.Repo;
import com.example.todo.Model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {

    @Autowired
    Repo repo;


    public List<ToDo> getAllItems(){
        ArrayList<ToDo> todolist =new ArrayList<>();
        repo.findAll().forEach(model ->todolist.add(model));
        return todolist;
    }

    public ToDo getItemById(Long id) {
        return repo.findById(id).get();
    }

    public boolean updatestatus(Long id){

        ToDo todo =getItemById(id);
        todo.setStatus("Completed");

        return saveUpdateItem (todo);
    }

    public boolean saveUpdateItem(ToDo todo) {
        ToDo updateobj=repo.save(todo);
        if (getItemById(updateobj.getId()) != null) {
            return true;
        }

        return false;
    }
    public boolean deleteItem(Long id){
        repo.deleteById(id);
        if (repo.findById(id).isEmpty()) {
            return true;
        }

        return false;
    }


}
