package com.crystal.eple.dto.request;


import lombok.*;



@Builder
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
  //  private String email;
    private String username;
    private String password;
    private String id;
    private String email;


}
