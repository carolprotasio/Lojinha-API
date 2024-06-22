package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.componentePojo.ComponentePojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produtos - Valores Limites")
public class ProdutoValorTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.usuarioAdmin())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                    .path("data.token");

    }

    @Test
    @DisplayName("Valor Limite - Validar que valor 0(zero) do produto não é permitido")
    public void testValidarValorLimite( ){

            given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumcomValorDiferente(0))

            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }

    @Test
    @DisplayName("Valor Limite - Validar que valor maior que sete mil do produto não é permitido")
    public void testValidarValorMaiorSeteMil(){

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumcomValorDiferente(7001.00))


        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);

    }
    @Test
    @DisplayName("Valor Limite - Validar que valor 0.01 do produto é permitido")
    public void testValidarValorMaiorQZero(){

        List<String> cores = new ArrayList<>();
        cores.add("amarelo");
        cores.add("verde");

        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo primeiroComponente = new ComponentePojo();
        primeiroComponente.setComponenteNome("controle");
        primeiroComponente.setComponenteQuantidade(1);
        componentes.add(primeiroComponente);



        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProduto("teste 0.01", 0.01, cores, " ", componentes))


        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("message", equalTo("Produto adicionado com sucesso"))
                    .statusCode(201);
    }

    @Test
    @DisplayName("Valor Limite - Validar que valor 7000 do produto é permitido")
    public void testValidarValorDeSeteMil(){

        List<String> cores = new ArrayList<>();
        cores.add("amarelo");
        cores.add("verde");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo primeiroComponente = new ComponentePojo();
        primeiroComponente.setComponenteNome("controle");
        primeiroComponente.setComponenteQuantidade(1);

        componentes.add(primeiroComponente);


        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProduto("teste 0.01", 7000, cores, " ", componentes))


        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                    .body("message", equalTo("Produto adicionado com sucesso"))
                    .statusCode(201);

    }

}
