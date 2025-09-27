package com.web.catalog.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private String role;
    private StoreInfo store;
    
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreInfo {
        private String id;
        private String name;
        private String description;
        private String phone;
        private String identifier;
    }
}
