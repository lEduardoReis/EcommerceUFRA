import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ecommerce ecommerce = new Ecommerce();
        Transportadora transportadora = new Transportadora("Transportadora X", 10.0, 5.0); // preço por volume: 10, preço por peso: 5

        // Cadastro de cliente
        System.out.println("=== Cadastro de Cliente ===");
        System.out.print("Digite seu nome: ");
        String nomeCliente = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String emailCliente = scanner.nextLine();

        Cliente cliente = new Cliente(nomeCliente, emailCliente);
        ecommerce.cadastrar(cliente);

        // Cadastro de endereço do cliente
        System.out.println("\n=== Cadastro de Endereço ===");
        System.out.print("Digite o nome do endereço (ex: Casa, Trabalho): ");
        String nomeEndereco = scanner.nextLine();
        System.out.print("Digite o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Digite o número: ");
        String numero = scanner.nextLine();
        System.out.print("Digite o complemento (opcional): ");
        String complemento = scanner.nextLine();

        Endereco endereco = new Endereco(nomeEndereco, logradouro, numero, complemento);

        // Lista para armazenar os itens do carrinho
        List<Item> carrinho = new ArrayList<>();

        // Opções de adicionar e remover produtos
        boolean continuarComprando = true;
        while (continuarComprando) {
            System.out.println("\n=== Opções de Produto ===");
            System.out.println("1. Adicionar Produto ao Carrinho");
            System.out.println("2. Remover Produto do Carrinho");
            System.out.println("3. Finalizar Compra");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do scanner

            switch (opcao) {
                case 1:
                    // Adicionar Produto ao Carrinho
                    System.out.println("\n=== Adicionar Produto ao Carrinho ===");
                    System.out.print("Digite o nome do produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Digite o preço do produto: ");
                    double precoProduto = parseDouble(scanner.nextLine());  // Usando parseDouble para garantir que o separador decimal seja aceito
                    System.out.print("Digite o volume do produto: ");
                    double volumeProduto = parseDouble(scanner.nextLine());  // Usando parseDouble para garantir que o separador decimal seja aceito
                    System.out.print("Digite o peso do produto: ");
                    double pesoProduto = parseDouble(scanner.nextLine());  // Usando parseDouble para garantir que o separador decimal seja aceito

                    Produto produto = new Produto(nomeProduto, precoProduto, volumeProduto, pesoProduto);
                    System.out.print("Quantos deste produto você deseja comprar? ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine(); // Limpar buffer do scanner

                    Item item = new Item(quantidade, produto);
                    carrinho.add(item);
                    System.out.println("Produto adicionado ao carrinho!");
                    break;

                case 2:
                    // Remover Produto do Carrinho
                    System.out.println("\n=== Remover Produto do Carrinho ===");
                    if (carrinho.isEmpty()) {
                        System.out.println("Carrinho vazio! Nenhum produto para remover.");
                    } else {
                        // Exibir itens no carrinho
                        System.out.println("Produtos no Carrinho:");
                        for (int i = 0; i < carrinho.size(); i++) {
                            Item itemCarrinho = carrinho.get(i);
                            System.out.println((i + 1) + ". " + itemCarrinho.getProduto().getNome() + " - Quantidade: " + itemCarrinho.getQuantidade());
                        }

                        System.out.print("Escolha o número do produto que deseja remover: ");
                        int indiceRemover = scanner.nextInt();
                        if (indiceRemover > 0 && indiceRemover <= carrinho.size()) {
                            carrinho.remove(indiceRemover - 1);
                            System.out.println("Produto removido do carrinho.");
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    }
                    break;

                case 3:
                    // Finalizar Compra
                    System.out.println("\n=== Resumo do Pedido ===");
                    if (carrinho.isEmpty()) {
                        System.out.println("Carrinho vazio! Adicione produtos antes de finalizar a compra.");
                    } else {
                        double subtotal = 0.0;
                        for (Item itemCarrinho : carrinho) {
                            System.out.println(itemCarrinho.getProduto().getNome() + " - Quantidade: " + itemCarrinho.getQuantidade() + " - Preço: R$ " + itemCarrinho.getProduto().getPreco());
                            subtotal += itemCarrinho.getProduto().getPreco() * itemCarrinho.getQuantidade();
                        }

                        // Calcular e exibir frete
                        double freteProduto = transportadora.calcularFrete(carrinho);
                        String tipoFrete = transportadora.determinarTipoFrete(carrinho.get(0)); // Assume que todos os produtos têm o mesmo tipo de frete
                        double total = subtotal + freteProduto;

                        System.out.println("Subtotal: R$ " + subtotal);
                        System.out.println("Tipo de Frete: " + tipoFrete);
                        System.out.println("Frete: R$ " + freteProduto);
                        System.out.println("Total: R$ " + total);

                        // Realizar pagamento
                        double valorPagamento;
                        boolean pagamentoRealizado = false;

                        // Loop para validar o pagamento
                        while (!pagamentoRealizado) {
                            System.out.print("\nDigite o valor para pagamento: ");
                            valorPagamento = scanner.nextDouble();

                            // Verifica se o valor de pagamento é suficiente
                            if (valorPagamento >= total) {
                                pagamentoRealizado = ecommerce.pagar(cliente, valorPagamento);
                                if (pagamentoRealizado) {
                                    ecommerce.getListaPedidos().add(new Pedido("2024-11-21 14:30", cliente, endereco, carrinho));
                                    System.out.println("Compra realizada com sucesso!");
                                } else {
                                    System.out.println("Pagamento não realizado. Tente novamente.");
                                }
                            } else {
                                System.out.println("Valor insuficiente. O total é de R$ " + total + ". Tente novamente.");
                            }
                        }
                    }
                    continuarComprando = false; // Finaliza a compra após a finalização
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    // Método auxiliar para parse de double que considera a entrada com vírgula ou ponto
    public static double parseDouble(String input) {
        // Substitui a vírgula por ponto
        input = input.replace(',', '.');
        return Double.parseDouble(input);
    }
}
