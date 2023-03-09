import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {

    Estoque estoque;

    @BeforeEach
    void setUp() {
        estoque = new Estoque();
    }

    @Test
    void testCadastrarProduto() {
        assertAll(
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 40, 30));
                    assertTrue(estoque.getProdutos().stream().anyMatch(p -> p.getNome().equals("Coca-cola")), "Deve haver um produto com nome 'Coca-cola' no estoque");
                },
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 10, 30));
                    Optional<Produto> prodOpt = estoque.getProdutos().stream().filter(p -> p.getNome().equals("Coca-cola")).findFirst();
                    int qtd = prodOpt.isPresent() ? prodOpt.get().getQuantidade() : 0;
                    assertEquals(
                            50,
                            qtd,
                            "Deve adicionar 10 à quantidade de Coca-cola"
                    );
                }
        );
    }

    @Test
    void testAdicionarEstoqueNoProduto() {
        estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 40, 30));
        estoque.adicionarEstoqueNoProduto("Coca-cola", 10);
        Optional<Produto> prodOpt = estoque.getProdutos().stream().filter(p -> p.getNome().equals("Coca-cola")).findFirst();
        int qtd = prodOpt.isPresent() ? prodOpt.get().getQuantidade() : 0;
        assertEquals(
                50,
                qtd,
                "Deve adicionar 10 à quantidade de Coca-cola"
        );
    }

    @Test
    void venderProduto() {
    }

    @Test
    void gerarRelatorioDeVendas() {
    }

    @Test
    void imprimirRelatorioVendas() {
    }
}
