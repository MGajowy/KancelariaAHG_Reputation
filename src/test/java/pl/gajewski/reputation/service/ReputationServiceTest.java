package pl.gajewski.reputation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.gajewski.reputation.db.model.ReputationOB;
import pl.gajewski.reputation.db.model.repository.ReputationRepository;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.AddNotLikeReputationResponse;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.AddReputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.Reputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.ReputationAdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReputationServiceTest {

    @Mock
    ReputationRepository reputationRepository;

    @InjectMocks
    ReputationService reputationService;

    @Test
    void sholudGetReputationById() {
        // given
        // when
        when(reputationRepository.findById(any())).thenReturn(createReputation());
        Reputation actual = reputationService.getReputationById(1L);

        // then
        assertThat(actual.getLike()).isOne();
        assertThat(actual).isNotNull();
    }

    @Test
    void shouldGetAllReputation() {
        // given
        List<ReputationOB> list = new ArrayList<>();
        list.add(createReputation().get());
        // when
        when(reputationRepository.findAll()).thenReturn(list);
        List<Reputation> actual = reputationService.getAllReputation();
        // then
        assertThat(actual.size()).isOne();
        assertThat(actual).isNotNull();
        assertThat(actual.get(0).getLike()).isOne();
    }

    @Test
    void shouldSaveReputation() {
        // given
        AddReputation reputation = new AddReputation();
        ReputationAdd reputationAdd = new ReputationAdd();
        reputationAdd.setUser("Adam");
        reputationAdd.setDescription("Test");
        reputation.setReputationAdd(reputationAdd);
        // when
        when(reputationRepository.save(any())).thenReturn(obReputation());
        Long actual = reputationService.addReputation(reputation);
        // then
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(1);
    }

    @Test
    void shouldAddLikeReputation() {
        // given
        // when
        when(reputationRepository.findById(any())).thenReturn(Optional.of(obReputation()));
        Long actual = reputationService.addLikeReputation(1L);
        // then
        assertThat(actual).isEqualTo(2L);
    }

    @Test
    void shouldAddNotLikeReputation() {
        // given
        // when
        when(reputationRepository.findById(any())).thenReturn(Optional.of(obReputation()));
        AddNotLikeReputationResponse actual = reputationService.addNotLikeReputation(1L);
        // then
        assertThat(actual.getNotLikeReputation()).isEqualTo(3L);
    }

    private ReputationOB obReputation() {
        ReputationOB reputation = new ReputationOB();
        reputation.setId(1L);
        reputation.setVisible(true);
        reputation.setDescription("Test");
        reputation.setLikeRep(1L);
        reputation.setNotLikeRep(2L);
        reputation.setUserName("Adam");
        return reputation;
    }

    private Optional<ReputationOB> createReputation() {
        ReputationOB reputation = new ReputationOB();
        reputation.setId(1);
        reputation.setNotLikeRep(0L);
        reputation.setLikeRep(1L);
        reputation.setUserName("Adam");
        reputation.setDescription("test test");
        reputation.setVisible(true);
        return Optional.of(reputation);
    }
}