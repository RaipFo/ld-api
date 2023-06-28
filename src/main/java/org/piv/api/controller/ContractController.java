package org.piv.api.controller;

import lombok.RequiredArgsConstructor;
import org.piv.api.model.ResponseMessage;
import org.piv.api.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contracts")
public class ContractController {
    private final ContractService contractService;

    @PostMapping("/submit")
    public ResponseEntity<ResponseMessage> submit(@RequestHeader(name = "Authorization") String authHeader) {
        contractService.submitContract(authHeader);
        return ResponseEntity.ok(new ResponseMessage("Application sent"));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ResponseMessage> approve(@PathVariable("id") Long id,
                                                   @RequestHeader(name = "Authorization") String authHeader) {
        contractService.approveContract(id, authHeader);
        return ResponseEntity.ok(new ResponseMessage("Application approved"));
    }
}
