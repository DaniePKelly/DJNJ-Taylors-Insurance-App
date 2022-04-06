package com.example.taylor_insurance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class WebController {

    private String email = "admin@email.com";
    private String password = "12345";

    //getting quote page
    @GetMapping("/quote")
    public String showForm(Model model) {
        model.addAttribute("quoteForm", new QuoteForm());
        return "Quote";
    }

    //getting form info from quote page
    @PostMapping("/quote")
    public String getForm(@ModelAttribute QuoteForm quoteForm) {
        System.out.println(quoteForm);
        //Do something with data
        return "redirect:/Dashboard";
    }

    //get login page
    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginManager", new LoginManager());
        return "login";
    }

    //get form data for log in page
    @PostMapping("/login")
    public String getLogin(@ModelAttribute LoginManager loginManager) {
        System.out.println(loginManager);
        //if email and password match then log in to dashboard
        if (loginManager.getEmail().equals(email) && loginManager.getPassword().equals(password)) {
            return "redirect:/Dashboard";
        } else {
            //TODO: show error
            return "login";
        }
    }

    //gets dashboard
    @GetMapping("/Dashboard")
    public String getDashboard() {
        return "Dashboard";
    }



}
