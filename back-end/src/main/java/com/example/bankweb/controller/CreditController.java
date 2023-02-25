package com.example.bankweb.controller;
import com.example.bankweb.Request.CreditApplicationRequest;
import com.example.bankweb.dto.CreditDto;
import com.example.bankweb.service.abstracts.ICreditService;
//import org.apache.log4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/credit")
public class CreditController {
    private final ICreditService creditService;
    private static final Logger LOG = Logger.getLogger(String.valueOf(CreditController.class));

    public CreditController(ICreditService creditService) {
        this.creditService = creditService;
    }
    @PostMapping("/apply")
    public ResponseEntity creditApplication(@RequestBody CreditApplicationRequest creditApplicationRequest
            ) throws ParseException {

        creditService.creditApplication(creditApplicationRequest);
        return ResponseEntity.ok("applied to credit.");
    }
    @GetMapping("/check/application")
    public ResponseEntity<List<CreditDto>> checkCreditApplication(@RequestParam String userIdNumber
                                                    , @RequestParam String userBirthDate){
        return ResponseEntity.ok(creditService.checkCreditApplication(userIdNumber,userBirthDate));

    }

}
