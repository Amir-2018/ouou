package com.ouou.dto;
import com.ouou.models.Categorie;
import com.ouou.models.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id  ;
    private String name ;
    private String description ;
    private String price ;
    private String  qteStock ;
    private byte[] image;
    private Categorie categorie ;

}
