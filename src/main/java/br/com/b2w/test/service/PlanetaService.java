package br.com.b2w.test.service;

import br.com.b2w.test.dto.request.PlanetaRequest;
import br.com.b2w.test.entity.Planeta;
import br.com.b2w.test.repository.PlanetaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanetaService {

    private final PlanetaRepository repository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public List<Planeta> listarPlanetas(String id, String nome){
        if (ObjectUtils.isEmpty(id)){
            this.repository.findAll();
        } else if (!ObjectUtils.isEmpty(nome) && ObjectUtils.isEmpty(id)){
            repository.findByName();
        } else {
            List<Planeta> planetas = new ArrayList<>();
            Optional<Planeta> planeta = this.repository.findById(id);

            planeta.ifPresent(planetas::add);
            return planetas;
        }
        return null;
    }

    public Planeta adicionarPlaneta(PlanetaRequest request){
        Planeta planeta = new Planeta();
        planeta.setId(sequenceGeneratorService.generateSequence(Planeta.SEQUENCE_NAME));
        planeta.setNome(request.getNome());
        planeta.setClima(request.getClima());
        planeta.setTerreno(request.getTerreno());
        //planeta.setAparicoes();
        return planeta;
    }

    public String removerPlaneta(String id){
        this.repository.deleteById(id);
        return "Planeta deletado com sucesso";
    }
}
