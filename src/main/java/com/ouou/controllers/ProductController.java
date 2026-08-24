package com.ouou.controllers;
import java.util.List;

import com.ouou.dto.CategorieDTO;
import com.ouou.dto.ProductDTO;
import com.ouou.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/product/")
public class ProductController {

    @Autowired
    private ProductService productServices;


    @GetMapping("/getProducts")
    public List<ProductDTO> getProducts() {
        return productServices.getAllProducts();
    }

    @GetMapping("/getProductById/{product_id}")
    public ProductDTO getProductById(@PathVariable int product_id) {
        return productServices.getProductById(product_id);
    }

    @PostMapping("/saveProduct")
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productServices.saveProduct(productDTO);
    }

    @PutMapping("/updateProduct/{productId}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable int productId) {
        return productServices.updateProduct(productDTO,productId);
    }

    @DeleteMapping("deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        return productServices.deleteProduct(productId);
    }


}