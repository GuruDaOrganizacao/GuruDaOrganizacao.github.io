package app;

import static spark.Spark.*;

import service.TarefaService;
import service.UsuarioService;


public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	private static TarefaService tarefaService = new TarefaService();
	
    public static void main(String[] args) {
        port(6789);
        
        post("/cadastrar/usuario", (request, response) -> usuarioService.add(request, response));

        post("/login", (request, response) -> usuarioService.login(request, response));
     
        
        
        post("/tarefa/insert", (request, response) -> tarefaService.insert(request, response));

        get("/tarefa/:idTarefa", (request, response) -> tarefaService.get(request, response));
        
        get("/tarefa/list/:orderby", (request, response) -> tarefaService.getAll(request, response));

        get("/tarefa/update/:idTarefa", (request, response) -> tarefaService.getToUpdate(request, response));
        
        post("/tarefa/update/:idTarefa", (request, response) -> tarefaService.update(request, response));
           
        get("/tarefa/delete/:idTarefa", (request, response) -> tarefaService.delete(request, response));
        
    }
}