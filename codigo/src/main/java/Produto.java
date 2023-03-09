public class Produto {
    private String nome;
    private String descricao;
    private Double precoCusto;
    private Double margemDeLucro;
    private Integer quantidade;
    private Integer quantidadeMinima;


    public Produto(String nome, String descricao, Double precoCusto, Double margemDeLucro, Integer quantidade, Integer quantidadeMinima) {
        this.nome = nome;
        setDescricao(descricao);
        this.precoCusto = precoCusto;
        setMargemDeLucro(margemDeLucro);
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        validateQuantidades();
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        validateQuantidades();
    }

    public Double getPrecoDeVenda() {
        return (precoCusto + margemDeLucro) * 1.18;
    }

    public void setMargemDeLucro(Double margemDeLucro) {
        if (!(margemDeLucro >=  0.3 * precoCusto && margemDeLucro <= 0.8 * precoCusto)) {
            throw new IllegalArgumentException("A margem de lucro deve estar entre 30% e 80% do preco de custo");
        }
        this.margemDeLucro = margemDeLucro;
    }

    public Double getMargemDeLucro() {
        return margemDeLucro;
    }

    public void setDescricao(String descricao) {
        if (descricao.length() < 3) {
            throw new IllegalArgumentException("A descrição precisa ter pelo menos 3 caracteres");
        }
        this.descricao = descricao;
    }

    public void validateQuantidades() {
        if (this.quantidade < 0 || this.quantidadeMinima < 0) {
            throw new IllegalArgumentException("A quantidade e a quantidade mínima devem ser maiores que zero");
        }
    }
}
