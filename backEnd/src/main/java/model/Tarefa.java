package model;

public class Tarefa {
	private int idTarefa;
	private int idUsuario;
	String descricao;
	String categoria;
	String dataInicio;
	String dataFim;
	String complete; 
	
	public Tarefa() {
		idTarefa = -1;
		idUsuario = 0;
		descricao = "";
		categoria = "";
		dataInicio = "";
		dataFim = "";
		complete = "";
	}

	public Tarefa(int idTarefa, int idUsuario, String descricao, String categoria, String dataInicio, String dataFim,
	String complete) {
		setIdTarefa(idTarefa);
		setIdUsuario(idUsuario);
		setDescricao(descricao);
		setCategoria(categoria);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setComplete(complete);
	}		
	
	public int getIDTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}
	
	public int getIDUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}
	
	public String getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}	
}