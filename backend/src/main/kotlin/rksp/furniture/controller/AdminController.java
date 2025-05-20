//package rksp.furniture.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import rksp.furniture.model.FurnitureItem;
//import rksp.furniture.model.User;
//import rksp.furniture.repository.FurnitureRepository;
//import rksp.furniture.repository.UserRepository;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final UserRepository userRepo;
//    private final FurnitureRepository itemRepo;
//
//    public AdminController(UserRepository userRepo, FurnitureRepository itemRepo) {
//        this.userRepo = userRepo;
//        this.itemRepo = itemRepo;
//    }
//
//    @GetMapping
//    public String dashboard(Model model) {
//        model.addAttribute("users", userRepo.findAll());
//        model.addAttribute("items", itemRepo.findAll());
//        return "admin/dashboard";
//    }
//
//    @PostMapping("/user/update/{id}")
//    public String updateUser(
//            @PathVariable Long id,
//            @RequestParam String username,
//            @RequestParam(required = false) String email
//    ) {
//        User u = userRepo.findById(id).orElseThrow();
//        u.setUsername(username);
//        u.setEmail(email);
//        userRepo.save(u);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/user/delete/{id}")
//    public String deleteUser(@PathVariable Long id) {
//        userRepo.deleteById(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/item/edit/{id}")
//    public String editItemPage(@PathVariable Long id, Model model) {
//        model.addAttribute("item", itemRepo.findById(id).orElseThrow());
//        return "admin/edit_item";
//    }
//
//    @PostMapping("/item/update/{id}")
//    public String updateItem(
//            @PathVariable Long id,
//            @ModelAttribute FurnitureItem form
//    ) {
//        FurnitureItem existing = itemRepo.findById(id).orElseThrow();
//        existing.setTitle(form.getTitle());
//        existing.setDescription(form.getDescription());
//        existing.setPrice(form.getPrice());
//        existing.setCondition(form.getCondition());
//        itemRepo.save(existing);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/item/delete/{id}")
//    public String deleteItem(@PathVariable Long id) {
//        itemRepo.deleteById(id);
//        return "redirect:/admin";
//    }
//}
package rksp.furniture.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rksp.furniture.model.FurnitureItem;
import rksp.furniture.model.User;
import rksp.furniture.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String adminDashboard(Model model, HttpServletRequest request) {
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("furniture", adminService.getAllFurniture());
        model.addAttribute("currentPath", request.getRequestURI());
        return "admin";
    }

    // Удаление пользователя
    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return "redirect:/admin";
    }

    // Обновление email пользователя
    @PostMapping("/users/update/{id}")
    public String updateUserEmail(@PathVariable Long id, @RequestParam String email) {
        adminService.updateUserEmail(id, email);
        return "redirect:/admin";
    }

    // Удаление объявления
    @PostMapping("/furniture/delete/{id}")
    public String deleteFurniture(@PathVariable Long id) {
        adminService.deleteFurniture(id);
        return "redirect:/admin";
    }

    // Обновление объявления
    @PostMapping("/furniture/update/{id}")
    public String updateFurniture(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String condition
    ) {
        adminService.updateFurniture(id, title, description, price, condition);
        return "redirect:/admin";
    }
}
