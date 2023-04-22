import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EstoqueTest {

    Estoque estoque;
    Produto bala;

    private Produto getProdutoByName(String nomeProduto) {
        Optional<Produto> prodOpt = estoque.getProdutos().stream().filter(
                p -> p.getNome().equals(nomeProduto)
        ).findFirst();

        return prodOpt.orElse(null);
    }

    @BeforeEach
    void setUp() {
        estoque = new Estoque();
        bala = new Produto("Bala", "Bala",100d,50d,100,50);
    }

    
    //adicionado por Joao. Compare com o teste abaixo. Fazem a mesma coisa.
    @Test 
    public void deveCadastrarProdutoNoEstoque(){
        estoque.cadastrarProduto(bala);
        assertNotNull(estoque.gerarRelatorio("Bala"));
    }

    @Test
    void testCadastrarProduto() {
        assertAll(
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L", 6.0, 2.7, 40, 30));
                    assertTrue(
                            estoque.getProdutos().stream().anyMatch(p -> p.getNome().equals("Coca-cola")),
                            "Deve haver um produto com nome 'Coca-cola' no estoque"
                    );
                },
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L", 6.0, 2.7, 10, 30));
                    assertEquals(
                            50,
                            getProdutoByName("Coca-cola").getQuantidade(),
                            "Deve adicionar 10 à quantidade de Coca-cola"
                    );
                }
        );
    }

    ///criado por JOAO, para mostrar que FALHA
    @Test 
    public void naoDeveAdicionarEstoqueNegativo(){
        
        estoque.cadastrarProduto(bala);
        estoque.adicionarEstoqueNoProduto("Bala", -10);
        assertEquals(100,bala.getQuantidade());
        
        
    }

    @Test
    void testAdicionarEstoqueNoProduto() {
        assertAll(
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L", 6.0, 2.7, 40, 30));
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
    void testVenderProduto() {
        assertAll(
                () -> {
                    estoque.cadastrarProduto(new Produto("Coca-cola", "Refrigerante coca-cola 2L", 6.0, 2.7, 40, 30));
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
    void testGerarRelatorioDeVendas() {
        String nomeProduto = "Coca-cola";
        Produto produto = new Produto(nomeProduto, "Refrigerante coca-cola 2L", 6.0, 2.7, 40, 30);
        estoque.cadastrarProduto(produto);
        estoque.venderProduto(nomeProduto, 10);

        assertAll(
                () -> {
                    RelatoriosVendasDTO relatorio = estoque.gerarRelatorioDeVendas(nomeProduto);
                    assertInstanceOf(RelatoriosVendasDTO.class, relatorio);
                    assertEquals("Coca-cola", relatorio.nomeProduto);
                    assertEquals(10, relatorio.itemsVendidos);
                    assertEquals(produto.getPrecoDeVenda() * 10, relatorio.valorArrecadado);
                    assertEquals(produto.getMargemDeLucro() * 10, relatorio.lucro);
                },
                () -> {
                    assertNull(
                            estoque.gerarRelatorioDeVendas("Guarana"),
                            "Deve retornar null pois não há vendas de Guarana"
                    );
                }
        );
    }

    @Test
    void imprimirRelatorioVendas() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String nomeProduto = "Coca-cola";
        Produto produto = new Produto(nomeProduto, "Refrigerante coca-cola 2L", 6.0, 2.7, 40, 30);
        estoque.cadastrarProduto(produto);
        estoque.venderProduto(nomeProduto, 10);
        RelatoriosVendasDTO relatorio = estoque.gerarRelatorioDeVendas(nomeProduto);

        relatorio.imprimirRelatorio();

        assertEquals(
                "Relatório do produto: Coca-cola\nItems vendidos: 10\nValor arrecadado: "
                        + String.format("%.2f", relatorio.valorArrecadado)
                        + "\nLucro: " + String.format("%.2f", relatorio.lucro) + "\n",
                outContent.toString()
        );
    }
}
