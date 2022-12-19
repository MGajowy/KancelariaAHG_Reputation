package pl.gajewski.reputation.service;

import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.AddReputation;

import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.Reputation;


import java.util.ArrayList;
import java.util.List;

@Service
public class ReputationService {

    private List<Reputation> reputationList;

    public ReputationService() {
        reputationList = new ArrayList<Reputation>();
        Reputation reputation1 = new Reputation();
        reputation1.setId(1);
        reputation1.setUser("Adam");
        reputation1.setDescription("Dobra aplikacja");
        reputation1.setLike(1);
        reputation1.setNotLike(3);

        Reputation reputation2 = new Reputation();
        reputation2.setId(2);
        reputation2.setUser("Ewa");
        reputation2.setDescription("Fajna strona!");
        reputation2.setLike(35);
        reputation2.setNotLike(3);

        Reputation reputation3 = new Reputation();
        reputation3.setId(3);
        reputation3.setUser("Michal");
        reputation3.setDescription("Za mało informacji");
        reputation3.setLike(1);
        reputation3.setNotLike(15);

        reputationList.add(reputation1);
        reputationList.add(reputation2);
        reputationList.add(reputation3);
    }

    public Reputation getReputationById(long id) {
        return reputationList.stream()
                .filter(rep -> rep.getId() == id)
                .findFirst()
                .get();
    }

    public List<Reputation> getAllReputation() {
        return reputationList;
    }

    public AddReputation addReputation(AddReputation reputation) {
        reputationList.add(reputation.getReputation());
        return reputation;
    }
}
