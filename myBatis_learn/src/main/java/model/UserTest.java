package model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTest {
    private Integer userId;
    private Integer userName;
    private Integer password;
}