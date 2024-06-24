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
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("SQL Injection - Testar vulnerabilidade de injeção de SQL no modulo produto")
public class ProdutoInjecaoSqlTest {
    private String token;
    private int produtoId;

    @BeforeEach
    public void beforeEach() {
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
                .body(ProdutoDataFactory.criarProduto("Samsung Tablet", 4000.99, cores, "", componentes))
            .when()
                .post("/v2/produtos")
            .then()
                .extract()
                .path("data.produtoId");
    }

    @Test
    @DisplayName("GET - Testar SQL Injection no endpoint de busca de produto por ID")
    public void testarSQLInjectionBuscarProdutoPorId() {

        String produtoId = "1'; DROP TABLE produtos; --";

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
            .when()
                .get("/v2/produtos/" + produtoId)
            .then()
                //.log().all()
                .assertThat()
                .statusCode(anyOf(is(404), is(500)));
    }

    @Test
    @DisplayName("GET - Testar Injeção de SQL em strings")
    public void testarSQLInjectionStrings() {

        String payload = "\" OR \"\"=\"";

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
            .when()
                .get("/v2/produtos/" + payload)
            .then()
                //.log().all()
                .assertThat()
                .statusCode(anyOf(is(404), is(500)));
    }

    @Test
    @DisplayName("GET - Testar Injeção de SQL em parâmetros numéricos")
    public void testarSQLInjectionParametrosNumericos() {

        String payload = "1 OR 1=1; --";

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .when()
                .get("/v2/produtos/" + payload)
                .then()
                //.log().all()
                .assertThat()
                .statusCode(anyOf(is(404), is(500)));
    }
    @Test
    @DisplayName("GET - Testar SQL Injection em outro endpoint")
    public void testarOutroSQLInjection() {
        String payload = "' OR 1=1; --";

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .when()
                .get("/v2/outro-endpoint/" + payload)
                .then()
                //.log().all()
                .assertThat()
                .statusCode(anyOf(is(404), is(500)));
    }

}
