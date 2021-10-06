public class RelacaoPesoPreco {

    public static double retornaPrecoProduto(String item, double qtd) { // Troquei tipo int para double
        double precoTotal = 0;

        if ("pao".equals(item)) {
            precoTotal = 12.75 * (qtd * 60 / 1000);
            ItensPorQuantidade.pao -= (qtd * 60);
        }

        if ("torta".equals(item)) {
            precoTotal = 96.00 * (qtd / 16);
            ItensPorQuantidade.torta -= qtd;

        }

        if ("leite".equals(item)) {
            precoTotal = 4.48 * qtd;
            ItensPorQuantidade.leite -= qtd;
        }

        if ("cafe".equals(item)) {
            precoTotal = 9.56 * qtd;
            ItensPorQuantidade.cafe -= qtd;
        }

        if ("sanduiche".equals(item)) {
            precoTotal = 4.5 * qtd;
        }

        return precoTotal;
    }
}
