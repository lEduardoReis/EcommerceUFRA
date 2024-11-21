import java.util.List;

public class Transportadora {
    private String nome;
    private double precoPorVolume;
    private double precoPorPeso;

    public Transportadora(String nome, double precoPorVolume, double precoPorPeso) {
        this.nome = nome;
        this.precoPorVolume = precoPorVolume;
        this.precoPorPeso = precoPorPeso;
    }

    // Método para calcular o frete dos itens e determinar o tipo de frete
    public double calcularFrete(List<Item> itens) {
        double freteTotal = 0.0;

        for (Item item : itens) {
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();

            // Calcula o frete para o produto considerando o maior valor entre peso e volume
            double fretePorVolume = produto.getVolume() * precoPorVolume * quantidade;
            double fretePorPeso = produto.getPeso() * precoPorPeso * quantidade;

            // Usa o maior valor como o frete do produto
            double freteProduto = Math.max(fretePorVolume, fretePorPeso);

            freteTotal += freteProduto;
        }

        return freteTotal;
    }

    public String determinarTipoFrete(Item item) {
        Produto produto = item.getProduto();
        double fretePorVolume = produto.getVolume() * precoPorVolume * item.getQuantidade();
        double fretePorPeso = produto.getPeso() * precoPorPeso * item.getQuantidade();

        return fretePorVolume >= fretePorPeso ? "Frete por Volume" : "Frete por Peso";
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoPorVolume() {
        return precoPorVolume;
    }

    public void setPrecoPorVolume(double precoPorVolume) {
        this.precoPorVolume = precoPorVolume;
    }

    public double getPrecoPorPeso() {
        return precoPorPeso;
    }

    public void setPrecoPorPeso(double precoPorPeso) {
        this.precoPorPeso = precoPorPeso;
    }
}
