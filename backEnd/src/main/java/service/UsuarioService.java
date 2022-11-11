package service;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {
	
	private UsuarioDAO UsuarioDAO = new UsuarioDAO();	
	
	public UsuarioService() {
		UsuarioDAO = new UsuarioDAO();
		UsuarioDAO.conectar();
	}	
	
	public Object add(Request request, Response response) {
		String id = request.queryParams("id");
		int idUsuario = Integer.parseInt(id);
		String nome = request.queryParams("nome").toString();
		String username = request.queryParams("username").toString();
		String email = request.queryParams("email").toString();
		String senha = request.queryParams("senha").toString();
		
		response.header("Access-Control-Allow-Origin", "*");
		
		Usuario usuario = new Usuario(idUsuario, nome, username, email, senha);		

		UsuarioDAO.add(usuario);

		response.status(201); // 201 Created
		return 0;
	}

	public String login(Request request, Response response) {
		response.header("Content-Type", "application/json");
		response.header("Access-Control-Allow-Origin", "*");
		response.header("Content-Encoding", "UTF-8");
		return "\"ERRO AO REALIZAR O LOGIN\"";
		//return "{\"name\":\"camila\"}";
		/*String body = request.body().toString();
		body = body.replaceAll("username=", "");
		body = body.replaceAll("senha=", "");
		body = body.replaceAll("%40", "@");
		
		String login[] = body.split("&");
		
		Usuario user = UsuarioDAO.login(login[0], login[1]);
		if(user != null) {
			response.header("Access-Control-Allow-Origin", "*");
		    response.header("Content-Type", "application/");
		    response.header("Content-Encoding", "UTF-8");
		    String resultado = "{\"id\":" + user.getid() + ",\"email\":\"" + user.getEmail() + "\",\"nome\":\"" + user.getNome() + "\",\"username\":\"" + user.getusername() + "\"}";
		    return resultado.toString();
		}
		else {
			response.header("Access-Control-Allow-Origin", "*");
		    response.header("Content-Type", "application/");
		    response.header("Content-Encoding", "UTF-8");
		    String res = "null";
		    return res;
		}*/
	}

	
}