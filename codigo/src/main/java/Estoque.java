
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

public class Estoque {
    Set<Produto> produtos;
    Set<ControleDeVendas> vendasProdutos;

    public Estoque() {
        this.produtos = new HashSet<>();
        this.vendasProdutos = new HashSet<>();
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public void cadastrarProduto(Produto produto) {
        Optional<Produto> prodEncontrado  = produtos.stream().filter(p -> p.getNome().equals(produto.getNome())).findFirst();
        prodEncontrado.ifPresentOrElse(value -> value.setQuantidade(value.getQuantidade() + produto.getQuantidade()), () -> produtos.add(produto));
    }

    public void adicionarEstoqueNoProduto(String nomeProduto, Integer quantidade) {
        Optional<Produto> prodEncontrado  = produtos.stream().filter(p ->p.getNome().equals(nomeProduto)).findFirst();
        prodEncontrado.ifPresentOrElse(value -> value.setQuantidade(value.getQuantidade() + quantidade), () -> {
                    throw new IllegalArgumentException("Produto não encontrado");
                }
        );
    }

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

    private void addVendaNoControleDeVendas(Integer quantidade, Produto p) {
        Optional<ControleDeVendas> vendas =  vendasProdutos.stream().filter(v -> v.getProduto().getNome().equals(p.getNome())).findFirst();
        vendas.ifPresentOrElse(v -> v.setItemsVendidos(v.getItemsVendidos() + quantidade), () -> vendasProdutos.add(new ControleDeVendas(p, quantidade)));
    }


    public RelatoriosVendasDTO gerarRelatorioDeVendas(String nomeProduto) {
        ControleDeVendas controleDeVendas  = vendasProdutos.stream().filter(v -> v.getProduto().getNome().equals(nomeProduto)).findFirst().orElse(null);
        if (nonNull(controleDeVendas)) {
            Integer vendidos = controleDeVendas.getItemsVendidos();
            return new RelatoriosVendasDTO(nomeProduto, controleDeVendas.getItemsVendidos(), controleDeVendas.getProduto().getPrecoDeVenda() * vendidos, vendidos * controleDeVendas.getProduto().getMargemDeLucro());
        }
        return null;
    }

    public void imprimirRelatorioVendas(String nomeProduto) {
        ControleDeVendas controleDeVendas  = vendasProdutos.stream().filter(v -> v.getProduto().getNome().equals(nomeProduto)).findFirst().orElse(null);
        if (nonNull(controleDeVendas)) {
            Integer vendidos = controleDeVendas.getItemsVendidos();
            RelatoriosVendasDTO r  = new RelatoriosVendasDTO(nomeProduto, controleDeVendas.getItemsVendidos(), controleDeVendas.getProduto().getPrecoDeVenda() * vendidos, vendidos * controleDeVendas.getProduto().getMargemDeLucro());
            r.imprimirRelatorio();
        }
    }

}
