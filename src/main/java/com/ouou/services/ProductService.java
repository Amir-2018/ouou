package com.ouou.services;

import com.ouou.dto.CategorieDTO;
import com.ouou.dto.ProductDTO;
import com.ouou.models.Categorie;
import com.ouou.models.Product;
import com.ouou.repos.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {


    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    public ProductDTO getProductById(int product_id) {
        Product product = productRepo.findById(product_id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + product_id));

        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        try{
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
        }catch(Exception e) {
            return null ;
        }
    }

    public ProductDTO updateProduct(ProductDTO productDTO, int productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDTO productDTOSearched =  modelMapper.map(product, ProductDTO.class);
        productDTOSearched.setName(productDTO.getName());
        productDTOSearched.setDescription(productDTO.getDescription());
        productDTOSearched.setPrice(productDTO.getPrice());



        productRepo.save(modelMapper.map(productDTOSearched, Product.class));
        return productDTOSearched;
    }

    public String deleteProduct(int productId) {
        productRepo.deleteById((productId));
        return "Product deleted";
    }


}
