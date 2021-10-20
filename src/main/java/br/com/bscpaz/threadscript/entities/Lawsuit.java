package br.com.bscpaz.threadscript.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Lawsuit.TABLE_NAME)
public class Lawsuit {
    
    public static final String TABLE_NAME = "tb_lawsuit";

    @Id
	@Column(name = "id_lawsuit", unique = true, nullable = false)
	private Integer idLawsuit;

	@Column(name = "num_lawsuit", nullable = false, length = 30)
	private String numberLawsuit;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_plaintiff", nullable = false)
	private Plaintiff plaintiff;

	@Column(name = "in_active", nullable = false)
	private Boolean isActive;

    public Integer getIdLawsuit() {
        return idLawsuit;
    }

    public void setIdLawsuit(Integer idLawsuit) {
        this.idLawsuit = idLawsuit;
    }

    public String getNumberLawsuit() {
        return numberLawsuit;
    }

    public void setNumberLawsuit(String numberLawsuit) {
        this.numberLawsuit = numberLawsuit;
    }

    public Plaintiff getPlaintiff() {
        return plaintiff;
    }

    public void setPlaintiff(Plaintiff plaintiff) {
        this.plaintiff = plaintiff;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "idLawsuit = " + this.idLawsuit + " [" + this.numberLawsuit + "]";
    }

}
