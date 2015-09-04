package controller;

import br.com.caelum.brutauth.auth.annotations.AccessLevel;
import br.com.caelum.brutauth.auth.annotations.SimpleBrutauthRules;
import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import database.UsuarioDAO;
import java.sql.SQLException;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import models.Usuario;
import sessao.UsuarioSessao;

@Controller
@Any
@Default
public class UsuarioController {

    @Inject
    private Result result;

    @Inject
    private UsuarioSessao sessao;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Path({"/", "/usuario/index", "/usuario/tela_login"})
    public void tela_login() {
    }

    public void login(Usuario usuario) throws SQLException {
        boolean resultado = this.usuarioDAO.autentica(usuario.getEmail(), usuario.getSenha());
        if (resultado) {            
            this.sessao.setUser(usuario);
            this.result.redirectTo(IndexController.class).tela_baixar();
        } else {
            this.result.include("mensagem", "Email e Senha Incorretos");
            this.result.redirectTo(this).tela_login();
        }
    }

    public void mensagem() {
    }

    @Path({"/usuario/logout", "/usuario/logout/"})
    public void logout() {
        this.sessao.logout();
        this.result.redirectTo(this).tela_login();
       
    }
}