package rksp.furniture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rksp.furniture.model.FurnitureItem;
import rksp.furniture.model.User;
import rksp.furniture.repository.FurnitureRepository;
import rksp.furniture.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepo;
    private final FurnitureRepository itemRepo;

    public AdminController(UserRepository userRepo, FurnitureRepository itemRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("items", itemRepo.findAll());
        return "admin/dashboard";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(
            @PathVariable Long id,
            @RequestParam String username,
            @RequestParam(required = false) String email
    ) {
        User u = userRepo.findById(id).orElseThrow();
        u.setUsername(username);
        u.setEmail(email);
        userRepo.save(u);
        return "redirect:/admin";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/item/edit/{id}")
    public String editItemPage(@PathVariable Long id, Model model) {
        model.addAttribute("item", itemRepo.findById(id).orElseThrow());
        return "admin/edit_item";
    }

    @PostMapping("/item/update/{id}")
    public String updateItem(
            @PathVariable Long id,
            @ModelAttribute FurnitureItem form
    ) {
        FurnitureItem existing = itemRepo.findById(id).orElseThrow();
        existing.setTitle(form.getTitle());
        existing.setDescription(form.getDescription());
        existing.setPrice(form.getPrice());
        existing.setCondition(form.getCondition());
        itemRepo.save(existing);
        return "redirect:/admin";
    }

    @PostMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemRepo.deleteById(id);
        return "redirect:/admin";
    }
}
