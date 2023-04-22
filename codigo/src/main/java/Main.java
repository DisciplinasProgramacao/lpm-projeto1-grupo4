import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Scanner scanner = new Scanner(System.in);

        String opcao;
        while (true) {
            System.out.println("Bem vindo ao sistema de gestão de produtos, o que deseja fazer?");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Adicionar estoque a um produto");
            System.out.println("3 - Vender produto");
            System.out.println("4 - Gerar relatório de vendas do produto");
            System.out.println("5 - Sair");
            opcao = scanner.nextLine();
            //adicionado por João para poder executar.
            // estoque.cadastrarProduto(new Produto("Bala", "Bala", 100d, 0.50, 100, 50));
            // estoque.cadastrarProduto(new Produto("Mate", "Mate", 200d, 0.50, 100, 90));
           
            switch (opcao) {
                case "1":
                    System.out.println("\nCadastro de Produto");
                    System.out.println("Digite o nome do produto:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite a descrição do produto:");
                    String descricao = scanner.nextLine();
                    System.out.println("Digite o preço de custo do produto:");
                    double precoCusto = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Digite a margem de lucro do produto:");
                    double margemDeLucro = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Digite a quantidade do produto:");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite a quantidade mínima do produto:");
                    int quantidadeMinima = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Produto produto = new Produto(nome, descricao, precoCusto, margemDeLucro, quantidade, quantidadeMinima);
                        estoque.cadastrarProduto(produto);
                        System.out.println("\nProduto cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Não foi possível cadastrar um produto devio ao erro : " +  e.getMessage());
                    }


                    break;
                case "2":
                    System.out.println("\nAdicionar estoque a um produto");
                    System.out.println("Digite o nome do produto:");
                    nome = scanner.nextLine();
                    System.out.println("Digite a quantidade a ser adicionada:");
                    quantidade = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        estoque.adicionarEstoqueNoProduto(nome, quantidade);
                        System.out.println("\nEstoque adicionado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Não foi possível adicionar o estoque no produto devio ao erro : " +  e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("\nVender produto");
                    System.out.println("Digite o nome do produto:");
                    nome = scanner.nextLine();
                    System.out.println("Digite a quantidade a ser vendida:");
                    quantidade = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        estoque.venderProduto(nome, quantidade);
                        System.out.println("\nProduto vendido com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Não foi possível vender o produto devio ao erro : " +  e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("\nGerar relatório de vendas do produto");
                    System.out.println("Digite o nome do produto que deseja gerar o relatorio de vendas");
                    nome = scanner.nextLine();
                    try {
                        estoque.imprimirRelatorioVendas(nome);
                    }catch (IllegalArgumentException e) {
                        System.err.println("Não foi possível gerar o relatório do produto devio ao erro : " +  e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println("\nAté mais!");
                    System.exit(0);
                default:
                    System.out.println("\nOpção inválida!");
                    break;

            }
        }
    }

}
