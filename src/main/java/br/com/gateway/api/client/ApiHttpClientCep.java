package br.com.gateway.api.client;

import br.com.gateway.api.dto.EnderecoDTO;
import br.com.gateway.api.exception.GatewayException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.logging.Logger;

public class ApiHttpClientCep {

    private static final String BASE_URL = "https://brasilapi.com.br/api/cep/v1/";
    private static final Logger LOG = Logger.getLogger(ApiHttpClientCep.class.getName());

    public Optional<EnderecoDTO> buscarEnderecoViaCep(String cep) {

        URI uri = URI.create(BASE_URL + cep);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        try {

            LOG.info("Buscando endereço via CEP: " + cep);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            LOG.info("Codigo de resposta: " + response.statusCode());

            if(response.statusCode() != 200) {
               return Optional.empty();
            }

            return Optional.of(parseJsonToDTO(response.body()));

        } catch (IOException | InterruptedException e) {
            throw new GatewayException("Erro ao buscar endereço via CEP: ", e);
        }




    }

    private EnderecoDTO parseJsonToDTO(String json) {
        JSONObject jsonObject = new JSONObject(json);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(jsonObject.getString("cep"));
        enderecoDTO.setUf(jsonObject.getString("state"));
        enderecoDTO.setCidade(jsonObject.getString("city"));
        enderecoDTO.setVizinhaca(jsonObject.getString("neighborhood"));
        enderecoDTO.setRua(jsonObject.getString("street"));
        enderecoDTO.setService(jsonObject.getString("service"));

        return enderecoDTO;

    }

}
