package com.app.eventOrganizer.controller;


import com.app.eventOrganizer.model.TestModel;
import com.app.eventOrganizer.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("/test")
    public TestModel createTestModel ( @RequestParam Long ID, @RequestParam String name) {
        return testService.saveToDB(ID, name);
    }

}
