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
        Long result = reputationService.addReputation(reputation);
        response.setId(result);
        return response;
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "AddLikeReputation")
    @ResponsePayload
    public AddLikeReputationResponse addLikeReputation(@RequestPayload AddLikeReputation request) {
        AddLikeReputationResponse response = new AddLikeReputationResponse();
        Long result = reputationService.addLikeReputation(request.getId());
        response.setLikeReputation(result);
        return response;
    }

    @PayloadRoot(namespace = NAME_SPACE, localPart = "AddNotLikeReputation")
    @ResponsePayload
    public AddNotLikeReputationResponse addNotLikeReputation(@RequestPayload AddNotLikeReputation request) {
        AddNotLikeReputationResponse response = new AddNotLikeReputationResponse();
        Long result = reputationService.addNotLikeReputation(request.getId());
        response.setNotLikeReputation(result);
        return response;
    }

}
