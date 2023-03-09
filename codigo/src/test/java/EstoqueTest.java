import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {

    Estoque estoque;

    private Produto getProdutoByName(String nomeProduto) {
        Optional<Produto> prodOpt = estoque.getProdutos().stream().filter(
                p -> p.getNome().equals(nomeProduto)
        ).findFirst();

        return prodOpt.orElse(null);
    }

    @BeforeEach
    void setUp() {
        estoque = new Estoque();
    }

    @Test
    void testCadastrarProduto() {
        assertAll(
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 40, 30));
                    assertTrue(
                            estoque.getProdutos().stream().anyMatch(p -> p.getNome().equals("Coca-cola")),
                            "Deve haver um produto com nome 'Coca-cola' no estoque"
                    );
                },
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 10, 30));
                    assertEquals(
                            50,
                            getProdutoByName("Coca-cola").getQuantidade(),
                            "Deve adicionar 10 à quantidade de Coca-cola"
                    );
                }
        );
    }

    @Test
    void testAdicionarEstoqueNoProduto() {
        assertAll(
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 40, 30));
                    estoque.adicionarEstoqueNoProduto("Coca-cola", 10);
                    assertEquals(
                            50,
                            getProdutoByName("Coca-cola").getQuantidade(),
                            "Deve adicionar 10 à quantidade de Coca-cola"
                    );
                },
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> estoque.adicionarEstoqueNoProduto("Guarana", 10),
                            "Deve lançar IllegalArgumentException pois Guarana não está cadastrado"
                    );
                }
        );
    }

    @Test
    void venderProduto() {
        assertAll(
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L",6.0, 2.7, 40, 30));
                    estoque.venderProduto("Coca-cola", 10);
                    assertEquals(
                            30,
                            getProdutoByName("Coca-cola").getQuantidade(),
                            "Deve ter vendido 10 unidades"
                    );
                },
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> estoque.venderProduto("Coca-cola", 40),
                            "Deve lançar IllegalArgumentException pois não há 40 Coca-cola"
                    );
                },
                () -> {
                    assertThrows(
                            IllegalArgumentException.class,
                            () -> estoque.venderProduto("Guarana", 10),
                            "Deve lançar IllegalArgumentException pois Guarana não está cadastrado"
                    );
                }
        );
    }

    @Test
    void gerarRelatorioDeVendas() {
    }

    @Test
    void imprimirRelatorioVendas() {
    }
}
