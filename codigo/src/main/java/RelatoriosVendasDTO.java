public class RelatoriosVendasDTO {
    String nomeProduto;
    Integer itemsVendidos;
    Double valorArrecadado;
    Double lucro;

    public RelatoriosVendasDTO(String nomeProduto, Integer itemsVendidos, Double valorArrecadado, Double lucro) {
        this.nomeProduto = nomeProduto;
        this.itemsVendidos = itemsVendidos;
        this.valorArrecadado = valorArrecadado;
        this.lucro = lucro;
    }

    public RelatoriosVendasDTO(String nomeProduto) {
        this.nomeProduto = nomeProduto;
        this.itemsVendidos = 0;
        this.valorArrecadado = 0.0;
        this.lucro = 0.0;
    }

    public void imprimirRelatorio() {
        System.out.println("Relatório do produto: " + this.nomeProduto);
        System.out.println("Items vendidos: " + this.itemsVendidos);
        System.out.println("Valor arrecadado: " + String.format("%.2f", this.valorArrecadado));
        System.out.println("Lucro: " + String.format("%.2f", this.lucro));

    }
}
