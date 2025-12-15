package com.google.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
	TaskRepository tr;
	
    @PostMapping("/save") 
	public ResponseEntity<?> saveTask(@RequestBody Task task) {
    	   Task saveTask = tr.save(task);
		return ResponseEntity.status(201).body(saveTask);
		
		
	}
    @GetMapping("/findall")
    public ResponseEntity<?> findAll(){
    	List<Task> find = tr.findAll();
    	
    	if(find.isEmpty()) {
    		return ResponseEntity.status(404).body("not task is present");
    		
    	}else {
    	return ResponseEntity.status(200).body(find);
    	}
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
    	Optional<Task> optional= tr.findById(id);
    	
    	if(optional.isPresent()) {
    		Task task = optional.get();
    		return ResponseEntity.status(200).body(task);
    		
    	}else {
    		return ResponseEntity.status(404).body("invalid id,unable to find");
    	}
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
    	Optional<Task> optional=tr.findById(id);
    	if(optional.isPresent()) {
    		tr.deleteById(id);
    		return ResponseEntity.status(200).body("delete task successfull");
    	}else {
    	return ResponseEntity.status(400).body("invalid id,unable to delete");
    	}
    }
    
}
