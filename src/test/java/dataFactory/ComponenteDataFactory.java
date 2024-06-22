package dataFactory;

import pojo.componentePojo.ComponentePojo;

public class ComponenteDataFactory {

    public static ComponentePojo adicionarComponente(String nome, int qtd) {

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome(nome);
        componente.setComponenteQuantidade(qtd);

        return componente;
    }
}
