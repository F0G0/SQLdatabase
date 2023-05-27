package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "Hike_group", schema = "20203_KISELEV")
@IdClass(org.ngu.Entity.HikeGroupEntityPK.class)
public class HikeGroupEntity {
    @Id
    @Basic
    @Column(name = "id_hike")
    private BigInteger idHike;
    @Id
    @Basic
    @Column(name = "id_tourist")
    private BigInteger idTourist;

    public BigInteger getIdHike() {
        return idHike;
    }

    public void setIdHike(BigInteger idHike) {
        this.idHike = idHike;
    }

    public BigInteger getIdTourist() {
        return idTourist;
    }

    public void setIdTourist(BigInteger idTourist) {
        this.idTourist = idTourist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HikeGroupEntity that = (HikeGroupEntity) o;

        if (idHike != null ? !idHike.equals(that.idHike) : that.idHike != null) return false;
        if (idTourist != null ? !idTourist.equals(that.idTourist) : that.idTourist != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHike != null ? idHike.hashCode() : 0;
        result = 31 * result + (idTourist != null ? idTourist.hashCode() : 0);
        return result;
    }
}
