package dao;

import model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;

public class UsuarioDAO extends DAO {	
private Connection conexao;
	
	public int getMaxIdUsuario() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id FROM usuario");
			rs.absolute(-1);
			maxId = rs.getInt("id");
			System.out.println(maxId);
			st.close();
			if (maxId >= 0) {
				maxId++;
				return maxId;
			}
			else {
				return maxId;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return maxId;
	}
	
	public int getMaxIdPerfil() {
		int maxId = 0;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT id FROM perfil");
			rs.absolute(-1);
			maxId = rs.getInt("id");
			System.out.println(maxId);
			st.close();
			if (maxId >= 0) {
				maxId++;
				return maxId;
			}
			else {
				return maxId;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return maxId;
	}
	
	public UsuarioDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "guruDaOrganizacao";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "postgres";
		String password = "12345";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	public void add(Usuario u) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO usuario (id, email, senha, nome, username) "
				       + "VALUES(" + u.getid() + ", '" + u.getEmail() + "', '" + u.getSenha() 
				       + "', '" + u.getNome() + "', '" + u.getusername() + "');\n");
			st.close();
		} catch (SQLException e) {  
			throw new RuntimeException(e);
		}
	}

	public Usuario login(String email, String senha) {
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE email = '" + email + "' AND senha = '" + senha + "';");
			rs.beforeFirst();
			if (rs.next()) {
				Usuario user = new Usuario(rs.getInt("id"), rs.getString("email"), rs.getString("senha"), rs.getString("nome"), rs.getString("username"));
				return user;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
}