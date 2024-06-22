package modulos.usuario;

import com.github.javafaker.Faker;
import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import pojo.usuarioPojo.UsuarioPojo;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Validar os verbos HTTP do usuário - CRUD")
public class UsuarioTest {
    private String token;
    private String usuarioId;
    private Faker faker;

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

        faker = new Faker();

        this.usuarioId =   given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.novoUsuario(faker.name().firstName(), faker.name().username(), "123456"))
            .when()
                .post("/v2/usuarios")
            .then()
                .extract()
                .path("data.usuarioID");
    }
    @Test
    @DisplayName("POST - Validar obtenção do token")
    public void testarObterToken() {

        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.usuarioAdmin())
        .when()
                .post("/v2/login")
        .then()
                .assertThat()
                .body("data.token", equalTo(this.token));
    }

    @Test
    @DisplayName("POST - Validar adicionar novo usuário")
    public void testarAdicionarUsuario() {


        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.novoUsuario(faker.name().firstName(), faker.name().username(), "123456"))
        .when()
                .post("/v2/usuarios")
        .then()
                .assertThat()
                .body("message", equalTo("Usuário adicionado com sucesso"))
                .statusCode(201);

    }
    @Test
    @DisplayName("DELETE - Limpar todos os dados do usuário")
    public void testarLimparTodosOsDadosdoUsuario() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
        .when()
                .delete("/v2/dados")
        .then()
                .assertThat()
                .statusCode(204);
    }

}
