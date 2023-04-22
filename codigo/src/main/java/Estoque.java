
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.print.attribute.HashDocAttributeSet;

import static java.util.Objects.nonNull;

import java.util.HashMap;

public class Estoque {
    Set<Produto> produtos;
    Set<ControleDeVendas> vendasProdutos;
    Map<String, Produto> meusProdutos; //adicionado por João

    /**
        Cria um objeto Estoque com conjuntos de produtos e vendas vazios.
    */
    public Estoque() {
        this.produtos = new HashSet<>();
        this.vendasProdutos = new HashSet<>();
        this.meusProdutos = new HashMap<>();
    }

    /**
        Retorna o conjunto de produtos armazenados no estoque.
        @return o conjunto de produtos armazenados no estoque
     */
    public Set<Produto> getProdutos() {
        return produtos;
    }

    /**
        Define o conjunto de produtos armazenados no estoque.
        @param produtos o conjunto de produtos armazenados no estoque
    */
    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
        Cadastra um novo produto ou atualiza a quantidade de um produto existente na lista de produtos.
        Se o produto já existe na lista, atualiza a quantidade do produto existente com a quantidade do produto a ser adicionado.
        Caso contrário, adiciona o novo produto na lista.
        @param produto o objeto Produto a ser adicionado ou atualizado na lista de produtos.
    */
    public void cadastrarProduto(Produto produto) {
        Optional<Produto> prodEncontrado  = produtos.stream().filter(p -> p.getNome().equals(produto.getNome())).findFirst();
        prodEncontrado.ifPresentOrElse(value -> value.setQuantidade(value.getQuantidade() + produto.getQuantidade()), () -> produtos.add(produto));

        this.meusProdutos.put(produto.getDescricao(), produto); //adicionado por joao
    }

    /**
    Adiciona uma determinada quantidade ao estoque de um produto com o nome especificado.
        @param nomeProduto o nome do produto que terá o estoque atualizado.
        @param quantidade a quantidade que será adicionada ao estoque do produto.
        @throws IllegalArgumentException se o produto não existir na lista de produtos.
    */
    public void adicionarEstoqueNoProduto(String nomeProduto, Integer quantidade) {
        Optional<Produto> prodEncontrado  = produtos.stream().filter(p ->p.getNome().equals(nomeProduto)).findFirst();
        prodEncontrado.ifPresentOrElse(value -> value.setQuantidade(value.getQuantidade() + quantidade), () -> {
                    throw new IllegalArgumentException("Produto não encontrado");
                }
        );
    }

    //////CRIADO POR JOAO
    public void adicionarProdutoNoEstoque(String nomeProduto, Integer quantidade) {
        Produto prod = meusProdutos.get(nomeProduto);
        if(prod!=null){
            prod.acrescentarNoEstoque(quantidade);
        }
    }


    /**
        Realiza a venda de uma determinada quantidade de um produto com o nome especificado.
        @param nomeProduto o nome do produto a ser vendido.
        @param quantidade a quantidade a ser vendida.
        @throws IllegalArgumentException se o produto não existir na lista de produtos ou se a quantidade disponível for insuficiente.
    */
    public void venderProduto(String nomeProduto, Integer quantidade) {
        Optional<Produto> prodEncontrado  = produtos.stream().filter(p ->p.getNome().equals(nomeProduto)).findFirst();
        prodEncontrado.ifPresentOrElse(p -> {
            if (p.getQuantidade() < quantidade) {
                throw new IllegalArgumentException("Quantiadade Insuficiente");
            } else {
                p.setQuantidade(p.getQuantidade() - quantidade);
                addVendaNoControleDeVendas(quantidade, p);
            }
        }, () ->{
            throw new IllegalArgumentException("Não tem como vender um produto que não existe");
        });
    }

    /**
        Adiciona uma venda ao controle de vendas para um produto especificado.
        @param quantidade a quantidade vendida.
        @param p o produto vendido.
     */
    private void addVendaNoControleDeVendas(Integer quantidade, Produto p) {
        Optional<ControleDeVendas> vendas =  vendasProdutos.stream().filter(v -> v.getProduto().getNome().equals(p.getNome())).findFirst();
        vendas.ifPresentOrElse(v -> v.setItemsVendidos(v.getItemsVendidos() + quantidade), () -> vendasProdutos.add(new ControleDeVendas(p, quantidade)));
    }

    /**
        Gera um relatório de vendas para um produto com o nome especificado.
        @param nomeProduto o nome do produto a ser analisado.
        @return um objeto RelatoriosVendasDTO com as informações de venda do produto, ou null se o produto não tiver sido vendido.
     */
    public RelatoriosVendasDTO gerarRelatorioDeVendas(String nomeProduto) {
        ControleDeVendas controleDeVendas  = vendasProdutos.stream().filter(v -> v.getProduto().getNome().equals(nomeProduto)).findFirst().orElse(null);
        if (nonNull(controleDeVendas)) {
            Integer vendidos = controleDeVendas.getItemsVendidos();
            return new RelatoriosVendasDTO(nomeProduto, controleDeVendas.getItemsVendidos(), controleDeVendas.getProduto().getPrecoDeVenda() * vendidos, vendidos * controleDeVendas.getProduto().getMargemDeLucro());
        }
        return null;
    }

    /////////////////////////ADICIONADO POR JOÃO. Compare com o código acima. 
    public String gerarRelatorio(String nomeProduto) {
        String relat = null;
        Produto produto = this.meusProdutos.get(nomeProduto);

        if(produto!=null) 
            relat =produto.toString();  

        return relat;
    }



    /**
        Gera e imprime um relatório de vendas para um produto com o nome especificado.
        @param nomeProduto o nome do produto a ser analisado.
     */
    public void imprimirRelatorioVendas(String nomeProduto) {
        ControleDeVendas controleDeVendas  = vendasProdutos.stream().filter(v -> v.getProduto().getNome().equals(nomeProduto)).findFirst().orElse(null);
        if (nonNull(controleDeVendas)) {
            Integer vendidos = controleDeVendas.getItemsVendidos();
            RelatoriosVendasDTO r  = new RelatoriosVendasDTO(nomeProduto, controleDeVendas.getItemsVendidos(), controleDeVendas.getProduto().getPrecoDeVenda() * vendidos, vendidos * controleDeVendas.getProduto().getMargemDeLucro());
            r.imprimirRelatorio();
        }
    }

}
