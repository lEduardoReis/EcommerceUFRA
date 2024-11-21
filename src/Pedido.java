import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String dataHora;
    private double subtotal;
    private double frete;
    private double total;
    private Cliente cliente;
    private Endereco endereco;
    private Transportadora transportadora;
    private List<Item> listaItens;

    public Pedido(String dataHora, Cliente cliente, Endereco endereco, List<Item> carrinho) {
        this.dataHora = dataHora;
        this.subtotal = 0.0;
        this.frete = 0.0;
        this.total = 0.0;
        this.cliente = cliente;
        this.endereco = endereco;
        this.transportadora = null;
        this.listaItens = new ArrayList<>();
    }

    // Método para calcular o total do pedido
    public void calcularTotal() {
        this.total = this.subtotal + this.frete;
    }

    // Método para calcular o frete baseado na transportadora
    public void calcularFrete() {
        if (this.transportadora != null) {
            double volumeTotal = 0.0;
            double pesoTotal = 0.0;

            for (Item item : listaItens) {
                Produto produto = item.getProduto();
                volumeTotal += produto.getVolume() * item.getQuantidade();
                pesoTotal += produto.getPeso() * item.getQuantidade();
            }

            this.frete = (volumeTotal * this.transportadora.getPrecoPorVolume())
                    + (pesoTotal * this.transportadora.getPrecoPorPeso());
        } else {
            this.frete = 0.0;
        }
        calcularTotal();
    }

    // Método para definir a transportadora
    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
        calcularFrete();
    }

    // Método para adicionar produto ao pedido
    public void adicionar(Produto produto) {
        adicionar(1, produto); // Chama o método sobrecarregado com quantidade = 1
    }

    // Método sobrecarregado para adicionar produto com quantidade
    public void adicionar(int quantidade, Produto produto) {
        Item itemExistente = null;

        // Verificar se já existe um item com o mesmo produto
        for (Item item : listaItens) {
            if (item.getProduto().equals(produto)) {
                itemExistente = item;
                break;
            }
        }

        if (itemExistente != null) {
            itemExistente.setQuantidade(itemExistente.getQuantidade() + quantidade);
        } else {
            listaItens.add(new Item(quantidade, produto));
        }

        atualizarSubtotal();
        calcularFrete();
    }

    // Método para remover produto do pedido
    public void remover(Produto produto) {
        Item itemARemover = null;

        // Encontrar o item com o produto indicado
        for (Item item : listaItens) {
            if (item.getProduto().equals(produto)) {
                itemARemover = item;
                break;
            }
        }

        if (itemARemover != null) {
            listaItens.remove(itemARemover);
            System.out.println("Produto removido.");
        } else {
            System.out.println("Não há como remover o item com o produto indicado.");
        }

        atualizarSubtotal();
        calcularFrete();
    }

    // Método para atualizar o subtotal
    private void atualizarSubtotal() {
        this.subtotal = 0.0;
        for (Item item : listaItens) {
            this.subtotal += item.getSubtotal();
        }
        calcularTotal();
    }

    // Getters e Setters
    public String getDataHora() {
        return dataHora;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getFrete() {
        return frete;
    }

    public double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public List<Item> getListaItens() {
        return listaItens;
    }

    public List<Item> getItens() {
        return listaItens;
    }
}

