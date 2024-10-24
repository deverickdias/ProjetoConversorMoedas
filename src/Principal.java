import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        // Substitua com sua chave API da ExchangeRate
        String apiKey = "1e31e5527c3b7871bf845676";
        ApiCliente apiCliente = new ApiCliente(apiKey);
        ConversorMoeda conversor = new ConversorMoeda();

        Scanner leitura = new Scanner(System.in);

        // Menu para escolha das moedas
        System.out.println("Escolha a moeda de origem: ");
        String moedaOrigem = escolherMoeda(leitura);

        System.out.println("Escolha a moeda de destino: ");
        String moedaDestino = escolherMoeda(leitura);

        // Leitura do valor a ser convertido
        System.out.print("Digite o valor que deseja converter: ");
        double valor = leitura.nextDouble();

        try {
            // Obter a taxa de câmbio da moeda de origem para a moeda de destino
            double taxaDeCambio = apiCliente.obterTaxaDeCambio(moedaOrigem, moedaDestino);

            // Realizar a conversão
            double valorConvertido = conversor.converter(valor, taxaDeCambio);

            // Exibir o resultado
            System.out.printf("Valor convertido (%s -> %s): %.2f%n", moedaOrigem, moedaDestino, valorConvertido);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao tentar obter a taxa de câmbio: " + e.getMessage());
        }

        leitura.close();
    }

    // Método para exibir as opções de moedas e retornar o código da moeda escolhida
    public static String escolherMoeda(Scanner leitura) {
        System.out.println("1 - Dólar (USD)");
        System.out.println("2 - Real Brasileiro (BRL)");
        System.out.println("3 - Peso Argentino (ARS)");
        System.out.println("4 - Peso Colombiano (COP)");

        int opcao = leitura.nextInt();
        leitura.nextLine(); // Consumir nova linha

        switch (opcao) {
            case 1:
                return "USD";
            case 2:
                return "BRL";
            case 3:
                return "ARS";
            case 4:
                return "COP";
            default:
                System.out.println("Opção inválida, usando Dólar (USD) por padrão.");
                return "USD";
        }
    }
}
