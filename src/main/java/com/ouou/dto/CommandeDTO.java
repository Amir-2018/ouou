package com.ouou.dto;

import com.ouou.models.Client;
import com.ouou.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeDTO {
    private Integer id  ;
    private String name ;
    private String date ;
    private String quantity ;
    private String total ;
    private String status ;
    private String delivred ;
    private String clientTel;
    private String nameClient ;
    private String lastnameClient ;
    private Product product ;
    private Client client ;
    ;
}
