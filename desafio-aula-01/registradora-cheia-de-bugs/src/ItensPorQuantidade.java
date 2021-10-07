public class ItensPorQuantidade {

    static int pao = 3600;
    static int torta = 64;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static int inventory (String item) {
        int inStock = 0;

        if ("pao".equals(item)) {
            inStock = pao;
        }
        if ("torta".equals(item)) {
            inStock = torta;
        }
        if ("sanduiche".equals(item)) {
            inStock = sanduiche;
        }
        if ("leite".equals(item)) {
            inStock = leite;
        }
        if ("cafe".equals(item)) {
            inStock = cafe;
        }
        return inStock;
    }

    public static void subtractInventory (String item, int quantidade) {
        if ("pao".equals(item)) {
            pao = (quantidade < pao) ? pao - (quantidade * 60) : 0;
        }

        if ("torta".equals(item)) {
            torta = (quantidade < torta) ? torta - quantidade : 0;
        }

        if ("sanduiche".equals(item)) {
            sanduiche = (quantidade < sanduiche) ? sanduiche - quantidade : 0;
        }

        if ("leite".equals(item)) {
            leite = (quantidade < leite) ? leite - quantidade : 0;
        }

        if ("cafe".equals(item)) {
            cafe = (quantidade < cafe) ? cafe - quantidade : 0;
        }

    }

}
