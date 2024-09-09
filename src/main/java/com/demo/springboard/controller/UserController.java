package com.demo.springboard.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.springboard.UserDTO;
import com.demo.springboard.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "signin";
    }

    @PostMapping("/login")
    public String tryLogin(final String id, final String password, Model model,
            final HttpServletRequest httpServletRequest) {
        final Map<String, Boolean> blankErrors = userService.getBlankError(id, password);

        if (!blankErrors.isEmpty()) { // login failed - not input
            model.addAttribute("errors", blankErrors);
            return "signin";
        }
        UserDTO user = userService.getUserById(id);

        final Map<String, Boolean> validateErrors = userService.getValidateError(password, user);
        if (!validateErrors.isEmpty()) { // login failed -
            model.addAttribute("errors", validateErrors);
            return "signin";
        }
        httpServletRequest.getSession().setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "signup";
    }

    @PostMapping("/register")
    public String tryRegister(final String id, final String password, final String confirm,
            final String name, Model model) {
        final Map<String, Boolean> blankErrors = userService.getBlankError(id, password, name);
        final UserDTO user = UserDTO.builder().id(id).password(password).name(name).build();
        if (!blankErrors.isEmpty()) { // register failed - not input
            model.addAttribute("errors", blankErrors);
            model.addAttribute("user", user);
            model.addAttribute("confirm", confirm);
            return "signup";
        }
        if (!password.equals(confirm)) {
            model.addAttribute("errors", Map.of("isNotMatch", true));
            model.addAttribute("user", user);
            model.addAttribute("confirm", confirm);
            return "signup";
        }

        final Map<String, Boolean> validateErrors = userService.getRegisterValidateError(user);
        if (!validateErrors.isEmpty()) { // register failed
            model.addAttribute("errors", validateErrors);
            model.addAttribute("user", user);
            model.addAttribute("confirm", confirm);
            return "signup";
        }

        final Map<String, Boolean> duplicateErrors = userService.getDuplicateError(user);
        if (!duplicateErrors.isEmpty()) { // register failed - duplicate
            model.addAttribute("errors", duplicateErrors);
            model.addAttribute("user", user);
            model.addAttribute("confirm", confirm);
            return "signup";
        }
        userService.register(user);
        return "signin";
    }

}
