package com.journalsystem.controller;

import com.journalsystem.model.Condition;
import com.journalsystem.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {
    @Autowired
    private ConditionService conditionService;

    @GetMapping
    public List<Condition> getAllConditions() {
        return conditionService.getAllConditions();
    }

    @PostMapping
    public Condition addCondition(@RequestBody Condition condition) {
        return conditionService.addCondition(condition);
    }

    @GetMapping("/{id}")
    public Condition getConditionById(@PathVariable Long id) {
        return conditionService.getConditionById(id);
    }
}