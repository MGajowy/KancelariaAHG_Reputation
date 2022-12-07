package pl.gajewski.reputation.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.gajewski.reputation.service.ReputationService;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.*;

import java.util.List;

@Endpoint
public class ReputationEndpoint {
    private final ReputationService reputationService;

    public ReputationEndpoint(ReputationService reputationService) {
        this.reputationService = reputationService;
    }

    @PayloadRoot(namespace = "http://gajewski.pl/reputation", localPart = "getReputation")
    @ResponsePayload
    public GetResponse getReputationById(@RequestPayload GetReputation reputation) {
        Reputation result = reputationService.getReputationById(reputation.getId());
        GetResponse response = new GetResponse();
        response.setReputation(result);
        return response;
    }

    @PayloadRoot(namespace = "http://gajewski.pl/reputation", localPart = "getAllReputation")
    @ResponsePayload
    public GetAllReputationResponse getAllReputation() {
        GetAllReputationResponse response = new GetAllReputationResponse();
        List<Reputation> allReputation = reputationService.getAllReputation();
        response.getReputation().addAll(allReputation);
        return response;
    }
}
