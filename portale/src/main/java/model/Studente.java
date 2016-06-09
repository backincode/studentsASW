package model;

import java.util.LinkedList;
import java.util.List;

public class Studente {
		private String nome, cognome;
		private int matricola;
		private List<Esame> esami;

		public Studente()	{
			this.esami = new LinkedList<Esame>();
		}
		
		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public int getMatricola() {
			return this.matricola;
		}

		public void setMatricola(int matricola) {
			this.matricola = matricola;
		}

		public List<Esame> getEsami() {
			return this.esami;
		}
		
		public boolean aggiungiEsame(Esame e)	{
			return this.esami.add(e);
		}
		
		
		public void setEsami(List<Esame> esami) {
			this.esami = esami;
		}
		@Override
		public int hashCode() {
			return this.nome.hashCode()*this.cognome.hashCode()*this.matricola;
		}
		@Override
		public boolean equals(Object obj) {
			Studente other = (Studente) obj;
			return (this.cognome.equals(other.cognome))&&(this.nome.equals(other.nome))&&(this.matricola==other.getMatricola());
		}
		
		public String toString()	{
			String s = "";
			s += this.matricola+ "\t";
			s += this.nome+ "\t";
			s += this.cognome+ "\n";
			return s;
		}

}
