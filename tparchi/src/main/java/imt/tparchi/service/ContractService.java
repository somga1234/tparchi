package imt.tparchi.service;

import imt.tparchi.model.Contract;
import imt.tparchi.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }
}
