package org.mvc.controller;

import org.mvc.controller.dto.LessonDto;
import org.mvc.controller.dto.LessonMapper;
import org.mvc.lesson.Lesson;
import org.mvc.lesson.Person;
import org.mvc.repository.LessonRepository;
import org.mvc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LessonController {

    @Autowired
    private LessonRepository repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LessonMapper lessonMapper;


    @ModelAttribute("authors")
    private List<Person> authors() {
        return personRepository.findAll();
    }

    @GetMapping("/lesson/create")
    public String index(Model model) {
        Lesson lesson = new Lesson();

        model.addAttribute("lesson", lesson);
        return "lesson/edit";
    }

    @PostMapping("/lesson/create")
    public String processForm(@ModelAttribute("lesson") @Valid LessonDto lessonDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "lesson/edit";
        }

        Lesson lesson = new Lesson();
        lessonMapper.lessonDtoToLesson(lesson , lessonDto);
        repository.save(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/lesson/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Lesson lesson = repository.findById(id).orElseThrow();
        LessonDto dto = lessonMapper.lessonToLessonDto(lesson);
        model.addAttribute("lesson", dto);

        return "lesson/edit";

    }

    @PostMapping("/lesson/{id}/edit")
    public String processEditForm(@PathVariable("id") Lesson lessonEntity, @ModelAttribute @Valid Lesson lesson, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "lesson/edit";
        }
//        Lesson entity = repository.findById(id).orElseThrow();
//        lessonMapper.lessonDtoToLesson(entity, lessonDto);


        repository.save(lesson);
        return "redirect:/lessons";
    }

}

