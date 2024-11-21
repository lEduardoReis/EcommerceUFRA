import java.util.List;

public class ProdutoCombo extends Produto {
    private String tipoDesconto; // "VALOR" ou "PERCENTUAL"
    private double valorDesconto;
    private List<Produto> listaProdutos;

    public ProdutoCombo(String tipoDesconto, double valorDesconto, List<Produto> listaProdutos) {
        super("Combo", 0, 0, 0); // Nome padrão "Combo", preço e medidas inicializados como 0
        this.tipoDesconto = tipoDesconto;
        this.valorDesconto = valorDesconto;
        this.listaProdutos = listaProdutos;
    }

    @Override
    public double getPreco() {
        double somaPrecoProdutos = 0;

        for (Produto produto : listaProdutos) {
            somaPrecoProdutos += produto.getPreco();
        }

        if ("PERCENTUAL".equalsIgnoreCase(tipoDesconto)) {
            return somaPrecoProdutos * (1 - valorDesconto / 100);
        } else if ("VALOR".equalsIgnoreCase(tipoDesconto)) {
            return somaPrecoProdutos - valorDesconto;
        }

        return somaPrecoProdutos; // Sem desconto se tipoDesconto for inválido
    }

    // Getters e Setters
    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(String tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
}


