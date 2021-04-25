package br.com.b2w.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "planeta")
public class Planeta {

    @Transient
    public static final String SEQUENCE_NAME = "sequencia_planeta";

    @Id
    private String id;

    private String nome;

    private String clima;

    private String terreno;

    private int aparicoes;
}
