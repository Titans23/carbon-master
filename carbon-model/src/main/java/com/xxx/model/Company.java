package com.xxx.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Company implements Serializable {
    private Long id;

    private String company_name;

    private String tel;

    private String mail;

    private Double carbon_emissions;

    private String address;

    private String username;

    private String avatar;

//    @TableField(select = false)
    private String password;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

}
