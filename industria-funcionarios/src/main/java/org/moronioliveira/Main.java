package org.moronioliveira;

import org.moronioliveira.business.FuncionarioService;
import org.moronioliveira.infrastructure.entity.Funcionario;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class Main {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat FORMATO_MOEDA = new DecimalFormat("#,##0.00");

    private static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }

    private static String formatarMoeda(BigDecimal valor) {
        return FORMATO_MOEDA.format(valor);
    }

    private static void imprimirFuncionario(Funcionario f) {
        System.out.println("Nome: " + f.getNome() +
                " | Data Nasc: " + formatarData(f.getDataNascimento()) +
                " | Salário: R$ " + formatarMoeda(f.getSalario()) +
                " | Função: " + f.getFuncao());
    }

    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();
        List<Funcionario> funcionarios = service.gerarLista();

        //Vai imprimir a lista toda
        for (Funcionario f : funcionarios) {
            imprimirFuncionario(f);
        }

    //Vai remover algum funcionario escolhido (por enquanto estatico)
        service.removerPorNome(funcionarios, "João");

    //Vai atribuir 10% em cima do salario dos funcionarios
        service.atribuirSalario(funcionarios, new BigDecimal("1.10"));

        Map<String, List<Funcionario>> agrupados = service.agruparLista(funcionarios);
        //Agrupa os funcionarios pela função exercida na empresa
        agrupados.forEach((funcao, lista) -> {
            System.out.println("\n ___ Função: " + funcao + " ___");
            for (Funcionario f : lista) {
                imprimirFuncionario(f);
            }
        });

        List<Funcionario> aniversariantes = service.filtrarNascimento(funcionarios);
        //Mostra os aniversariantes daqueles meses escolhidos (Por enquanto estático também)
        System.out.println("\n___ Aniversariantes (Outubro e Dezembro) ___");
        for (Funcionario f : aniversariantes) {
            imprimirFuncionario(f);
        }

        Funcionario maisVelho = service.encontrarMaisVelho(funcionarios);
        //Mostra o funcionario mais velho prestando serviços
        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("\n___ Mais Velho ___");
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade + " anos");
        }

        service.ordenarPorNome(funcionarios);
        //Ordena a lista por ordem alfabetica
        System.out.println("\n___ Lista Ordenada por Nome ___");
        for (Funcionario f : funcionarios) {
            imprimirFuncionario(f);
        }

        BigDecimal totalSalarios = service.somarSalarios(funcionarios);
        //Mostra o total de gastos com os salarios dos funcionarios
        System.out.println("\nTotal dos Salários: R$ " + formatarMoeda(totalSalarios));


        Map<String, BigDecimal> qtdSalarios = service.quantidadeSalariosMinimos(funcionarios);
        //Mostra quantos salarios minimos cada funcionario recebe
        System.out.println("\n___ Quantidade de Salários Mínimos ___");
        qtdSalarios.forEach((nome, quantidade) ->
                System.out.println(nome + " ganha " + quantidade + " salários mínimos.")
        );
    }
}
