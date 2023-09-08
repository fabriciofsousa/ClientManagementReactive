package br.com.ada.reactivejavasalarymanagement.converter;

import br.com.ada.reactivejavasalarymanagement.dto.SalaryDTO;
import br.com.ada.reactivejavasalarymanagement.model.Salary;
import org.springframework.stereotype.Component;

@Component
public class SalaryConverter {

    public SalaryDTO toSalaryDTO(Salary salary){
        return new SalaryDTO(salary.getCode(),
                salary.getIdade(),
                salary.getGenero(),
                salary.getEscolaridade(),
                salary.getCargo(),
                salary.getTempoDeExperienciaEmAnos(),
                salary.getSalario());
    }


    public Salary toSalary(SalaryDTO salary){
        return new Salary(salary.getCode(),
                salary.getIdade(),
                salary.getGenero(),
                salary.getEscolaridade(),
                salary.getCargo(),
                salary.getTempoDeExperienciaEmAnos(),
                salary.getSalario());
    }
}
