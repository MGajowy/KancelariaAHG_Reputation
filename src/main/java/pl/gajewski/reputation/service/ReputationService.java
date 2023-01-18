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
    public Long addReputation(AddReputation reputation) {
        return saveReputation(reputation);
    }

    private Long saveReputation(AddReputation reputation) {
        ReputationOB reputationOB = new ReputationOB();
        reputationOB.setUserName(reputation.getReputationAdd().getUser());
        reputationOB.setDescription(reputation.getReputationAdd().getDescription());
        reputationOB.setLikeRep(0L);
        reputationOB.setNotLikeRep(0L);
        reputationOB.setVisible(true);
        return reputationRepository.save(reputationOB).getId();
    }

    @Transactional
    public Long addLikeReputation(Long id) {
        Optional<ReputationOB> result = reputationRepository.findById(id);
        Long likeReputation = result.get().getLikeRep();
        likeReputation++;
        result.get().setLikeRep(likeReputation);
        reputationRepository.save(result.get());
        return likeReputation;
    }

    @Transactional
    public Long addNotLikeReputation(Long id) {
        Optional<ReputationOB> result = reputationRepository.findById(id);
        Long notLikeReputation = result.get().getNotLikeRep();
        notLikeReputation++;
        result.get().setNotLikeRep(notLikeReputation);
        reputationRepository.save(result.get());
        return notLikeReputation;
    }
}
