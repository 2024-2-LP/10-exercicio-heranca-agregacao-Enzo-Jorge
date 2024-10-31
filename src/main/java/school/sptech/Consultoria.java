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
        if (desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack()) {
            desenvolvedores.add(desenvolvedor);
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

        for (Desenvolvedor devDaVez : desenvolvedores) {
            if (devDaVez instanceof DesenvolvedorMobile) {
                if (((DesenvolvedorMobile) devDaVez).getLinguagem().equalsIgnoreCase(tecnologia)) {
                    listaRetorno.add(devDaVez);
                }
                if (((DesenvolvedorMobile) devDaVez).getPlataforma().equalsIgnoreCase(tecnologia)) {
                    listaRetorno.add(devDaVez);
                }
            }
            if (devDaVez instanceof DesenvolvedorWeb) {
               if (((DesenvolvedorWeb) devDaVez).getFrontend().equalsIgnoreCase(tecnologia)) {
                   listaRetorno.add(devDaVez);
               }
               if (((DesenvolvedorWeb) devDaVez).getBackend().equalsIgnoreCase(tecnologia)) {
                   listaRetorno.add(devDaVez);
               }
               if (((DesenvolvedorWeb) devDaVez).getSgbd().equalsIgnoreCase(tecnologia)) {
                   listaRetorno.add(devDaVez);
               }
            }
        }

        return listaRetorno;

    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        Double salarioTotal = 0.0;

        for (int i = 0; i < buscarPorTecnologia(tecnologia).size(); i++) {
            salarioTotal += buscarPorTecnologia(tecnologia).get(i).calcularSalario();
        }

        return salarioTotal;
    }

}
