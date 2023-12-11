package principalDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Conexao;
import principal.Viagem;

public class ViagemDAO {
	private Connection conexao;
	AgenteViagensDAO agenteViagensDAO = new AgenteViagensDAO();
	ClienteDAO clienteDAO = new ClienteDAO();

	public ViagemDAO() {
		try {
			conexao = Conexao.conectar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void criarViagem(Viagem viagem) {
		String sql = "INSERT INTO viagens (agente_id, cliente_id, data_viagem) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, viagem.getAgenteViagens().getId());
			stmt.setInt(2, viagem.getCliente().getId());
			stmt.setTimestamp(3, new java.sql.Timestamp(viagem.getDataViagem().getTime()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Viagem buscarViagem(int id) {
		Viagem viagem = null;
		String sql = "SELECT * FROM viagens WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				viagem = new Viagem();
				viagem.setId(resultado.getInt("id"));
				viagem.setAgenteViagens(agenteViagensDAO.buscarAgenteViagens(resultado.getInt("agente_id")));
				viagem.setCliente(clienteDAO.buscarCliente(resultado.getInt("cliente_id")));
				viagem.setDataViagem(resultado.getTimestamp("data_viagem"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return viagem;
	}

	public List<Viagem> listarViagens() {
		List<Viagem> viagens = new ArrayList<>();
		String sql = "SELECT * FROM viagens";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				Viagem viagem = new Viagem();
				viagem.setId(resultado.getInt("id"));
				viagem.setAgenteViagens(agenteViagensDAO.buscarAgenteViagens(resultado.getInt("agente_id")));
				viagem.setCliente(clienteDAO.buscarCliente(resultado.getInt("cliente_id")));
				viagem.setDataViagem(resultado.getTimestamp("data_viagem"));
				viagens.add(viagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return viagens;
	}

	public void atualizarViagem(Viagem viagem) {
		String sql = "UPDATE viagens SET agente_id = ?, cliente_id = ?, data_viagem = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, viagem.getAgenteViagens().getId());
			stmt.setInt(2, viagem.getCliente().getId());
			stmt.setTimestamp(3, new java.sql.Timestamp(viagem.getDataViagem().getTime()));
			stmt.setInt(4, viagem.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluirViagem(int id) {
		String sql = "DELETE FROM viagens WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fecharConexao() {
		try {
			if (conexao != null && !conexao.isClosed()) {
				conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
