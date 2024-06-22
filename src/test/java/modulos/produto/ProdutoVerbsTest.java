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

@DisplayName("Validar os verbos HTTP do produto -CRUD")
public class ProdutoVerbsTest {
    private String token;
    private int produtoId;

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


        List<String> cores = new ArrayList<>();
        cores.add("cinza");
        cores.add("branco");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo primeiroComponente = new ComponentePojo();
        primeiroComponente.setComponenteNome("Carregador");
        primeiroComponente.setComponenteQuantidade(1);

        componentes.add(primeiroComponente);

        this.produtoId = given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProduto("Samsung Tablet", 4000.99, cores, "", componentes ))
            .when()
                .post("/v2/produtos")
            .then()
                .extract()
                .path("data.produtoId");

    }
    @Test
    @DisplayName("POST - Validar adicionar novo Produto")
    public void testarAdicionarNovoProduto() {

        List<String> cores = new ArrayList<>();
        cores.add("cinza");
        cores.add("branco");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo primeiroComponente = new ComponentePojo();
        primeiroComponente.setComponenteNome("Carregador");
        primeiroComponente.setComponenteQuantidade(1);

        componentes.add(primeiroComponente);

         given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProduto("Samsung Tablet", 4000.99, cores, "", componentes ))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat()
                 .body("message", equalTo("Produto adicionado com sucesso"))
                 .statusCode(201);
    }
    @Test
    @DisplayName("GET - Validar busca de um dos produtos dos usuário")
    public void testarBuscarUmProdutoUsuario() {

         given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumcomValorDiferente(100))
            .when()
                .get("/v2/produtos/" + this.produtoId)
            .then()
                .assertThat()
                 .body("message", equalTo("Detalhando dados do produto"))
                 .statusCode(200);
    }

    @Test
    @DisplayName("GET - Validar busca de todos os produtos do usuário")
    public void testarBuscarProdutosUsuario() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumcomValorDiferente(100))
        .when()
                .get("/v2/produtos")
        .then()
                .assertThat()
                .statusCode(200);

    }
    @Test
    @DisplayName("PUT - Validar alterar informações de um produto")
    public void testarAlterarInformacaoProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumcomValorDiferente(3500.00))
        .when()
                .put("/v2/produtos/" + this.produtoId)
        .then()
                .assertThat()
                .body("message", equalTo("Produto alterado com sucesso"))
                .statusCode(200);
    }
    @Test
    @DisplayName("DELETE - Validar remover um produto")
    public void testarRemoverUmProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
        .when()
                .delete("/v2/produtos/" + this.produtoId)
        .then()
                .assertThat()
                .statusCode(204);
    }

}
