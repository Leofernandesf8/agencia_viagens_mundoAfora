package principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import principalDAO.ViagemDAO;
import principalDAO.AgenteViagensDAO;
import principalDAO.ClienteDAO;

public class AgenciaDeVeiagensApp2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AgenteViagensDAO agenteViagensDAO = new AgenteViagensDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		ViagemDAO viagemDAO = new ViagemDAO();

		while (true) {
			System.out.println("\n**Gestão Agência De Viagens**");
			System.out.println("1. Cadastrar Agente Viagens");
			System.out.println("2. Listar Agente Viagens");
			System.out.println("3. Atualizar Agente Viagens");
			System.out.println("4. Excluir Agente Viagens");
			System.out.println("5. Cadastrar Cliente");
			System.out.println("6. Listar Cliente");
			System.out.println("7. Atualizar Cliente");
			System.out.println("8. Excluir Cliente");
			System.out.println("9. Cadastrar Viagem");
			System.out.println("10. Listar Viagem");
			System.out.println("11. Atualizar Viagem");
			System.out.println("12. Excluir Viagem");
			System.out.println("13. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				
				AgenteViagens agenteViagens = new AgenteViagens();
				System.out.print("Nome do Agente Viagens: ");
				scanner.nextLine(); 
				agenteViagens.setNome(scanner.nextLine());
				System.out.print("Area Especialização: ");
				agenteViagens.setAreaEspecializacao(scanner.nextLine());
				agenteViagensDAO.criaAgenteViagens(agenteViagens);
				System.out.println("Medico cadastrado com sucesso!");
				break;
			case 2:
				
				List<AgenteViagens> agenteViagem = agenteViagensDAO.listarAgentes();
				System.out.println("Lista de agenteViagem:");
				for (AgenteViagens agente : agenteViagem) {
					System.out.println(
							"ID: " + agente.getId() + ", Nome: " + agente.getNome() + ", Area Especializacao: " + agente.getAreaEspecializacao());
				}
				break;
			case 3:
				
				System.out.print("ID do AgenteViagens para atualização: ");
				int agenteViagensIdAtualizar = scanner.nextInt();
				AgenteViagens agenteViagensAtualizar = agenteViagensDAO.buscarAgenteViagens(agenteViagensIdAtualizar);
				if (agenteViagensAtualizar != null) {
					System.out.print("Novo Nome do Agente de Viagens: ");
					scanner.nextLine(); 
					agenteViagensAtualizar.setNome(scanner.nextLine());
					System.out.print("Nova Area de Epecialização: ");
					agenteViagensAtualizar.setAreaEspecializacao(scanner.nextLine());
					agenteViagensDAO.atualizarAgenteViagens(agenteViagensAtualizar);
					System.out.println("Agente atualizado com sucesso!");
				} else {
					System.out.println("Agente não encontrado.");
				}
				break;
			case 4:
				
				System.out.print("ID do Agente para exclusão: ");
				int agenteViagensIdExcluir = scanner.nextInt();
				AgenteViagens agenteViagensExcluir = agenteViagensDAO.buscarAgenteViagens(agenteViagensIdExcluir);
				if (agenteViagensExcluir != null) {
					agenteViagensDAO.excluirAgenteViagens(agenteViagensIdExcluir);
					System.out.println("Agente excluído com sucesso!");
				} else {
					System.out.println("Agente não encontrado.");
				}
				break;
			case 5:
				
				Cliente cliente = new Cliente();
				System.out.print("Nome do Cliente: ");
				scanner.nextLine(); 
				cliente.setNome(scanner.nextLine());
				System.out.print("Email: ");
				cliente.setEmail(scanner.nextLine());
				clienteDAO.criarCliente(cliente);
				System.out.println("Cliente cadastrado com sucesso!");
				break;
			case 6:
				
				List<Cliente> clientes = clienteDAO.listarCliente();
				System.out.println("Lista de Pacientes:");
				for (Cliente c : clientes) {
					System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", email: " + c.getEmail());
				}
				break;
			case 7:
				
				System.out.print("ID do Cliente para atualização: ");
				int clienteIdAtualizar = scanner.nextInt();
				Cliente clienteAtualizar = clienteDAO.buscarCliente(clienteIdAtualizar);
				if (clienteAtualizar != null) {
					System.out.print("Novo Nome do Cliente: ");
					scanner.nextLine(); 
					clienteAtualizar.setNome(scanner.nextLine());
					System.out.print("Novo Email: ");
					clienteAtualizar.setEmail(scanner.nextLine());
					clienteDAO.atualizarCliente(clienteAtualizar);
					System.out.println("Cliente atualizado com sucesso!");
				} else {
					System.out.println("Cliente não encontrado.");
				}
				break;
			case 8:
				
				System.out.print("ID do Cliente para exclusão: ");
				int clienteIdExcluir = scanner.nextInt();
				Cliente clienteExcluir = clienteDAO.buscarCliente(clienteIdExcluir);
				if (clienteExcluir != null) {
					clienteDAO.excluirCliente(clienteIdExcluir);
					System.out.println("Cliente excluído com sucesso!");
				} else {
					System.out.println("Cliente não encontrado.");
				}
				break;
			case 9:
				
				Viagem viagem = new Viagem();
				System.out.print("ID do Agente Viagens: ");
				int agenteViagensId = scanner.nextInt();
				AgenteViagens agenteViagensViagem = agenteViagensDAO.buscarAgenteViagens(agenteViagensId);
				if (agenteViagensViagem != null) {
					viagem.setAgenteViagens(agenteViagensViagem);
					System.out.print("ID do Cliente: ");
					int clienteId = scanner.nextInt();
					Cliente clienteViagem = clienteDAO.buscarCliente(clienteId);
					if (clienteViagem != null) {
						viagem.setCliente(clienteViagem);
						System.out.print("Data da Viagem (dd/mm/yyyy): ");
						String dataString = scanner.next();
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							Date dataViagem = sdf.parse(dataString);
							viagem.setDataViagem(dataViagem);
							viagemDAO.criarViagem(viagem);
							System.out.println("Viagem cadastrada com sucesso!");
						} catch (ParseException e) {
							System.out.println("Formato de data inválido. Use dd/mm/yyyy.");
						}
					} else {
						System.out.println("Cliente não encontrado.");
					}
				} else {
					System.out.println("Agente não encontrado.");
				}
				break;
			case 10:
				
				List<Viagem> viagens = viagemDAO.listarViagens();
				System.out.println("Lista de Viagens:");
				for (Viagem v : viagens) {
					System.out.println("ID: " + v.getId() + ", agente: " + v.getAgenteViagens().getNome() + ", Cliente: "
							+ v.getCliente().getNome() + ", Data: " + v.getDataViagem());
				}
				break;
			case 11:
				
				System.out.print("ID da Viagem para atualização: ");
				int viagemId = scanner.nextInt();
				Viagem viagemAtualizar = viagemDAO.buscarViagem(viagemId);
				if (viagemAtualizar != null) {
					System.out.print("Nova Data da Viagem (dd/mm/yyyy): ");
					String novaDataString = scanner.next();
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date novaDataViagem = sdf.parse(novaDataString);
						viagemAtualizar.setDataViagem(novaDataViagem);
						viagemDAO.atualizarViagem(viagemAtualizar);
						System.out.println("Viagem atualizada com sucesso!");
					} catch (ParseException e) {
						System.out.println("Formato de data inválido. Use dd/mm/yyyy.");
					}
				} else {
					System.out.println("Viagem não encontrada.");
				}
				break;
			case 12:
				
				System.out.print("ID da Viagem para exclusão: ");
				int viagemIdExcluir = scanner.nextInt();
				Viagem viagemExcluir = viagemDAO.buscarViagem(viagemIdExcluir);
				if (viagemExcluir != null) {
					viagemDAO.excluirViagem(viagemIdExcluir);
					System.out.println("Viagem excluída com sucesso!");
				} else {
					System.out.println("Viagem não encontrada.");
				}
				break;
			case 13:
				
				System.out.println("Saindo do sistema...");
				viagemDAO.fecharConexao();
				clienteDAO.fecharConexao();
				agenteViagensDAO.fecharConexao();
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Opção inválida. Tente novamente.");
				break;
			}
		}
	}
}
	
	

