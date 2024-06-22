package modulos.componentes;

import com.github.javafaker.Faker;
import dataFactory.ComponenteDataFactory;
import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.componentePojo.ComponentePojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Validar os verbos HTTP dos Componentes - CRUD")
public class ComponenteTest {
    private String token;
    private int produtoId;
    private int componenteId;

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

        this.produtoId = given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumcomValorDiferente(100))
            .when()
                .post("/v2/produtos")
            .then()
                .extract()
                .path("data.produtoId");


        this.componenteId = given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ComponenteDataFactory.adicionarComponente("Test", 1))
            .when()
                .post("/v2/produtos/" + this.produtoId + "/componentes")
            .then()
                .extract()
                 .path("data.componenteId");
    }
    @Test
    @DisplayName("POST - Validar adicionar novo componente ao produto")
    public void testAdicionarComponente() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ComponenteDataFactory.adicionarComponente("Test", 1))
        .when()
                .post("/v2/produtos/" + this.produtoId + "/componentes")
        .then()
                .assertThat()
                .body("message", equalTo("Componente de produto adicionado com sucesso"))
                .statusCode(201);
    }

    @Test
    @DisplayName("GET - Validar buscar dados dos componentes de um produto")
    public void testBuscarDadosComponentes() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
            .when()
                .get("/v2/produtos/" + this.produtoId + "/componentes")
            .then()
                .assertThat()
                    .body("message", equalTo("Listagem de componentes de produto realizada com sucesso"))
                    .statusCode(200);
    }
    @Test
    @DisplayName("GET - Validar buscar UM componente de um produto")
    public void testBuscarUmComponente() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
            .when()
                .get("/v2/produtos/" + this.produtoId + "/componentes/" + this.componenteId)
            .then()
                .assertThat()
                .body("message", equalTo("Detalhando dados do componente de produto"))
                .statusCode(200);
    }

    @Test
    @DisplayName("PUT - Validar alterar informações de um componente de produto")
    public void testAlterarInformacaoProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ComponenteDataFactory.adicionarComponente("produtoAlterado", 2))
            .when()
                .put("/v2/produtos/" + this.produtoId + "/componentes/" + this.componenteId)
                .then()
                .assertThat()
                .body("message", equalTo("Componente de produto alterado com sucesso"))
                .statusCode(200);
    }

    @Test
    @DisplayName("DELETE - Validar remover um componente do produto")
    public void testRemoverUmComponenteProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
            .when()
                .delete("/v2/produtos/" + this.produtoId + "/componentes/" + this.componenteId)
            .then()
                .assertThat()
                    .statusCode(204);
    }

}
