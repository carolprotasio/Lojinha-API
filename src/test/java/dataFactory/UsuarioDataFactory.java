package dataFactory;

import pojo.usuarioPojo.UsuarioPojo;

public class UsuarioDataFactory {

    public static UsuarioPojo usuarioAdmin(){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha("admin");

        return usuario;
    }

    public static UsuarioPojo novoUsuario(String nome, String usuarioLogin, String senha ){
        UsuarioPojo novoUsuario = new UsuarioPojo();
        novoUsuario.setUsuarioNome(nome);
        novoUsuario.setUsuarioLogin(usuarioLogin);
        novoUsuario.setUsuarioSenha(senha);

        return novoUsuario;
    }

}
