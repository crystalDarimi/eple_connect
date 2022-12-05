package com.crystal.eple.dto.request;


import com.crystal.eple.domain.entity.Role;
import lombok.*;
import lombok.Builder;



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
    private Boolean isTeacher;
    private Role role;
    private Long calendarId;


}
