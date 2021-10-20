package br.com.bscpaz.threadscript.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = Plaintiff.TABLE_NAME)
public class Plaintiff {
    
    public static final String TABLE_NAME = "tb_plaintiff";

    @Id
	@Column(name = "id_plaintiff", unique = true, nullable = false)
	private Integer idPlaintiff;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "plaintiff")
    List<Lawsuit> lawsuits = new ArrayList<>();


    public Integer getIdPlaintiff() {
        return idPlaintiff;
    }

    public void setIdPlaintiff(Integer idPlaintiff) {
        this.idPlaintiff = idPlaintiff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lawsuit> getLawsuits() {
        return lawsuits;
    }

    public void setLawsuits(List<Lawsuit> lawsuits) {
        this.lawsuits = lawsuits;
    }
}
