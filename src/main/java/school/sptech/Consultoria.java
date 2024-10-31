package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Consultoria {

    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria() {
    }

    public Consultoria(String nome, Integer vagas, List<Desenvolvedor> desenvolvedores) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public void contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedor != null && desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack()) {
            contratar(desenvolvedor);
        }
    }

    public Double getTotalSalarios() {
        Double totalSalario = 0.0;

        for (Desenvolvedor devDaVez : desenvolvedores) {
            totalSalario += devDaVez.calcularSalario();
        }

        return totalSalario;
    }

    public Integer qtdDesenvolvedoresMobile() {
        return desenvolvedores.stream()
                .filter(devDaVez -> devDaVez instanceof DesenvolvedorMobile)
                .toList()
                .size();
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        return desenvolvedores.stream()
                .filter(devDaVez -> devDaVez.calcularSalario() >= salario)
                .toList();
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores != null && !desenvolvedores.isEmpty()) {
        return this.desenvolvedores.stream()
                .min(Comparator.comparingDouble(Desenvolvedor::calcularSalario))
                .get();
        } else {
            return null;
        }
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> listaRetorno = new ArrayList<>();

        return this.desenvolvedores.stream()
                .filter(devDaVez -> devDaVez instanceof DesenvolvedorMobile &&
                        (((DesenvolvedorMobile) devDaVez).getPlataforma().equalsIgnoreCase(tecnologia) ||
                                ((DesenvolvedorMobile) devDaVez).getLinguagem().equalsIgnoreCase(tecnologia)) ||
                        devDaVez instanceof DesenvolvedorWeb &&
                        (((DesenvolvedorWeb) devDaVez).getSgbd().equalsIgnoreCase(tecnologia) ||
                                ((DesenvolvedorWeb) devDaVez).getBackend().equalsIgnoreCase(tecnologia) ||
                                ((DesenvolvedorWeb) devDaVez).getFrontend().equalsIgnoreCase(tecnologia)))
                .toList();

    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        Double salarioTotal = 0.0;

        for (int i = 0; i < buscarPorTecnologia(tecnologia).size(); i++) {
            salarioTotal += buscarPorTecnologia(tecnologia).get(i).calcularSalario();
        }

        return salarioTotal;
    }

}
