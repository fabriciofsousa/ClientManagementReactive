package br.com.ada.reactivejavasalarymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "salaries")
public class Salary {

    private String id = new ObjectId().toString();
    @Indexed(unique = true, background = true)
    private String code;
    private Integer idade;
    private String genero;
    private String escolaridade;
    private String cargo;
    private Double tempoDeExperienciaEmAnos;
    private Double salario;

    public Salary(String code, Integer idade, String genero, String escolaridade, String cargo, Double tempoDeExperienciaEmAnos, Double salario) {
        this.code = code;
        this.idade = idade;
        this.genero = genero;
        this.escolaridade = escolaridade;
        this.cargo = cargo;
        this.tempoDeExperienciaEmAnos = tempoDeExperienciaEmAnos;
        this.salario = salario;
    }
}
