import java.util.HashSet;
import java.util.Set;

public class EstoqueHandler {

    //Esta eh apenas uma pre main de testes, ha uma mais recente e com um melhor uso dentre os arquivos
    public static void main(String[] args) {
        Set<Produto> produtos = new HashSet<>();
        produtos.add(new Produto("Sabonte dove", "Sabonete para banho",1.0, 0.3, 10, 4));
        produtos.add(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 40, 30));
        produtos.add(new Produto("Desodorante rexona", "Desodorante rexona masculino",11.0, 3.4, 14, 4));
        produtos.add(new Produto("Cerveja corona", "Cerveja corona 330ml",4.0, 2.0, 100, 50));


        Estoque estoqueLoja = new Estoque();
        estoqueLoja.setProdutos(produtos);
        // vamos vender 5 sabonetes
        estoqueLoja.venderProduto("Sabonte dove", 5);
        estoqueLoja.venderProduto("Coca-cola", 10);
        estoqueLoja.adicionarEstoqueNoProduto("Sabonte dove", 10);

        estoqueLoja.imprimirRelatorioVendas("Sabonte dove");
        estoqueLoja.imprimirRelatorioVendas("Coca-cola");
    }
}
