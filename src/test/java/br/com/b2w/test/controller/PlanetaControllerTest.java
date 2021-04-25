package br.com.b2w.test.controller;

import br.com.b2w.test.dto.request.PlanetaRequest;
import br.com.b2w.test.dto.response.PlanetaResponse;
import br.com.b2w.test.entity.Planeta;
import br.com.b2w.test.service.PlanetaService;
import org.jeasy.random.EasyRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetaControllerTest {

    @InjectMocks
    private PlanetaController controller;

    @Mock
    private PlanetaService service;

    @Test
    public void listarPlanetasTest(){
        List<Planeta> planetas = new EasyRandom().objects(Planeta.class, 1).collect(Collectors.toList());
        when(this.service.listarPlanetas(null)).thenReturn(planetas);
        List<Planeta> response = this.controller.listarPlanetas(null);
        assertNotNull(response);
    }

    @Test
    public void listarPlanetaPorNomeTest(){
        Planeta planeta = new EasyRandom().nextObject(Planeta.class);
        when(this.service.listarPlanetaPorNome("Coruscant")).thenReturn(planeta);
        Planeta response = this.controller.listarPlanetaPorNome("Coruscant");
        assertNotNull(response);
    }

    @Test
    public void adicionarPlanetaTest(){
        PlanetaResponse response = new EasyRandom().nextObject(PlanetaResponse.class);
        PlanetaRequest request = new EasyRandom().nextObject(PlanetaRequest.class);
        when(this.service.adicionarPlaneta(request)).thenReturn(response);
        PlanetaResponse planetaResponse = this.controller.adicionarPlaneta(request);
        assertNotNull(planetaResponse);
    }

    @Test
    public void removerPlanetaTest(){
        String id = "1";
        when(this.service.removerPlaneta(id)).thenReturn("Planeta removido com sucesso");
        String response = this.controller.removerPlaneta(id);
        assertNotNull(response);
    }
}
