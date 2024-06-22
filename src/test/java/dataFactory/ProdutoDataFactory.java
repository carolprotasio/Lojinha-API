package dataFactory;

import pojo.componentePojo.ComponentePojo;
import pojo.produtoPojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {

    public static ProdutoPojo criarProdutoComumcomValorDiferente(double valor){
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("PlayStation API");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("amarelo");
        cores.add("verde");

        produto.setProdutoCores(cores);
        produto.setProdutoMock("");

        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo primeiroComponente = new ComponentePojo();
        primeiroComponente.setComponenteNome("controle");
        primeiroComponente.setComponenteQuantidade(1);

        componentes.add(primeiroComponente);

        produto.setComponentes(componentes);

        return produto;

    }

    public static ProdutoPojo criarProduto(String nome, double valor, List<String> cores, String mock, List<ComponentePojo> componentes){
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome(nome);
        produto.setProdutoValor(valor);
        produto.setProdutoCores(cores);
        produto.setProdutoMock(mock);
        produto.setComponentes(componentes);

        return produto;

    }
}
