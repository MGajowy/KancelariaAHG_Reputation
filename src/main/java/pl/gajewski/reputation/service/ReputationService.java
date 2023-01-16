package pl.gajewski.reputation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gajewski.reputation.db.model.ReputationOB;
import pl.gajewski.reputation.db.model.repository.ReputationRepository;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.AddReputation;

import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.Reputation;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReputationService {

    @Autowired
    private final ReputationRepository reputationRepository;

    public Reputation getReputationById(long id) {
        Optional<ReputationOB> result = reputationRepository.findById(id);
        return mapObToReputation(result);
    }

    private Reputation mapObToReputation(Optional<ReputationOB> result) {
        Reputation reputation = new Reputation();
        reputation.setId(result.get().getId());
        reputation.setDescription(result.get().getDescription());
        reputation.setUser(result.get().getUserName());
        reputation.setLike(result.get().getLikeRep());
        reputation.setNotLike(result.get().getNotLikeRep());
        reputation.setVisible(result.get().getVisible());
        return reputation;
    }

    public List<Reputation> getAllReputation() {
        List<ReputationOB> result = reputationRepository.findAll();
        return result.stream()
                .map(reputationOB -> mapObToReputation(Optional.ofNullable(reputationOB)))
                .collect(Collectors.toList());
    }

    @Transactional
    public AddReputation addReputation(AddReputation reputation) {
        saveReputation(reputation);
        return reputation;
    }

    private void saveReputation(AddReputation reputation) {
        ReputationOB reputationOB = new ReputationOB();
        reputationOB.setUserName(reputation.getReputation().getUser());
        reputationOB.setDescription(reputation.getReputation().getDescription());
        reputationOB.setLikeRep(reputation.getReputation().getLike());
        reputationOB.setNotLikeRep(reputation.getReputation().getNotLike());
        reputationOB.setVisible(true);
        reputationRepository.save(reputationOB);
    }
}
