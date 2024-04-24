package br.com.gateway.api.dto;

import static java.lang.System.*;

public class EnderecoDTO {

    private String cep;
    private String uf;
    private String cidade;
    private String vizinhaca;
    private String rua;
    private String service;

    public EnderecoDTO() {
    }

    public EnderecoDTO(String cep, String uf, String cidade, String vizinhaca, String rua, String service) {
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
        this.vizinhaca = vizinhaca;
        this.rua = rua;
        this.service = service;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setEstado(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getVizinhaca() {
        return vizinhaca;
    }

    public void setVizinhaca(String vizinhaca) {
        this.vizinhaca = vizinhaca;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    /*
    * metodo para logar no termianl o endereco apenas com os atributos cep, estado, cidade, vizinhaca e rua
    *
    * */
    public void exibirEndereco(){
        out.println("Informações do Endereço:");
        out.println("------------------------");
        out.println("CEP: " + cep);
        out.println("RUA: " + rua);
        out.println("VIZINHANÇA: " + vizinhaca);
        out.println("CIDADE: " + cidade);
        out.println("UF: " + uf);
        out.println("------------------------");

    }

    @Override
    public String toString() {
        return "Endereco {" +
                "cep='" + cep + '\'' +
                ", uf='" + uf + '\'' +
                ", cidade='" + cidade + '\'' +
                ", vizinhaca='" + vizinhaca + '\'' +
                ", rua='" + rua + '\'' +
                ", service='" + service + '\'' +
                '}';
    }
}
