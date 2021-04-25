package br.com.b2w.test.controller;

import br.com.b2w.test.dto.request.PlanetaRequest;
import br.com.b2w.test.dto.response.PlanetaResponse;
import br.com.b2w.test.entity.Planeta;
import br.com.b2w.test.service.PlanetaService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/planeta")
@AllArgsConstructor
public class PlanetaController {

    private final PlanetaService service;

    @GetMapping
    public List<Planeta> listarPlanetas(@RequestParam(required = false) String id){ return this.service.listarPlanetas(id);}

    @GetMapping("/nome")
    public Planeta listarPlanetaPorNome(@RequestParam String nome){
        return this.service.listarPlanetaPorNome(nome);
    }

    @PostMapping
    public PlanetaResponse adicionarPlaneta(@Validated @RequestBody PlanetaRequest request){ return this.service.adicionarPlaneta(request);}

    @DeleteMapping("/{id}")
    public String removerPlaneta(@PathVariable String id) { return this.service.removerPlaneta(id);}
}
