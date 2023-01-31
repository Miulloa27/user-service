package com.mikauran.microevo.userservice.entity.vo;

import com.mikauran.microevo.userservice.entity.UserMicro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private UserMicro user;
    private Department department;
}
