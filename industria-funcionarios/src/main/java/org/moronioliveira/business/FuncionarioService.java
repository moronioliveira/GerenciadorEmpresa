package org.moronioliveira.business;

import org.moronioliveira.infrastructure.entity.Funcionario;
import org.moronioliveira.infrastructure.entity.Pessoa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {

    public List<Funcionario> gerarLista() {
        List<Funcionario> lista = new ArrayList<>();

        lista.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        lista.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        lista.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        lista.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        lista.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        lista.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        lista.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        lista.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        lista.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        lista.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        return lista;
    }

    public void removerPorNome(List<Funcionario> funcionarios, String nome) {
        if (funcionarios != null && !funcionarios.isEmpty()) {
            boolean removedor = funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase(nome));
        }
    }

    //Vai atribuir 10% em cima do salario dos funcionarios
    public void atribuirSalario(List<Funcionario> funcionarios, BigDecimal salario) {
        if (funcionarios != null && !funcionarios.isEmpty()) {
            for (Funcionario f : funcionarios) {
                BigDecimal novoSalario = f.getSalario()
                        .multiply(salario);
                f.setSalario(novoSalario);
            }
        }
    }


    public Map<String, List<Funcionario>> agruparLista(List<Funcionario> funcionarios) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, List<Funcionario>> agrupar = funcionarios
                .stream().collect(Collectors.groupingBy(
                        Funcionario::getFuncao));
        return agrupar;
    }

    public List<Funcionario> filtrarNascimento(List<Funcionario> funcionarios) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return Collections.emptyList();
        }
        List<Funcionario> filtro = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 ||
                        f.getDataNascimento().getMonthValue() == 12)
                .toList();

        return filtro;
    }

    public Funcionario encontrarMaisVelho(List<Funcionario> funcionarios) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return null;
        }

        Funcionario maisVelho = funcionarios.get(0);

        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().isBefore(maisVelho.getDataNascimento())) {
                maisVelho = f;
            }
        }

        return maisVelho;
    }

    public void ordenarPorNome(List<Funcionario> funcionarios) {
        if (funcionarios != null && !funcionarios.isEmpty()) {
            funcionarios.sort(Comparator.comparing(Pessoa::getNome));
        }
    }

    public BigDecimal somarSalarios(List<Funcionario> funcionarios) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }

    public Map<String, BigDecimal> quantidadeSalariosMinimos(List<Funcionario> funcionarios) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            return Collections.emptyMap();
        }

        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        Map<String, BigDecimal> resultado = new HashMap<>();

        for (Funcionario f : funcionarios) {
            BigDecimal qtdSalarios = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            resultado.put(f.getNome(), qtdSalarios);
        }

        return resultado;
    }
}
