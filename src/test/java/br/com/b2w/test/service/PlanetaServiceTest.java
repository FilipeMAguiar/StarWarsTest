package br.com.b2w.test.service;

import br.com.b2w.test.dto.request.PlanetaRequest;
import br.com.b2w.test.dto.response.PlanetaResponse;
import br.com.b2w.test.entity.Planeta;
import br.com.b2w.test.repository.PlanetaRepository;
import org.jeasy.random.EasyRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetaServiceTest {

    @InjectMocks
    private PlanetaService service;

    @Mock
    private PlanetaRepository repository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @Mock
    private MongoOperations operations;

    @Test
    public void listarPlanetasTest(){
        List<Planeta> planetaList = new EasyRandom().objects(Planeta.class, 1).collect(Collectors.toList());
        when(this.repository.findAll()).thenReturn(planetaList);
        List<Planeta> response = this.service.listarPlanetas(null);
        assertNotNull(response);
    }

    @Test
    public void listarPlanetasByIdTest(){
        Planeta planeta = new EasyRandom().nextObject(Planeta.class);
        when(this.repository.findById("1")).thenReturn(java.util.Optional.ofNullable(planeta));
        List<Planeta> response = this.service.listarPlanetas("1");
        assertNotNull(response);
    }

    @Test
    public void listarPlanetesPorNomeTest(){
        Planeta planeta = new EasyRandom().nextObject(Planeta.class);
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(planeta.getNome()));
        when(this.operations.findOne(query, Planeta.class)).thenReturn(planeta);
        Planeta response = this.service.listarPlanetaPorNome(planeta.getNome());
        assertNotNull(response);
    }

    @Test
    public void adicionarPlanetaTest(){
        PlanetaRequest request = new EasyRandom().nextObject(PlanetaRequest.class);
        PlanetaResponse planetaResponse = new EasyRandom().nextObject(PlanetaResponse.class);
        when(this.service.adicionarPlaneta(request)).thenReturn(planetaResponse);
        when(this.sequenceGeneratorService.generateSequence(Planeta.SEQUENCE_NAME)).thenReturn("1");
        PlanetaResponse response = this.service.adicionarPlaneta(request);
        assertNotNull(response);
    }

    @Test
    public void removerPlanetaTest(){
        this.repository.deleteById("1");
        String response = this.service.removerPlaneta("1");
        assertNotNull(response);
    }
}
