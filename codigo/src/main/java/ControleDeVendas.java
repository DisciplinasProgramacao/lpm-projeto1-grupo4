public class ControleDeVendas {
    private Produto produto;
    private Integer itemsVendidos;

    public ControleDeVendas(Produto produto, Integer itemsVendidos) {
        this.produto = produto;
        this.itemsVendidos = itemsVendidos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getItemsVendidos() {
        return itemsVendidos;
    }

    public void setItemsVendidos(Integer itemsVendidos) {
        this.itemsVendidos = itemsVendidos;
    }


}
