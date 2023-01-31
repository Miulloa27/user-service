package com.mikauran.microevo.userservice.service;


import com.mikauran.microevo.userservice.entity.UserMicro;
import com.mikauran.microevo.userservice.entity.vo.Department;
import com.mikauran.microevo.userservice.entity.vo.ResponseTemplateVO;
import com.mikauran.microevo.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public UserMicro saveUser(UserMicro user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        UserMicro user = userRepository.findByUserId(userId);

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }
}
