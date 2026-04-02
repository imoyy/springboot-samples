package com.hxc.springbootweek05.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@TableName("user")
@Schema(name = "User", description = "User entity")
public class User {

    @Schema(description = "Primary key")
    private Long id;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "Password")
    private String password;

    @Schema(description = "Age")
    private Integer age;

    @Schema(description = "Email")
    private String email;

    @Schema(description = "Create time")
    private LocalDateTime createTime;
}
