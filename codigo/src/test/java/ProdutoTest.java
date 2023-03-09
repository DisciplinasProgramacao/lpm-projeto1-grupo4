import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProdutoTest {

    private Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto de Teste", "Descrição de Teste", 100.0, 45.5, 10, 2);
    }

    @Test
    public void testSetMargemDeLucroInvalida() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> produto.setMargemDeLucro(200.0));
    }

    @Test
    public void testSetMargemDeLucro() {
        produto.setMargemDeLucro(45.6);
        Assertions.assertEquals(45.6, produto.getMargemDeLucro());
    }

    @Test
    public void testSetDescricaoValida() {
        produto.setDescricao("Nova Descrição");
        Assertions.assertEquals("Nova Descrição", produto.getDescricao());
    }

    @Test
    public void testSetDescricaoInvalida() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> produto.setDescricao("AB"));
    }

    @Test
    public void testSetQuantidadeValida() {
        produto.setQuantidade(20);
        Assertions.assertEquals(20, produto.getQuantidade());
    }

    @Test
    public void testValidateQuantidadesInvalida() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> produto.setQuantidade(-10));
    }

    @Test
    public void testGetPrecoDeVenda() {
        Assertions.assertEquals(171.69, produto.getPrecoDeVenda(), 0.01);
    }
}
