package rksp.furniture.service;

import org.springframework.stereotype.Service;
import rksp.furniture.model.FurnitureItem;
import rksp.furniture.model.User;
import rksp.furniture.repository.FurnitureRepository;
import rksp.furniture.repository.UserRepository;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final FurnitureRepository furnitureRepository;

    public AdminService(UserRepository userRepository, FurnitureRepository furnitureRepository) {
        this.userRepository = userRepository;
        this.furnitureRepository = furnitureRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<FurnitureItem> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public void deleteFurniture(Long itemId) {
        furnitureRepository.deleteById(itemId);
    }

    public void updateUserEmail(Long userId, String newEmail) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void updateFurniture(Long itemId, String title, String description, double price, String condition) {
        FurnitureItem item = furnitureRepository.findById(itemId).orElseThrow();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        item.setCondition(condition);
        furnitureRepository.save(item);
    }
}
