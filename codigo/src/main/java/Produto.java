public class Produto {
    private String nome;
    private String descricao;
    private Double precoCusto;
    private Double margemDeLucro;
    private Integer quantidade;
    private Integer quantidadeMinima;

    
    /**

        Cria um novo produto com as informações fornecidas.
        @param nome O nome do produto.
        @param descricao A descrição do produto.
        @param precoCusto O preço de custo do produto.
        @param margemDeLucro A margem de lucro desejada para o produto, em porcentagem.
        @param quantidade A quantidade disponível em estoque do produto.
        @param quantidadeMinima A quantidade mínima desejada em estoque para o produto.
        @throws IllegalArgumentException se o nome, descrição, preço de custo, margem de lucro, quantidade ou quantidade mínima fornecidos forem inválidos.
    */
    public Produto(String nome, String descricao, Double precoCusto, Double margemDeLucro, Integer quantidade, Integer quantidadeMinima) {
        this.nome = nome;
        setDescricao(descricao);
        this.precoCusto = precoCusto;
        setMargemDeLucro(margemDeLucro);
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        validateQuantidades();
    }

    /**
        Retorna o nome do produto.
        @return O nome do produto.
    */
    public String getNome() {
        return nome;
    }

    /**
        Retorna a descrição do produto.
        @return A descrição do produto.
    */    
    public String getDescricao() {
        return descricao;
    }

    /**
        Retorna a quantidade disponível em estoque do produto.
        @return A quantidade disponível em estoque do produto.
    */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
        Define a quantidade disponível em estoque do produto.
        Se a quantidade fornecida for inválida, uma exceção "IllegalArgumentException" será lançada.
        @param quantidade A nova quantidade disponível em estoque do produto.
        @throws IllegalArgumentException se a quantidade fornecida for inválida.
    */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        validateQuantidades();
    }

    //////////////ACRESCENTADO POR JOÃO
    public void acrescentarNoEstoque(int quantidade){
        if(quantidade>0)
            this.quantidade += quantidade;
    }

    /**
        Retorna o preço de venda do produto.
        O preço de venda é calculado a partir do preço de custo, margem de lucro e taxa de imposto de 18%.
        @return O preço de venda do produto.
    */
    public Double getPrecoDeVenda() {
        return (precoCusto + margemDeLucro) * 1.18;
    }

    /**
        Define a margem de lucro do produto.
        Se a margem de lucro fornecida for inválida, uma exceção "IllegalArgumentException" será lançada.
        @param margemDeLucro A nova margem de lucro do produto.
        @throws IllegalArgumentException se a margem de lucro fornecida for inválida.
    */
    public void setMargemDeLucro(Double margemDeLucro) {
        if (!(margemDeLucro >=  0.3 * precoCusto && margemDeLucro <= 0.8 * precoCusto)) {
            throw new IllegalArgumentException("A margem de lucro deve estar entre 30% e 80% do preco de custo");
        }
        this.margemDeLucro = margemDeLucro;
    }

    /**
        Retorna a margem de lucro do produto.
        @return A margem de lucro do produto.
    */
    public Double getMargemDeLucro() {
        return margemDeLucro;
    }

    /**
        Define a descrição do produto.
        Se a descrição fornecida tiver menos de 3 caracteres, uma exceção "IllegalArgumentException" será lançada.
        @param descricao A nova descrição do produto.
        @throws IllegalArgumentException se a descrição fornecida tiver menos de 3 caracteres.
    */
    public void setDescricao(String descricao) {
        if (descricao.length() < 3) {
            throw new IllegalArgumentException("A descrição precisa ter pelo menos 3 caracteres");
        }
        this.descricao = descricao;
    }

    /**
        Valida as quantidades de produto.
        Se a quantidade ou quantidade mínima for menor que zero, uma exceção "IllegalArgumentException" será lançada.
        @throws IllegalArgumentException se a quantidade ou quantidade mínima for menor que zero.
    */
    public void validateQuantidades() {
        if (this.quantidade < 0 || this.quantidadeMinima < 0) {
            throw new IllegalArgumentException("A quantidade e a quantidade mínima devem ser maiores que zero");
        }
    }

    
}
