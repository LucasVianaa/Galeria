package controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.inject.Inject;
import sessao.UsuarioSessao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

@Controller
public class IndexController {

    @Inject
    private Result result;

    @Inject
    private UsuarioSessao sessao;

    private static final String DIR_DOWNLOAD = "/Users/Aluno/Desktop/vraptor4CRUD/src/main/webapp/WEB-INF/download/";

    /*  
     public void index() throws SQLException {
     //result.include("mensagem", "Novo projeto com VRaptor 4"); // enviar alguma coisa para a view - acessar pelo nome mensagem 
     sessao.setValor1("sodofjasidofjosidfjiosa");
     result.redirectTo(PessoaController.class).listar(); // redirecionar
     }*/
    // metodo do controller - sem @path - url fica (/index/tela_alterar)
    public void tela_baixar() {
        //sessao.setValor1("IGOR"); // alterando o valor da sessao        

        File folder = new File(DIR_DOWNLOAD);

        ArrayList<File> files = new ArrayList();

        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile() && !fileEntry.isHidden() && fileEntry.exists()) {
      
                files.add(fileEntry);
                //fileEntry.getAbsoluteFile();
                
                //    temp = fileEntry.getName();
                //  if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("txt")) {
                //       System.out.println("File= " + folder.getAbsolutePath() + "\\" + fileEntry.getName());
            }
        }
        //}
        result.include("files", files);
        //result.include("sessao", sessao.getValor1());
        //this.result.redirectTo(this).tela_baixar();
    }

    @Path("/index/baixar/{arquivo}")
    public File baixar(String arquivo) {
        return new File(DIR_DOWNLOAD + arquivo);
    }
    /*
       
    @Path("/index/obter/{arquivo}")
    public File obter(String arquivo) {
      //  String pastaImagens = "C:/download"; // configure isso num arquivo properties, por exemplo  
        return new File(DIR_DOWNLOAD, arquivo);
    }
    */
    @Path("/index/remover/{arquivo}")
    public void remover(String arquivo) {
        File file = new File(DIR_DOWNLOAD + arquivo);
        if (file.delete()) {
            result.include("mensagem", file.getName() + " foi deletado!");
        } else {
            result.include("mensagem", "Opção de remover o arquivo falhou.");
        }
        this.result.redirectTo(this).tela_baixar();
    }

    @Post
    @Path("/index/upload/")
    public void upload(UploadedFile file) throws FileNotFoundException, IOException {

        if (null != file) {

            // obtendo a extensao
            String extensao = file.getFileName().substring(file.getFileName().indexOf("."), file.getFileName().length());
            System.out.println(file.getContentType());

         // obtendo somente o nome - sem extensao
            //String nome = file.getFileName().substring(0, file.getFileName().indexOf("."));
        // em vez de pegar a extensao o melhor (por causa de invasoes) 
            // Eh pegar o file.getContentType() e testar ex: pdf == application/pdf 
            // E depois determinar a extensao
        // poderia gravar o arquivo no B.D
            //this.arquivoDAO.inserir(file.getFileName());
        // fazendo o upload com o nome verdadeiro do arquivo
            //File f = new File(DIR_DOWNLOAD + file.getFileName());
            // Nome aleatorio + extensao
            long time = System.currentTimeMillis();
            String novo_nome = String.format("%08x%05x", time / 1000, time);
            File f = new File(DIR_DOWNLOAD + novo_nome + extensao);

            // realizando o upload
            IOUtils.copyLarge(file.getFile(), new FileOutputStream(f));

            // mensagem
            result.include("mensagem", "O arquivo foi adicionado");

            // redirecionamento
            this.result.redirectTo(this).tela_baixar();

        } else {
            result.include("mensagem", "Nenhum arquivo foi selecionado...");

            // redirecionamento
            this.result.redirectTo(this).tela_upload();
        }

    }

    // somente para cair na tela de upload
    @Path({"/index/tela_upload/", "/index/tela_upload"})
    public void tela_upload() {
        //result.include("sessao",sessao.getValor1());

    }
    /*
     @Path("/index/retorno")
     public String retorno() {
     return "Retorno - OK";
     }*/

}
