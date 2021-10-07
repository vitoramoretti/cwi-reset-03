
public class Registradora {

    public static void main(String[] args) {

        System.out.println("##### Bug 1 ##### FIXED");
        // criação do horario de funcionamento estava errado, gerando sempre um horario menor que 16.
        primeiroBug();

        System.out.println("##### Bug 2 ##### FIXED");
        // tipo de dado em quantidade estava errado
        segundoBug();

        System.out.println("##### Bug 3 ##### FIXED");
        // palavra café estava com acento no item
        terceiroBug();

        System.out.println("##### Bug 4 ##### FIXED");
        // o item sanduiche estava com nome de sanduba
        quartoBug();

        System.out.println("##### Bug 5 ##### FIXED");
        // foi resolvido com a resolução do tipo int para double
        quintoBug();

        System.out.println("##### Bug 6 #####");
        sextoBug();
    }

    private static double registrarItem(String item, int quantidade) {

        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);


            if (QuantidadeMinimaItem.precisaReposicao(item)) {
                if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                    if (!DataProjeto.cozinhaEmFuncionamento()) {
                        System.out.println("Cozinha fechada!");
                        System.out.println("Reposição de " + item + " indisponível.");
                        System.out.println("Quantidade restante em estoque: " + ItensPorQuantidade.inventory(item));
                        precoItem = 0;
                    }
                    if (DataProjeto.cozinhaEmFuncionamento()) {
                        ReposicaoCozinha.reporItem(item);
                        System.out.println("Fazendo pedido para cozinha...");
                    }
                }
                if ("leite".equals(item) || "cafe".equals(item)) {
                    ReposicaoFornecedor.reporItem(item);
                }


        }

        ItensPorQuantidade.subtractInventory(item, quantidade);
//        System.out.println("Quantidade em estoque: " + ItensPorQuantidade.inventory(item)); // For testing

        return precoItem;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

}
