package br.com.b2w.test.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PlanetaRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String clima;

    @NotEmpty
    private String terreno;
}
