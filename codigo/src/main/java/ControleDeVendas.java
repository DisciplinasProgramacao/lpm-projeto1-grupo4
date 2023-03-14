public class ControleDeVendas {
    private Produto produto;
    private Integer itemsVendidos;

    /**
        Cria um objeto de controle de vendas para um determinado produto e quantidade de itens vendidos.
        @param produto O objeto Produto que representa o produto vendido.
        @param itemsVendidos O número de unidades do produto vendido.
    */
    public ControleDeVendas(Produto produto, Integer itemsVendidos) {
        this.produto = produto;
        this.itemsVendidos = itemsVendidos;
    }

    /**
        Retorna o produto associado a esta venda.
        @return o produto associado a esta venda
    */
    public Produto getProduto() {
        return produto;
    }

    /**
        Define o produto que será vendido.
        @param produto o produto a ser vendido
    */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
        Retorna os itens vendidos associados a este produto.
        @return o produto associado a esta venda
    */
    public Integer getItemsVendidos() {
        return itemsVendidos;
    }

    /**
        Define o número de itens vendidos para esse produto.
        @param itemsVendidos o número de itens vendidos
    */
    public void setItemsVendidos(Integer itemsVendidos) {
        this.itemsVendidos = itemsVendidos;
    }


}
