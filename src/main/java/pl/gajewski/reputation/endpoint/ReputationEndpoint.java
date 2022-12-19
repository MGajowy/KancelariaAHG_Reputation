package pl.gajewski.reputation.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.gajewski.reputation.service.ReputationService;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.*;

import java.util.List;

@Endpoint
public class ReputationEndpoint {

    public static final String NAME_SPACE = "http://gajewski.pl/reputation";
    private final ReputationService reputationService;

    @Autowired
    public ReputationEndpoint(ReputationService reputationService) {
        this.reputationService = reputationService;
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "getReputation")
    @ResponsePayload
    public GetResponse getReputationById(@RequestPayload GetReputation reputation) {
        Reputation result = reputationService.getReputationById(reputation.getId());
        GetResponse response = new GetResponse();
        response.setReputation(result);
        return response;
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "getAllReputation")
    @ResponsePayload
    public GetAllReputationResponse getAllReputation() {
        GetAllReputationResponse response = new GetAllReputationResponse();
        List<Reputation> allReputation = reputationService.getAllReputation();
        response.getReputation().addAll(allReputation);
        return response;
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "AddReputation")
    @ResponsePayload
    public AddReputationResponse addReputation(@RequestPayload AddReputation reputation) {
        AddReputationResponse response = new AddReputationResponse();
        AddReputation addReputation = reputationService.addReputation(reputation);
        response.setReputation(reputation.getReputation());
        return response;
    }

}
