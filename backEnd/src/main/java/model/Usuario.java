package model;

import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private String username;
	private String email;
	private String senha;
	
	public Usuario() {
		this.id = -1;
		this.email = "";
		this.senha = "";
		this.nome = "";
		this.username = "";
	}
	
	public Usuario(int id, String nome, String username, String email, String senha) {
		setid(id);
		setEmail(email);
		setNome(nome);
		setusername(username);
		setSenha(senha);
	}


	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}
	
}
