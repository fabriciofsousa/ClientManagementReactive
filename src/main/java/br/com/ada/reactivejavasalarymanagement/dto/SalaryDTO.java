package br.com.ada.reactivejavasalarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDTO {
    private String code;
    private Integer idade;
    private String genero;
    private String escolaridade;
    private String cargo;
    private Double tempoDeExperienciaEmAnos;
    private Double salario;
}
