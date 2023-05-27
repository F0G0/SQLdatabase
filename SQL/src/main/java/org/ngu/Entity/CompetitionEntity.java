package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "Competition", schema = "20203_KISELEV")
public class CompetitionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "id_coach")
    private BigInteger idCoach;
    @Basic
    @Column(name = "id_athlete")
    private BigInteger idAthlete;
    @Basic
    @Column(name = "id_section")
    private BigInteger idSection;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(BigInteger idCoach) {
        this.idCoach = idCoach;
    }

    public BigInteger getIdAthlete() {
        return idAthlete;
    }

    public void setIdAthlete(BigInteger idAthlete) {
        this.idAthlete = idAthlete;
    }

    public BigInteger getIdSection() {
        return idSection;
    }

    public void setIdSection(BigInteger idSection) {
        this.idSection = idSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompetitionEntity that = (CompetitionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idCoach != null ? !idCoach.equals(that.idCoach) : that.idCoach != null) return false;
        if (idAthlete != null ? !idAthlete.equals(that.idAthlete) : that.idAthlete != null) return false;
        if (idSection != null ? !idSection.equals(that.idSection) : that.idSection != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idCoach != null ? idCoach.hashCode() : 0);
        result = 31 * result + (idAthlete != null ? idAthlete.hashCode() : 0);
        result = 31 * result + (idSection != null ? idSection.hashCode() : 0);
        return result;
    }
}
