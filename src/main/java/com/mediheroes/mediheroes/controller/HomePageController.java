package com.mediheroes.mediheroes.controller;

import com.mediheroes.mediheroes.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class HomePageController {

    @GetMapping("/")
    @ResponseBody
    public UserResponse getHomePage() {
        return new UserResponse("Niklas", "Hanft");
    }


    @GetMapping("/users")
    @ResponseBody
    public ArrayList<UserResponse> getUsers(){
        var userList = new ArrayList<UserResponse>();

        var userOne = new UserResponse("Niklas", "Hanft");
        var userTwo = new UserResponse("Pia", "Depenbusch");

        userList.add(userOne);
        userList.add(userTwo);
        return userList;
    }
}
