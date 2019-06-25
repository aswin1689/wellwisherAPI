package com.wellwisher.api.controller;

import com.wellwisher.api.domain.Child;
import com.wellwisher.api.exception.ResourceNotFoundException;
import com.wellwisher.api.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChildController {
    @Autowired
    ChildRepository childRepository;

    @GetMapping("/children")
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @PostMapping("/children")
    public Child createChild(@Valid @RequestBody Child child) {
        return childRepository.save(child);
    }

    @GetMapping("/children/{id}")
    public Child getChildById(@PathVariable(value="id") Long childId) {
        return childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException("Child", "id", childId));
    }

    @PutMapping("/children/{id}")
    public Child updateChild(@PathVariable(value="id") Long childId, @Valid @RequestBody Child childDetails) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException("Child", "id", childId));

        child.setFirstName(childDetails.getFirstName());
        child.setLastName(childDetails.getLastName());
        child.setHomeTown(childDetails.getHomeTown());
        child.setAge(childDetails.getAge());
        child.setIllness(childDetails.getIllness());

        Child updatedChild = childRepository.save(child);
        return updatedChild;
    }

    @DeleteMapping("/children/{id}")
    public ResponseEntity<?> deleteChild(@PathVariable(value = "id") Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException("Child", "id", childId));

        childRepository.delete(child);

        return ResponseEntity.ok().build();
    }
}
