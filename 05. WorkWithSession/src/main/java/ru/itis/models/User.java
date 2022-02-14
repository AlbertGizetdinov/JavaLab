package ru.itis.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
