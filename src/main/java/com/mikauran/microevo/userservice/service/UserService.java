package com.mikauran.microevo.userservice.service;


import com.mikauran.microevo.userservice.entity.UserMicro;
import com.mikauran.microevo.userservice.entity.vo.Department;
import com.mikauran.microevo.userservice.entity.vo.ResponseTemplateVO;
import com.mikauran.microevo.userservice.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public static final String USER_SERVICE = "User-Service";
    public static final String FALLBACK_METHOD = "serviceUserFallback";

    int count=1;
    private Exception e;

    public UserMicro saveUser(UserMicro user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    @CircuitBreaker(name = USER_SERVICE )
    //@TimeLimiter(name = USER_SERVICE, fallbackMethod = FALLBACK_METHOD)
    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("****** INSIDE method getUserWithDepartment of UserService ***********");
        ResponseTemplateVO vo = new ResponseTemplateVO();

        UserMicro user = userRepository.findByUserId(userId);

        //System.out.println("Retry method called"+ count++ + " times at:" + new Date());

        Department department =
                restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId()
                        ,Department.class);

        vo.setUser(user);
        vo.setDepartment(department);

        return  vo;
    }


    public String serviceUserFallback(Long userId, Exception e){
        log.info("<<<<<<< INSIDE FALLBACK METHOD ----->>>>>>>>>>>>><<<<<");
        log.info(String.valueOf(userId));
        return "This is a fallback method for USER SERVICE";
    }

}
