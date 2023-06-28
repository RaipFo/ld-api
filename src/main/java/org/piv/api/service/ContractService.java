package org.piv.api.service;

import lombok.RequiredArgsConstructor;
import org.piv.api.entity.Contract;
import org.piv.api.entity.User;
import org.piv.api.model.enums.Status;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final RepositoryService repositoryService;
    private final JwtService jwtService;

    private User getUserFromBd(String authHeader){
        String jwt = jwtService.getToken(authHeader);
        String userLogin = jwtService.extractLogin(jwt);
        return repositoryService.getUserRepository().findByLogin(userLogin).get();
    }
    public void submitContract(String authHeader) {
        User user = getUserFromBd(authHeader);
        Contract contract = Contract.builder()
                .eventAdmin(repositoryService.getEventAdminRepository().findById(user.getId()).get())
                .status(Status.NEOK)
                .build();
        repositoryService.getContractRepository().save(contract);
    }

    public void approveContract(Long id, String authHeader) {
        User user = getUserFromBd(authHeader);
        Optional<Contract> contract = repositoryService.getContractRepository().findById(id);
        if (contract.isPresent()) {
            Contract updateContract = contract.get();
            updateContract.setPrincipal(repositoryService.getPrincipalRepository().findById(user.getId()).get());
            updateContract.setStatus(Status.OK);
            repositoryService.getContractRepository().save(updateContract);
        }
    }
}
