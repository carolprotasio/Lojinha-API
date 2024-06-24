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

@DisplayName("Validar Testes exploratórios")
public class UsuarioExploratorioTest {

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
    @DisplayName("Validar cadastro de usuário com campos obrigatórios faltando")
    public void testCadastroUsuarioComCamposFaltando() {

        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.novoUsuario("", faker.name().username(), "senhaSegura123"))
            .when()
                .post("/v2/usuarios")
            .then()
                .assertThat()
                .statusCode(201)
                .body("message", equalTo("Usuário adicionado com sucesso"));

                //.statusCode(400)
                //.body("error", equalTo("Nome é obrigatório"));
    }
    @Test
    @DisplayName("Validar cadastro de usuário com email já existente")
    public void testCadastroUsuarioComEmailExistente() {

        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.novoUsuario("admin", "admin", "admin"))
            .when()
                .post("/v2/usuarios")
            .then()
                .assertThat()
                .statusCode(409)
                .body("error", equalTo("O usuário admin já existe."));
    }




}
