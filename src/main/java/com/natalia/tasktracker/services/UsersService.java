package com.natalia.tasktracker.services;

import com.natalia.tasktracker.models.User;
import com.natalia.tasktracker.models.UserRole;
import com.natalia.tasktracker.repositories.UsersRepository;
import com.natalia.tasktracker.util.EmailAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository userRepository;

    @Transactional
    public User create(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }

        return userRepository.save(enrichUser(user));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findALl() {
        return userRepository.findAll();
    }

    @Transactional
    public User edit(Long id, User user) {
        User userToEdit = findById(id);
        userToEdit.setUsername(user.getUsername());
        userToEdit.setEmail(user.getEmail());
        userToEdit.setPassword(user.getPassword());

        return userRepository.save(userToEdit);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User enrichUser(User user) {
        user.setRole(UserRole.USER);

        return user;
    }
}
