package imt.tparchi.controller;

import imt.tparchi.model.Contract;
import imt.tparchi.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<Contract> getContracts() {
        return contractService.findAll();
    }

    @PostMapping
    public Contract createContract(@RequestBody Contract contract) {
        return contractService.createContract(contract);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
    }
}
