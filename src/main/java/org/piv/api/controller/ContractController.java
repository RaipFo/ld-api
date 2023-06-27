package org.piv.api.controller;

import org.piv.api.dao.Contract;
import org.piv.api.model.ResponseMessage;
import org.piv.api.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
public class ContractController {
    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/submit")
    public ResponseEntity<ResponseMessage> submit(@RequestHeader(name = "Authorization") String authHeader) {
        contractService.submitContract(authHeader);
        return ResponseEntity.ok(new ResponseMessage("Application sent"));
    }

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        return ResponseEntity.ok(contractService.getAllContracts());
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ResponseMessage> approve(@PathVariable("id") Long id,
                                                   @RequestHeader(name = "Authorization") String authHeader) {
        contractService.approveContract(id, authHeader);
        return ResponseEntity.ok(new ResponseMessage("Application approved"));
    }
}
