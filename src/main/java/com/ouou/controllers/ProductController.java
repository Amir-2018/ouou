package com.ouou.controllers;
import java.util.List;
import java.io.IOException;

import com.ouou.dto.CategorieDTO;
import com.ouou.dto.ProductDTO;
import com.ouou.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping(value = "/saveProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDTO saveProductWithImage(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("price") String price,
            @RequestPart("image") MultipartFile image) throws IOException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setImage(image.getBytes());
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