package entities;

public class Offre {
	  private int id;
	    private double pourcentage;
	    private double prix;
	    private String nom;
	    private String type;
	    

	    public Offre(double pourcentage, double prix,String nom,String type) {
	        this.pourcentage = pourcentage;
	        this.prix = prix;
	        this.nom = nom;
	        this.type = type;
	        
	    }

	    public Offre(int id, double pourcentage, double prix,String nom,String type) {
	        this.id = id;
	        this.pourcentage = pourcentage;
	        this.prix = prix;
	        this.nom = nom;
	        this.type = type;
	    }

 
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public double getPourcentage() {
	        return pourcentage;
	    }

	    public void setPourcentage(double pourcentage) {
	        this.pourcentage = pourcentage;
	    }

		public double getPrix() {
			return prix;
		}

		public void setPrix(double prix) {
			this.prix = prix;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return "Offre [id=" + id + ", pourcentage=" + pourcentage + ", prix=" + prix + ", nom=" + nom + ", type="
					+ type + "]";
		}
	    
	    

	
}
