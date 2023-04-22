[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-f4981d0f882b2a3f0472912d15f9806d57e124e0fc890972558857b51b24a6f9.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10064418)
# Projeto Gestão de estoque
Projeto que permite um usuario cadastrar, ver e organizar seus produtos.

---

## Nota base do grupo: 7,5

A nota final, que é individual, se dará pela nota acima, multiplicada por um peso entre 0 e 1 relativo ao acompanhamento semanal do projeto. Lembre-se: não é só a entrega do produto finalizado que importa, é todo o processo de sua construção e as entregas parciais para o “cliente”.

## Comentários

Pessoal, uma observação geral: a estrutura do trabalho de vocês está **MUITO** maior do que o necessário. O uso completamente desnecessário de recursos só faz piorar a legibilidade sem nenhum benefício ao sistema. Pelo contrário: possivelmente gastaram um tempo desnecessário, não sei se o main ficou incompleto por isso, e o código não foi testado corretamente por causa da superestrutura. Quando o problema é simples, a solução é simples. Vocês usaram **ERRADAMENTE** um monte de coisas que não estamos nem perto de estudar. Se tivessem usado corretamente, não seria tanto problema. De todo jeito, pensem: vocês acham que eu passaria um exercício no primeiro mês de aula que necessitasse que vocês usassem opcionais, streams, coleções, exceções, sendo todos estes assuntos coisas que vocês ainda não viram na matéria?

Exemplos:
[Consulta ao produto](/docs/keepItSimple.PNG)
[Inserindo produto no estoque produto](/docs/keepItSimple2.PNG)

- Mensagem no sistema: "Não foi possível cadastrar um produto devio ao erro : A margem de lucro deve estar entre 30% e 80% do preco de custo". Eu digitei 50 na margem de lucro. Qual foi o erro?. 
- Documentação do Produto fiz que margem é porcentagem. Digitei 0.50. Deu exceção
- Digitei 0,50. Repetiu a mensagem de erro acima.
- Fiz a inserção de dois produtos "na mão" no main. Na hora do relatório, exceção novamente.
- Relatório vendas deve receber um produto, e não criar um novo.
- Uso desnecessário de estruturas causando peso e ilegibilidade no código. Veja [o código de consulta ao produto](/docs/keepItSimple.PNG) e compare com o de vocês.

- Sem descontar ponto (ainda):
    - uso incorreto de exceções: são geradas por suas classes, mas não tratadas do sistema. Do que adianta, se mata o sistema em qualquer erro?
    - o que é o estoque handler? o que ele está fazendo?
    - Se a classe Relatorio existe (denecessária no momento), melhor um método static: faz o mesmo com qualquer produto.
    - Se a classe Relatorio apenas imprime informações de produto (sem flexibilidade), por que ela é uma classe e não um método de produto?
    - Se vai usar coleções e streams, usem direito. Não faz sentido usar "set" e depois ficar fazendo streams desnecessárias [como esta](/docs/keepItSimple.PNG). Set não é uma coleção adequada para busca/uso de dados.

### Diagrama + aderência das classes ao diagrama: 0,5/2 pontos 
    - Relação invertida entre Produto e Estoque, entre Controle e Estoque
    - Classe ControleDeVenda não faz nada (do jeito que está, poderia/deveria ser atributo do produto.)
    - Superestrutura gerada (veja comentário acima) prejudicando funcionamento do sistema. 
### Requisitos corretamente implementados: 2/6 pontos 
	- produto (preço, estoque)
        - margem de lucro deve ser calculada pelo produto a partir da aliquota (ou a documentação deveria deixar claro o que significa), pois o construtor informa porcentagem
        - validação deve validar e proteger. De que adianta "validarQuantidade" se mata o produto já existente?
	- estoque (valor, abaixo do estoque)
        - há um teste correto de produto para inserir quantidade negativa, porém o estoque consegue inserir quantidade negativa
	- sistema (vender, comprar, consultas)
        - sem conseguir executar direito por causa do erro de cadastro
        - após colocar produtos "na mão", pedi relatório de Bala e não me informou nada.
        - sem relatórios consolidados de estoque

### Documentação de código: 2/3 pontos 
Há inconsistência na documentação do construtor do produto. Documentação de métodos deve ser informativa. Por exemplo, "Se a margem de lucro fornecida for inválida, uma exceção IllegalArgumentException será lançada.". O que é uma margem inválida?
	
### Testes (quantidade e qualidade): 3/4 pontos 
Extremamente (desnecessariamente) complicados para o problema, dada a superestrutura utilizada. [Veja a diferença entre estes dois testes](/docs/keepItSimple3.PNG) que fazem a mesma coisa (teste de cadastro no produto). Por outro lado, coisas básicas faltando ([veja meu teste na linha 58.](/codigo/src/test/java/EstoqueTest.java) )
	- produto: 2/2 pontos
	- estoque: 1/2 pontos

---

## Alunos integrantes da equipe

* Fernando Couto
* Gabriel Augusto
* Rafael Pierre
* Tito Li An Chen
* Vinicius Lima

## Professores responsáveis

* João Caram

