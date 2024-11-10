package ru.library.api_library.model.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Token Model Information")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Token Id", example = "123")
    private Integer id;

    @Schema(description = "Access token", example = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJTb2tvbCIsImlhdCI6MTczMTI1NTg5NCwiZXhwIjoxNzMxMzQyMjk0fQ.-tXTK3hVUKSNkzgQWevw3etuVAJYtnHjqAMzuoUTIodbBTYcW_afL7VtBud-iBDO")
    private String accessToken;

    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJTb2tvbCIsImlhdCI6MTczMTI1NTg5NCwiZXhwIjoxNzMxMzQyMjk0fQ.-tXTK3hVUKSNkzgQWevw3etuVAJYtnHjqAMzuoUTIodbBTYcW_afL7VtBud-iBDO")
    private String refreshToken;

    @Schema(description = "Has the user logged out", example = "true")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}