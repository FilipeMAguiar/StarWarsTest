package br.com.b2w.test.repository;

import br.com.b2w.test.entity.Planeta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {
    List<Planeta> findAll();

    Planeta findByName();
}
