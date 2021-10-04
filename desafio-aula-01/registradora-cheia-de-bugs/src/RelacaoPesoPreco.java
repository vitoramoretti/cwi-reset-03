public class RelacaoPesoPreco {

    public static double retornaPrecoProduto(String item, double qtd) { // Troquei tipo int para double
        double precoTotal = 0;

        if ("pao".equals(item)) {
            precoTotal = 12.75 * (qtd * 60 / 1000);
            System.out.println("Preço do pão: R$ " + precoTotal); // Mensagem temporária
        }

        if ("torta".equals(item)) {
            precoTotal = 96.00 * (qtd / 16);
            System.out.println("Preço da torta: R$ " + precoTotal);
        }

        if ("leite".equals(item)) {
            precoTotal = 4.48 * qtd;
            System.out.println("Preço do leite: R$ " + precoTotal);
        }

        if ("café".equals(item)) {
            precoTotal = 9.56 * qtd;
            System.out.println("Preço do café: R$ " + precoTotal);
        }

        if ("sanduiche".equals(item)) {
            precoTotal = 4.5 * qtd;
            System.out.println("Preço do sanduiche R$ " + precoTotal);

        }

        return precoTotal;
    }
}
