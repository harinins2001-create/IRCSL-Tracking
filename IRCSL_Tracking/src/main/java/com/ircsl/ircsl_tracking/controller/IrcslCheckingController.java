package com.ircsl.ircsl_tracking.controller;

import org.springframework.web.bind.annotation.RestController;
import com.ircsl.ircsl_tracking.model.IrcslChecking;
import com.ircsl.ircsl_tracking.service.IrcslCheckingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ircsl")
@CrossOrigin(origins = "http://localhost:3000") // Next.js port එකට අවසර දීම
public class IrcslCheckingController {

    private final IrcslCheckingService service;

    public IrcslCheckingController(IrcslCheckingService service) {
        this.service = service;
    }

    @GetMapping
    public List<IrcslChecking> getAll() {
        return service.getAllCheckings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IrcslChecking> getById(@PathVariable String id) {
        return service.getCheckingById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public IrcslChecking create(@RequestBody IrcslChecking checking) {
        return service.saveChecking(checking);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IrcslChecking> update(@PathVariable String id, @RequestBody IrcslChecking checking) {
        return ResponseEntity.ok(service.updateChecking(id, checking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteChecking(id);
        return ResponseEntity.noContent().build();
    }
}
