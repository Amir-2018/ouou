package com.ouou.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ouou.dto.CategorieDTO;
import com.ouou.services.CategorieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping ; 
import org.springframework.web.bind.annotation.RequestBody ; 
import org.springframework.web.bind.annotation.PathVariable ; 

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/categorie/")
public class CategoryController {

    @Autowired
    private CategorieService categorieService;

    
    @GetMapping("/getcategories")
    public List<CategorieDTO> getCategorie() {
        return categorieService.getAllCategories();
    }

    @PostMapping("/savecategorie")
    public CategorieDTO saveCategory(@RequestBody CategorieDTO categorieDTO) {
    	return categorieService.saveCategorie(categorieDTO);
  
    }

    @PutMapping("/updatecategorie/{categorieId}")
    public CategorieDTO updateCategorie(@RequestBody CategorieDTO categorieDTO, @PathVariable int categorieId) {
        return categorieService.updateCategorie(categorieDTO,categorieId);
    }

    @DeleteMapping("deletecategorie/{categorieId}")
    public String deleteCategorie(@PathVariable int categorieId) {
        return categorieService.deleteCategorie(categorieId);
    }

}