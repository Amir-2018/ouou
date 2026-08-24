package com.ouou.services;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ouou.dto.CategorieDTO;
import com.ouou.models.Categorie;
import com.ouou.repos.CategoryRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CategorieService {

    @Autowired
    private CategoryRepo categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CategorieDTO> getAllCategories() {
        List<Categorie> categorieList = categoryRepository.findAll();
        return modelMapper.map(categorieList, new TypeToken<List<CategorieDTO>>() {}.getType());
    }

    public CategorieDTO saveCategorie(CategorieDTO categorieDTO) {
    	try {
    	    categoryRepository.save(modelMapper.map(categorieDTO, Categorie.class));
            return categorieDTO;
    		
    	}catch(Exception e) {
    		return null ; 
    	}
    
    }

    public CategorieDTO updateCategorie(CategorieDTO categorieDTO,int categoryId) {
    	Categorie categorie = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categorie not found"));
    	CategorieDTO categorieDTOSearched =  modelMapper.map(categorie, CategorieDTO.class);
    	categorieDTOSearched.setName(categorieDTO.getName());
    
    	categoryRepository.save(modelMapper.map(categorieDTOSearched, Categorie.class));
        return categorieDTOSearched;
    }

    public String deleteCategorie(int categoryId) {
    	categoryRepository.deleteById((categoryId));
        return "Category deleted";
    }
}
