package br.com.gateway.api.services;

import br.com.gateway.api.client.ApiHttpClientCep;
import br.com.gateway.api.dao.EnderecoDao;
import br.com.gateway.api.dto.EnderecoDTO;
import br.com.gateway.api.exception.GatewayException;

import java.util.List;
import java.util.Optional;


public class EnderecoService {



    public Optional<EnderecoDTO> buscarEnderecoViaCep(String cep) {

        if(!validaCep(cep)) {
            throw new GatewayException("CEP inválido - Verifique o tamanho do CEP e se contém apenas números");
        }

        ApiHttpClientCep apiHttpClientCep = new ApiHttpClientCep();
        Optional<EnderecoDTO> enderecoDTO = apiHttpClientCep.buscarEnderecoViaCep(cep);

        if(enderecoDTO.isEmpty()) {
            return Optional.empty();
        }

        EnderecoDao enderecoDao = new EnderecoDao();

        enderecoDao.insertEndereco(enderecoDTO.get());

        return enderecoDTO;

    }

    public List<EnderecoDTO> buscarEnderecos() {
        EnderecoDao enderecoDao = new EnderecoDao();
        return enderecoDao.findAll();
    }

    public static boolean validaCep(String cep) {

        return cep != null && cep.matches("\\d{8}");
    }
}
