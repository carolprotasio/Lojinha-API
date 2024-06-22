package modulos.usuario;

import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("SQL Injection - Testar vulnerabilidade de injeção de SQL no modulo usuário")
public class UsarioInjecaoSqlTest {

    @BeforeEach
    public void beforeEach() {
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

    }
    @Test
    @DisplayName("Validar injeção de SQL no cadastro de usuário")
    public void testInjecaoSqlCadastroUsuario() {

        String sqlInjectionPayLoad = "' OR '1'='1";

        given()
            .contentType(ContentType.JSON)
            .body(UsuarioDataFactory.novoUsuario(sqlInjectionPayLoad, sqlInjectionPayLoad,"123456" ))
        .when()
                .post("/v2/usuarios")
        .then()
                .log().all()
                .assertThat()
                .statusCode(anyOf(is(400), is(500)));

    }
    @Test
    @DisplayName("Teste com Subconsultas - Validar injeção de SQL com subconsulta")
    public void testInjecaoSqlSubconsulta() {

        String sqlInjectionPayLoad = "' OR (SELECT COUNT(*) FROM usuarios) > 0 --";

        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.novoUsuario(sqlInjectionPayLoad, sqlInjectionPayLoad, "123456"))
            .when()
                .post("/v2/usuarios")
            .then()
                .log().all()
                .assertThat()
                .statusCode(anyOf(is(400), is(500)));
    }

    @Test
    @DisplayName("Teste com Funções SQL - Validar injeção de SQL com funções SQL")
    public void testInjecaoSqlFuncoes() {

        String sqlInjectionPayLoad = "' OR 1=1; DROP TABLE usuarios; --";

        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.novoUsuario(sqlInjectionPayLoad, sqlInjectionPayLoad, "123456"))
            .when()
                .post("/v2/usuarios")
            .then()
                .log().all()
                .assertThat()
                .statusCode(anyOf(is(400), is(500)));
    }
    @Test
    @DisplayName("Teste com União de Consultas - Validar injeção de SQL com UNION")
    public void testInjecaoSqlUnion() {
        String sqlInjectionPayLoad = "' UNION SELECT null, null, null --";
        given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.novoUsuario(sqlInjectionPayLoad, sqlInjectionPayLoad, "123456"))
                .when()
                .post("/v2/usuarios")
                .then()
                .log().all()
                .assertThat()
                .statusCode(anyOf(is(400), is(500)));
    }



}
