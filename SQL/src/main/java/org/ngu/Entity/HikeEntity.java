package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "Hike", schema = "20203_KISELEV")
public class HikeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Basic
    @Column(name = "id_coach")
    private BigInteger idCoach;
    @Basic
    @Column(name = "id_athlete")
    private BigInteger idAthlete;
    @Basic
    @Column(name = "difficulty")
    private BigInteger difficulty;
    @Basic
    @Column(name = "path_name")
    private String pathName;
    @Basic
    @Column(name = "is_planned")
    private String isPlanned;
    @Basic
    @Column(name = "length")
    private BigInteger length;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public BigInteger getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(BigInteger difficulty) {
        this.difficulty = difficulty;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getIsPlanned() {
        return isPlanned;
    }

    public void setIsPlanned(String isPlanned) {
        this.isPlanned = isPlanned;
    }

    public BigInteger getLength() {
        return length;
    }

    public void setLength(BigInteger length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HikeEntity that = (HikeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idCoach != null ? !idCoach.equals(that.idCoach) : that.idCoach != null) return false;
        if (idAthlete != null ? !idAthlete.equals(that.idAthlete) : that.idAthlete != null) return false;
        if (difficulty != null ? !difficulty.equals(that.difficulty) : that.difficulty != null) return false;
        if (pathName != null ? !pathName.equals(that.pathName) : that.pathName != null) return false;
        if (isPlanned != null ? !isPlanned.equals(that.isPlanned) : that.isPlanned != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idCoach != null ? idCoach.hashCode() : 0);
        result = 31 * result + (idAthlete != null ? idAthlete.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (pathName != null ? pathName.hashCode() : 0);
        result = 31 * result + (isPlanned != null ? isPlanned.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        return result;
    }
}
