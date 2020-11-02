package com.CSCI4050.TermProject.CovidWebsite.controllers;

import com.CSCI4050.TermProject.CovidWebsite.servlets.Utility;
import com.CSCI4050.TermProject.CovidWebsite.entities.AccountEntity;
import com.CSCI4050.TermProject.CovidWebsite.repository.AccountRepository;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Controller
public class RegistrationController {

    @Autowired
    private AccountRepository accountRepo;

    public RegistrationController(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegPage(Model model) {
        model.addAttribute("accountForm", new AccountEntity());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Object registerAccount(@ModelAttribute("accountForm") AccountEntity accountForm, BindingResult bindingResult,
                                  Model model, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        accountForm.setFirstName(accountForm.getFirstName());
        accountForm.setEmail(accountForm.getEmail().toLowerCase());
        accountForm.setPassword(accountForm.getPassword());
        accountForm.setUserName(accountForm.getUserName());

        accountRepo.save(accountForm);

     return "registration";
    }

}
