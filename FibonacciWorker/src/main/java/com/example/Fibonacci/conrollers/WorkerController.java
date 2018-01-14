package com.example.Fibonacci.conrollers;

import com.example.Fibonacci.domain.FNumber;
import com.example.Fibonacci.domain.FRepo;
import com.example.Fibonacci.domain.FResponse;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    private FRepo fRepo;

    private BigInteger calcNewFib(FNumber fnumber){

        BigInteger val1 = fnumber.getValue();
        BigInteger val2 = fnumber.getValue2();

        if (fnumber.getNumber() == 1 || fnumber.getNumber() == 2) {
            return BigInteger.valueOf(1);
        }

        return val1.add(val2);
    }

    @GetMapping(value = "newValue")
    @Transactional
    public FResponse increment() {

        String host = getHost();

        FResponse fResponse = new FResponse(false, host, 0, BigInteger.valueOf(0));
        FNumber fNumber = fRepo.getValues();

        int number = fNumber.getNumber() + 1;
        BigInteger newValue = calcNewFib(fNumber);

        if ( newValue.compareTo(BigInteger.valueOf(0)) < 0) {
            return fResponse;
        }

        fNumber.updateValues(fNumber.getValue2(), newValue);
        fNumber.setNumber(number);

        fRepo.save(fNumber);

        fResponse.setSuccess(true);
        fResponse.setValue(newValue);
        fResponse.setNumber(number);

        return fResponse;
    }

    private String getHost (){
        String host;
        try{
            host = InetAddress.getLocalHost().toString();
        }
        catch (UnknownHostException ex){
            host = "Incorrect host";
        }

        return host;
    }
}
