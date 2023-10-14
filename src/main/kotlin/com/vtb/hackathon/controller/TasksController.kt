package com.vtb.hackathon.controller

import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
class TasksController {

    @GetMapping("/")
    @ResponseBody
    fun getTasks(): String {
        return "tasks"
    }
}