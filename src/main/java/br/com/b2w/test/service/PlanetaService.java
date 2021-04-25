package br.com.b2w.test.service;

import br.com.b2w.test.dto.request.PlanetaRequest;
import br.com.b2w.test.dto.response.PlanetaResponse;
import br.com.b2w.test.entity.Planeta;
import br.com.b2w.test.repository.PlanetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class PlanetaService {

    private final PlanetaRepository repository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final MongoOperations mongoOperations;

    public List<Planeta> listarPlanetas(String id) {
        List<Planeta> planetas = new ArrayList<>();
        if (ObjectUtils.isEmpty(id)) {
            return this.repository.findAll();
        } else {
            Optional<Planeta> optionalPlaneta = this.repository.findById(id);
            optionalPlaneta.ifPresent(planetas::add);
            return planetas;
        }
    }

    public Planeta listarPlanetaPorNome(String nome){
        Query query = new Query();
        query.addCriteria(Criteria.where("nome").is(nome));
        return mongoOperations.findOne(query, Planeta.class);
    }

    public PlanetaResponse adicionarPlaneta(PlanetaRequest request){
        PlanetaResponse response = new PlanetaResponse();
        Planeta planeta = setaAtributosPlaneta(request);
        this.repository.save(planeta);
        response.setPlaneta(planeta);
        response.setMensagem("Planeta adicionado com sucesso!");
        return response;
    }

    private Planeta setaAtributosPlaneta(PlanetaRequest request) {
        Planeta planeta = new Planeta();
        planeta.setId(sequenceGeneratorService.generateSequence(Planeta.SEQUENCE_NAME));
        planeta.setNome(request.getNome());
        planeta.setClima(request.getClima());
        planeta.setTerreno(request.getTerreno());
        planeta.setAparicoes(new Random().nextInt(10));
        return planeta;
    }

    public String removerPlaneta(String id){
        this.repository.deleteById(id);
        return "Planeta deletado com sucesso";
    }
}
