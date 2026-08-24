package com.ouou.services;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ouou.dto.ClientDTO;
import com.ouou.models.Client;
import com.ouou.repos.ClientRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClientDTO> getAllUsers() {
        List<Client> userList = userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<List<ClientDTO>>() {}.getType());
    }

    public ClientDTO saveUser(ClientDTO userDTO) {
    	
        Client user = modelMapper.map(userDTO, Client.class);

        userRepository.save(modelMapper.map(userDTO, Client.class));
        return userDTO;
    }

    public ClientDTO updateUser(ClientDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO, Client.class));
        return userDTO;
    }

    public String deleteUser(int userId) {
        userRepository.deleteById((userId));
        return "User deleted";
    }
}
