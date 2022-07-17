package com.rent.project.productservice.request.format;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCredObject {
    String token;
    long id;
    String role;

    public String getRoleName() {
        return this.role.substring(22,27);
    }
}
