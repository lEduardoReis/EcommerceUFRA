public class Item {
    private int quantidade;
    private double subtotal;
    private Produto produto;

    public Item(int quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
        calcularSubtotal(); // Inicializa o subtotal ao criar o objeto
    }

    public void calcularSubtotal() {
        this.subtotal = this.quantidade * this.produto.getPreco();
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal(); // Atualiza o subtotal sempre que a quantidade é alterada
    }

    // Getters e Setters
    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        calcularSubtotal(); // Recalcula o subtotal ao alterar o produto
    }
}


