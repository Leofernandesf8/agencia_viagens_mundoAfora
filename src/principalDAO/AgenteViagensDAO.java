package principalDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Conexao;
import principal.AgenteViagens;

public class AgenteViagensDAO {
	private Connection conexao;

	public AgenteViagensDAO() {
        try {
            conexao = Conexao.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void criaAgenteViagens(AgenteViagens agente) {
		String sql = "INSERT INTO Agentes (nome, areaEspecializacao) VALUES (?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, agente.getNome());
			stmt.setString(2, agente.getAreaEspecializacao());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AgenteViagens buscarAgenteViagens(int id) {
		AgenteViagens agente = null;
		String sql = "SELECT * FROM Agentes WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				agente = new AgenteViagens();
				agente.setId(resultado.getInt("id"));
				agente.setNome(resultado.getString("nome"));
				agente.setAreaEspecializacao(resultado.getString("AreaEspecializacao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agente;
	}

	public List<AgenteViagens> listarAgentes() {
		List<AgenteViagens> agentes = new ArrayList<>();
		String sql = "SELECT * FROM Agentes";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				AgenteViagens agente = new AgenteViagens();
				agente.setId(resultado.getInt("id"));
				agente.setNome(resultado.getString("nome"));
				agente.setAreaEspecializacao(resultado.getString("AreaEspecializacao"));
				agentes.add(agente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agentes;
	}

	public void atualizarAgenteViagens(AgenteViagens agente) {
		String sql = "UPDATE Agentes SET nome = ?, AreaEspecializacao = ? WHERE id = ?";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, agente.getNome());
			stmt.setString(2, agente.getAreaEspecializacao());
			stmt.setInt(3, agente.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluirAgenteViagens(int id) {
		String sql = "DELETE FROM Agentes WHERE id = ?";
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
