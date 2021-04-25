package br.com.b2w.test.dto.response;

import br.com.b2w.test.entity.Planeta;
import lombok.Data;

@Data
public class PlanetaResponse {
    private Planeta planeta;
    private String mensagem;
}
