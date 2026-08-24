package com.ouou.controllers;

import com.ouou.dto.CommandeDTO;
import com.ouou.dto.ProductDTO;
import com.ouou.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/command/")

public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @GetMapping("/getCommands")
    public List<CommandeDTO> getCommands() {
        return commandeService.getAllCommands();
    }

    @PostMapping("/saveCommand")
    public CommandeDTO saveCommande(@RequestBody CommandeDTO commandeDTO) {
        return commandeService.saveCommand(commandeDTO);
    }
}
