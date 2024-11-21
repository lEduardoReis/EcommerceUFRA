import java.util.ArrayList;
import java.util.List;

public class Ecommerce {
    private List<Produto> listaProdutos;
    private List<Cliente> listaClientes;
    private List<Pedido> listaPedidos;
    private List<Transportadora> listaTransportadoras;

    public Ecommerce() {
        this.listaProdutos = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
        this.listaTransportadoras = new ArrayList<>();
    }

    // Método pagar sem voucher
    public boolean pagar(Cliente pedido, double valor) {
        if (valor >= pedido.getTotal()) {
            System.out.println("Pagamento realizado com sucesso!");
            return true;
        } else {
            System.out.println("Pagamento insuficiente.");
            return false;
        }
    }

    // Método pagar com voucher
    public boolean pagar(Pedido pedido, double valor, String voucher) {
        double desconto = (voucher != null) ? 0.1 : 0.0; // 10% de desconto se houver voucher
        double valorComDesconto = pedido.getTotal() * (1 - desconto);
        if (valor >= valorComDesconto) {
            System.out.println("Pagamento realizado com sucesso com desconto!");
            return true;
        } else {
            System.out.println("Pagamento insuficiente mesmo com desconto.");
            return false;
        }
    }

    // Método para cadastrar cliente
    public void cadastrar(Cliente cliente) {
        listaClientes.add(cliente);
        System.out.println("Cliente cadastrado: " + cliente.getNome());
    }

    // Método para comprar produto
    public void comprar(Produto produto) {
        listaProdutos.add(produto);
        System.out.println("Produto adicionado: " + produto.getNome());
    }

    // Método para credenciar transportadora
    public void credenciar(Transportadora transportadora) {
        listaTransportadoras.add(transportadora);
        System.out.println("Transportadora credenciada: " + transportadora.getNome());
    }

    // Getters e Setters
    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public List<Transportadora> getListaTransportadoras() {
        return listaTransportadoras;
    }
}


