package com.ouou.services;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ouou.dto.UserDTO;
import com.ouou.models.User;
import com.ouou.repos.UserRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>() {}.getType());
    }

    public UserDTO saveUser(UserDTO userDTO) {
    	
        User user = modelMapper.map(userDTO, User.class);

        userRepository.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public String deleteUser(int userId) {
        userRepository.deleteById((userId));
        return "User deleted";
    }
}
