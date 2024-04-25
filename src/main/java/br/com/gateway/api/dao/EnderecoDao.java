package br.com.gateway.api.dao;

import br.com.gateway.api.database.DataBase;
import br.com.gateway.api.dto.EnderecoDTO;
import br.com.gateway.api.exception.GatewayException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {

    private static final String INSERT_ENDERECO_SQL = "INSERT INTO endereco_gateway(cep, uf, cidade, vizinhanca, rua, service) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ENDERECO_SQL = "SELECT * FROM endereco_gateway";
    private static final String SELECT_ENDERECO_BY_CEP_SQL = "SELECT COUNT(*) FROM endereco_gateway WHERE cep = ?";

    public void insertEndereco(EnderecoDTO enderecoDTO) {
        if(!existByCep(enderecoDTO.getCep())) {
            try (Connection connection = DataBase.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENDERECO_SQL)) {
                preparedStatement.setString(1, enderecoDTO.getCep());
                preparedStatement.setString(2, enderecoDTO.getUf());
                preparedStatement.setString(3, enderecoDTO.getCidade());
                preparedStatement.setString(4, enderecoDTO.getVizinhaca());
                preparedStatement.setString(5, enderecoDTO.getRua());
                preparedStatement.setString(6, enderecoDTO.getService());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new GatewayException("Erro ao inserir endereço na base", e);
            }
        }
    }

    public List<EnderecoDTO> findAll() {
        List<EnderecoDTO> enderecos = new ArrayList<>();

        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENDERECO_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                EnderecoDTO enderecoDTO = new EnderecoDTO();
                enderecoDTO.setCep(resultSet.getString("cep"));
                enderecoDTO.setEstado(resultSet.getString("uf"));
                enderecoDTO.setCidade(resultSet.getString("cidade"));
                enderecoDTO.setVizinhaca(resultSet.getString("vizinhanca"));
                enderecoDTO.setRua(resultSet.getString("rua"));
                enderecoDTO.setService(resultSet.getString("service"));
                enderecos.add(enderecoDTO);
            }
            return enderecos;

        } catch (SQLException e) {
            throw new GatewayException("Erro ao buscar endereços", e);
        }
    }

    private boolean existByCep(String cep) {
        try (Connection connection = DataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENDERECO_BY_CEP_SQL)) {

            preparedStatement.setString(1, cep);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            throw new GatewayException("Erro ao buscar endereço por CEP", e);
        }

        return false;
    }
}
