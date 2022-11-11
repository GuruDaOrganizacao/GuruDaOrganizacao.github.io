package dao;

import model.Tarefa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class TarefaDAO extends DAO {	
	public TarefaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Tarefa produto) {
		boolean status = false;
		try {
			String sql = "INSERT INTO produto (descricao, categoria, dataInicio, dataFim, complete) "
		               + "VALUES ('" + produto.getDescricao() + "', "
		               + produto.getCategoria() + ", " + produto.getComplete() + ", " + produto.getDataInicio() + ""
		               + produto.getDataFim() + "";
					  			
			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(produto.getDataInicio()));
			st.setDate(2, Date.valueOf(produto.getDataFim()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Tarefa get(int idTarefa) {
		Tarefa produto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id="+idTarefa;
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 produto = new Tarefa(rs.getInt("idTarefa"), idTarefa, rs.getString("descricao"), rs.getString("categoria"),
	        			              rs.getString("dataInicio"), rs.getString("dataFim"), rs.getString("complete"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produto;
	}
	
	
	public List<Tarefa> get() {
		return get("");
	}

	
	public List<Tarefa> getOrderByID() {
		return get("idTarefa");		
	}
	
	
	public List<Tarefa> getOrderByDescricao() {
		return get("descricao");		
	}
		
	
	private List<Tarefa> get(String orderBy) {
		List<Tarefa> produtos = new ArrayList<Tarefa>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Tarefa p = new Tarefa(rs.getInt("idTarefa"), 0, rs.getString("descricao"), rs.getString("categoria"),
			              rs.getString("dataInicio"), rs.getString("dataFim"), rs.getString("complete"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}
	
	
	public boolean update(Tarefa produto) {
		boolean status = false;
		try {  
			String sql = "UPDATE produto SET descricao = '" + produto.getDescricao() + "', "
					   + "categoria = " + produto.getCategoria() + ", " 
					   + "dataInicio = ? WHERE id = " + produto.getDataInicio()
					   + "dataFim = ? WHERE id = " + produto.getDataFim()
					   + "complete = " + produto.getComplete() + ", " ;

			PreparedStatement st = conexao.prepareStatement(sql);
		    st.setTimestamp(1, Timestamp.valueOf(produto.getDataInicio()));
			st.setDate(2, Date.valueOf(produto.getDataFim()));
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(int idTarefa) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM produto WHERE id = " + idTarefa);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}