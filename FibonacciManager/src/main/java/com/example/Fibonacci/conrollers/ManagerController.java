package com.example.Fibonacci.conrollers;

import com.example.Fibonacci.domain.FNumber;
import com.example.Fibonacci.domain.FRepo;
import com.example.Fibonacci.domain.FResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("manager")
public class ManagerController {

    @Value("${docker.worker.url}")
    private String workerUrl;

    @Autowired
    private FRepo fRepo;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "newValue", method = RequestMethod.GET)
    public FResponse newValue (){
        System.out.println();
        System.out.println("newValue");
        System.out.println();

        String url = workerUrl + "/worker/newValue";
        ResponseEntity<FResponse> result = restTemplate.exchange(url , HttpMethod.GET, null, FResponse.class);
        return result.getBody();
    }

    @RequestMapping(value = "getValue", method = RequestMethod.GET)
    public FNumber getValue() {
        System.out.println();
        System.out.println("getValue");
        System.out.println();
        return fRepo.findOne(1);
    }

    @RequestMapping(value = "clear", method = RequestMethod.GET)
    public Map<String, Boolean>  clear() {
        System.out.println();
        System.out.println("clear");
        System.out.println();
        fRepo.deleteAll();
        FNumber first = new FNumber(1, 1, BigInteger.valueOf(1), BigInteger.valueOf(1) );
        fRepo.save(first);
        return Collections.singletonMap("success", true);
    }
}
