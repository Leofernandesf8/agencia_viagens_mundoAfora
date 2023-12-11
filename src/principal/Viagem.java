package principal;

import java.util.Date;

public class Viagem {
	
	private int id;
	private AgenteViagens agenteViagens;
	private Cliente cliente;
	private Date dataViagem;

	public Viagem(AgenteViagens agenteViagens, Cliente cliente, Date dataViagem) {
		super();
		this.agenteViagens = agenteViagens;
		this.cliente = cliente;
		this.dataViagem = dataViagem;
	}

	public Viagem() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AgenteViagens getAgenteViagens() {
		return agenteViagens;
	}

	public void setAgenteViagens(AgenteViagens agenteViagens) {
		this.agenteViagens = agenteViagens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataViagem() {
		return dataViagem;
	}

	public void setDataViagem(Date dataViagem) {
		this.dataViagem = dataViagem;
	}

}
