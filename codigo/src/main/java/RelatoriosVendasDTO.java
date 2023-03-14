public class RelatoriosVendasDTO {
    String nomeProduto;
    Integer itemsVendidos;
    Double valorArrecadado;
    Double lucro;

    /**
        Cria um objeto {@code RelatoriosVendasDTO} com os dados de um produto vendido.
        @param nomeProduto nome do produto vendido.
        @param itemsVendidos quantidade de itens vendidos.
        @param valorArrecadado valor total arrecadado com as vendas do produto.
        @param lucro lucro obtido com as vendas do produto.
    */
    public RelatoriosVendasDTO(String nomeProduto, Integer itemsVendidos, Double valorArrecadado, Double lucro) {
        this.nomeProduto = nomeProduto;
        this.itemsVendidos = itemsVendidos;
        this.valorArrecadado = valorArrecadado;
        this.lucro = lucro;
    }

    /**
        Cria um objeto {@code RelatoriosVendasDTO} com o nome do produto.
        @param nomeProduto nome do produto.
    */
    public RelatoriosVendasDTO(String nomeProduto) {
        this.nomeProduto = nomeProduto;
        this.itemsVendidos = 0;
        this.valorArrecadado = 0.0;
        this.lucro = 0.0;
    }

    /**
        Imprime o relatório de vendas do produto com seus dados.
    */
    public void imprimirRelatorio() {
        System.out.println("Relatório do produto: " + this.nomeProduto);
        System.out.println("Items vendidos: " + this.itemsVendidos);
        System.out.println("Valor arrecadado: " + String.format("%.2f", this.valorArrecadado));
        System.out.println("Lucro: " + String.format("%.2f", this.lucro));

    }
}
