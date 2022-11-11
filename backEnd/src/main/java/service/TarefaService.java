package service;

import dao.TarefaDAO;
import model.Tarefa;
import spark.Request;
import spark.Response;


public class TarefaService {
	private TarefaDAO tarefaDAO = new TarefaDAO();
	
	public Object insert(Request request, Response response) {
		int idTarefa = Integer.parseInt(request.params(":idTarefa"));
		int idUsuario = Integer.parseInt(request.params(":idTarefa"));
		
		String descricao = request.queryParams("descricao");
		String categoria = request.queryParams("categoria");
		String dataInicio = request.queryParams("dataInicio");
		String dataFim = request.queryParams("dataFim");
		String complete = request.queryParams("complete"); 
		
		Tarefa tarefa = new Tarefa(idTarefa, idUsuario, descricao, categoria, dataInicio, dataFim, complete);
		
		
		if(tarefaDAO.insert(tarefa) == true) {
            response.status(201); // 201 Created
		} else {
			response.status(404); // 404 Not found
		}
		return tarefa;
	}

	
	public Object get(Request request, Response response) {
		int idTarefa = Integer.parseInt(request.params(":idTarefa"));		
		Tarefa tarefa = (Tarefa) tarefaDAO.get(idTarefa);
		
		if (tarefa != null) {
			response.status(200); // success
        } else {
            response.status(404); // 404 Not found
        }
		return tarefa;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int idTarefa = Integer.parseInt(request.params(":idTarefa"));		
		Tarefa tarefa = (Tarefa) tarefaDAO.get(idTarefa);
		
		if (tarefa != null) {
			response.status(200); // success
        } else {
            response.status(404); // 404 Not found
        }
		return tarefa;

	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return orderBy;
	}			
	
	public Object update(Request request, Response response) {
        int idTarefa = Integer.parseInt(request.params(":idTarefa"));
		Tarefa tarefa = tarefaDAO.get(idTarefa);
        String resp = "";       

        if (tarefa != null) {
        	tarefa.setDescricao(request.queryParams("descricao"));    		
        	tarefa.setCategoria(request.queryParams("categoria"));
        	tarefa.setDataInicio(request.queryParams("dataInicio"));
        	tarefa.setDataFim(request.queryParams("dataFim"));
        	tarefa.setComplete(request.queryParams("complete"));
        	
        	tarefaDAO.update(tarefa);
        	response.status(200); // success
            resp = "Tarefa (ID " + tarefa.getIDTarefa() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Tarefa (ID \" + tarefa.getId() + \") não encontrado!";
        }
		return resp;
	}

	
	public Object delete(Request request, Response response) {
        int idTarefa = Integer.parseInt(request.params(":idTarefa"));
        Tarefa tarefa = tarefaDAO.get(idTarefa);
        String resp = "";       

        if (tarefa != null) {
            tarefaDAO.delete(idTarefa);
            response.status(200); // success
            resp = "Tarefa (" + idTarefa + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Tarefa (" + idTarefa + ") não encontrado!";
        }
		return resp;
	}
}