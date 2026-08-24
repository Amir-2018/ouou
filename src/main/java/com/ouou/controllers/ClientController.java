package com.ouou.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ouou.dto.ClientDTO;
import com.ouou.services.ClientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping ; 
import org.springframework.web.bind.annotation.RequestBody ; 
import org.springframework.web.bind.annotation.PathVariable ; 

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class ClientController {

    @Autowired
    private ClientService userService;

    
    @GetMapping("/getusers")
    public List<ClientDTO> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/saveuser")
    public ClientDTO saveUser(@RequestBody ClientDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PutMapping("/updateuser")
    public ClientDTO updateUser(@RequestBody ClientDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("deleteuser/{userId}")
    public String deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

}