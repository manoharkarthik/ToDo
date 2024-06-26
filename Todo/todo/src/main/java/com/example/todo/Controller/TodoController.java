package com.example.todo.Controller;


import com.example.todo.Model.ToDo;
import com.example.todo.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TodoController {


    @Autowired
    private ToDoService service;


    @GetMapping({"/hello"})
    public String hello(){
        return "Hello World";
    }
    @GetMapping({"/","viewToDoList"})
    public String viewAllItems(Model model, @ModelAttribute("message") String message){
        model.addAttribute("list",service.getAllItems());
        model.addAttribute("message",message);

        return "ViewToDoList";
    }

    @GetMapping("/updateToDoStatus/{id}")
    public String updateStatus(@PathVariable Long id , RedirectAttributes redirectAttributes,Model model){
        if (service.updatestatus(id)){
            redirectAttributes.addFlashAttribute("message", "Update Success");
            model.addAttribute("list",service.getAllItems());
            return "viewToDoList";
        }
        redirectAttributes.addFlashAttribute("message","update Failure");
        return "redirect:/viewToDoList";
    }

    @GetMapping("/addToDoItem")
    public String addToDoItem (Model model){
        model.addAttribute("todo", new ToDo());

        return "AddToDoItem";
    }

    @PostMapping("/saveToDoItem")
    public String saveToDoItem(ToDo todo, RedirectAttributes redirectAttributes, Model model) {
        if(service.saveUpdateItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Save Success");
            model.addAttribute("list",service.getAllItems());
            return "viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "Save Failure");
        return "redirect:/addToDoItem";
    }

    @GetMapping("/editToDoItem/{id}")
    public String editToDoItem(@PathVariable Long id, Model model) {
        model.addAttribute("todo", service.getItemById(id));

        return "EditToDoItem";
    }

    @PostMapping("/editSaveToDoItem")
    public String editSaveToDoItem(ToDo todo, RedirectAttributes redirectAttributes, Model model) {
        if(service.saveUpdateItem(todo)) {
            redirectAttributes.addFlashAttribute("message", "Edit Success");
            model.addAttribute("list",service.getAllItems());
            return "viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "Edit Failure");
        return "redirect:/editToDoItem/" + todo.getId();
    }

    @GetMapping("/deleteToDoItem/{id}")
    public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
        if (service.deleteItem(id)) {
            redirectAttributes.addFlashAttribute("message", "Delete Success");
            model.addAttribute("list",service.getAllItems());
            return "viewToDoList";
        }

        redirectAttributes.addFlashAttribute("message", "Delete Failure");
        return "redirect:/viewToDoList";
    }



}
