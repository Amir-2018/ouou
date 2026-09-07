package com.ouou.controllers;

import com.ouou.dto.CommandeDTO;
import com.ouou.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/commande/")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/getCommands")
    public List<CommandeDTO> getCommands() {
        return commandeService.getAllCommands();
    }

    @PreAuthorize("hasRole('client_admin')")
    @GetMapping("/getCommandeById/{commande_id}")
    public CommandeDTO getCommandeById(@PathVariable int commande_id) {
        return commandeService.getCommanBdeyId(commande_id);
    }

    @PreAuthorize("hasAnyRole('client_user','client_admin')")
    @PostMapping("/saveCommand")
    public CommandeDTO saveCommande(@RequestBody CommandeDTO commandeDTO) {
        return commandeService.saveCommand(commandeDTO);
    }
}