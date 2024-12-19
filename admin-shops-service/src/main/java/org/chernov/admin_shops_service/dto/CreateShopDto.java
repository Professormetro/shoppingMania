package org.chernov.admin_shops_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Getter
@Setter
public class CreateShopDto {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Logo Image URL cannot be empty")
    private String logoImageUrl;

    @NotEmpty(message = "Username cannot be empty")
    private String sellerUsername;

    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String sellerEmail;

    @NotEmpty(message = "Phone cannot be empty")
    private String sellerPhone;


}
