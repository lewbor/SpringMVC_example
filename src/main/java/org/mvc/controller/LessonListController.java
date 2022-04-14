package org.mvc.controller;

import org.mvc.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LessonListController {

    @Autowired
    private LessonRepository repository;

    @GetMapping("/lessons")
    public String index(Model model) {
        model.addAttribute("lessons", this.repository.findAll());
        return "lesson/index";

    }
}
