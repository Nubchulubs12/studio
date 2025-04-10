package org.lauinchcode.codingevents.controllers;


import jakarta.validation.Valid;
import org.lauinchcode.codingevents.data.EventCategoryRepository;
import org.lauinchcode.codingevents.models.EventCategory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {


    private EventCategoryRepository eventCategoryRepository;

    @GetMapping("index")
    public String displayAllCategories(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    };
    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    };
    @PostMapping("create")
    public String processCreateEventCategoryForm(@Valid @ModelAttribute EventCategory eventCategory, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            model.addAttribute(new EventCategory());
            return "eventCategories/create";
        }
        eventCategoryRepository.save(eventCategory);
        return "redirect: /eventCategories/index";
    };
}
