package br.com.gateway.api;

import br.com.gateway.api.config.AppConfig;
import br.com.gateway.api.dto.EnderecoDTO;
import br.com.gateway.api.services.EnderecoService;
import br.com.gateway.api.utils.ScannerUtils;

import java.util.Optional;
import java.util.logging.Logger;

import static java.lang.System.*;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args ) {


        EnderecoService enderecoService = new EnderecoService();
        String cep;
        int navegacao;

        logger.info("INICIANDO GATEWAY DE AUTOMAÇÃO PARA CONSULTA DE CEP!");

        do {

            out.println("ESCOLHA AS OPÇÕES ABAIXO: ");
            out.println("1 - CONSULTAR CEP");
            out.println("2 - BUSCAR TODOS OS ENDERECOS NA BASE DE DADOS");
            out.println("3 - SAIR");

            navegacao = ScannerUtils.lerDadoInteiro();

            switch (navegacao) {
                case 1:
                    out.println("DIGITE O CEP PARA CONSULTA: ");
                    cep = ScannerUtils.lerDadoTexto();

                    Optional<EnderecoDTO> endereco;

                    try{
                       endereco  = enderecoService.buscarEnderecoViaCep(cep);
                    }catch (Exception e) {
                        logger.warning("ERRO AO BUSCAR ENDEREÇO VIA CEP: " + e.getCause().getMessage());
                        break;
                    }

                    if(endereco.isEmpty()){
                        out.println("CEP NÃO ENCONTRADO!");
                    }else{
                        endereco.get().exibirEndereco();
                    }

                    break;
                case 2:
                    out.println("BUSCANDO TODOS OS ENDERECOS NA BASE DE DADOS...");
                    try {
                        enderecoService.buscarEnderecos();
                    }catch (Exception e) {
                        logger.warning("ERRO AO BUSCAR ENDEREÇOS: " + e.getCause().getMessage());
                    }
                    break;
                case 3:
                    out.println("SAINDO DO GATEWAY DE AUTOMAÇÃO PARA CONSULTA DE CEP!");
                    break;
                default:
                    out.println("OPÇÃO INVÁLIDA!");
                    break;
            }


        }while (navegacao != 3);

        ScannerUtils.closeScanner();



    }
}
