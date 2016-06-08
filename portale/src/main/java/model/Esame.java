package model;

public class Esame {
	private String nome;
	private String descrizione;
	private int cfu;
	private Long id;
	
	
	public Esame() {
		super();
	}

	public Esame(String nome, String descrizione, int cfu){
		this.nome = nome;
		this.descrizione = descrizione;
		this.cfu = cfu;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}

	Esame getEsame() {
		return this;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode()*this.cfu;
	}

	@Override
	public boolean equals(Object obj) {
		Esame other = (Esame)obj;
		return this.nome.equals(other.getNome())&&(this.cfu==other.getCfu());
	}
	
	public String toString()	{
		String s = "";
		s += "<tr><td>" +this.nome+ "</td>";
		s += "<td>" +this.descrizione+ "</td>";
		s += "<td>" +this.cfu+ "</td></tr>";
		return s;
	}
}
