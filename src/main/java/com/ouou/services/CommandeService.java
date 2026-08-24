package com.ouou.services;

import com.ouou.dto.ClientDTO;
import com.ouou.dto.CommandeDTO;
import com.ouou.dto.ProductDTO;
import com.ouou.models.Commande;
import com.ouou.models.Product;
import com.ouou.repos.CommandeRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommandeService {

    @Autowired
    private CommandeRepo commandRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CommandeDTO> getAllCommands() {
        List<Commande> commandList = commandRepository.findAll();
        return modelMapper.map(commandList, new TypeToken<List<CommandeDTO>>() {}.getType());
    }

    public CommandeDTO saveCommand(CommandeDTO commandeDTO) {
        try{
            System.out.println("will save ");
            System.out.println(commandeDTO);
            System.out.println("After mapper");
            modelMapper.map(commandeDTO, Commande.class) ;
            commandRepository.save(modelMapper.map(commandeDTO, Commande.class));
            return commandeDTO;
        }catch(Exception e) {
            return null ;
        }
    }


}
