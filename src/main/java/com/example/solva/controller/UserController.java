package com.example.solva.controller;

import com.example.solva.ExchangeRate;
import com.example.solva.Transaction;
import com.example.solva.TransferRequestDto;
import com.example.solva.User;
import com.example.solva.repository.ExchangeRateRepository;
import com.example.solva.service.CurrencyService;
import com.example.solva.service.TransactionService;
import com.example.solva.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;
    @Autowired
    private CurrencyService currencyService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public UserController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Генерация случайных значений и регистрация пользователя
        User registeredUser = userService.registerUser(user.getName(), user.getEmail(), user.getCurrency(), user.getPassword());
        model.addAttribute("message", "Регистрация успешна! Ваш номер счета: " + registeredUser.getBankAccount() + ", сумма на счете: " + registeredUser.getAmount() + " " + registeredUser.getCurrency());
        return "redirect:/user/login";
    }

    @GetMapping("/dashboard")
    public String userDashboard(Model model, @SessionAttribute("userId") Integer currentUserId) {
        System.out.println("Current user ID from session: " + currentUserId);

        User currentUser = userService.findById(currentUserId);
        List<Transaction> transactions = transactionService.findTransactionsByUser(currentUserId);
        ExchangeRate usdToKztRate = exchangeRateRepository.findTopByCurrencyPairOrderByDateDesc("USD/KZT")
                .orElse(new ExchangeRate("USD/KZT", new BigDecimal("0"), LocalDate.now()));
        ExchangeRate usdToRubRate = exchangeRateRepository.findTopByCurrencyPairOrderByDateDesc("USD/RUB")
                .orElse(new ExchangeRate("USD/RUB", new BigDecimal("0"), LocalDate.now()));

        model.addAttribute("user", currentUser);
        model.addAttribute("users", userService.findAllOtherUsers(currentUserId));
        model.addAttribute("transactions", transactions);
        model.addAttribute("usdToKztRate", usdToKztRate);
        model.addAttribute("usdToRubRate", usdToRubRate);
        return "dashboard";
    }

    @PostMapping("/transfer")
    public String transferMoney(@ModelAttribute TransferRequestDto request, @SessionAttribute("userId") Integer currentUserId) {
        transactionService.transferMoney(currentUserId, request.getReceiverId(), new BigDecimal(request.getAmount()), request.getCurrency());
        return "redirect:/user/dashboard";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.findByEmailAndPassword(email, password);
        if (user != null) {
            session.setAttribute("userId", user.getId());
            return "redirect:/user/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid email or password.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/update-rates")
    public String updateExchangeRates() {
        currencyService.updateExchangeRates();
        return "redirect:/user/dashboard";
    }
}
