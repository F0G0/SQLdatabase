package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "Group_list", schema = "20203_KISELEV")
@IdClass(org.ngu.Entity.GroupListEntityPK.class)
public class GroupListEntity {
    @Id
    @Basic
    @Column(name = "id_group")
    private BigInteger idGroup;
    @Id
    @Basic
    @Column(name = "id_tourist")
    private BigInteger idTourist;

    public BigInteger getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(BigInteger idGroup) {
        this.idGroup = idGroup;
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

        GroupListEntity that = (GroupListEntity) o;

        if (idGroup != null ? !idGroup.equals(that.idGroup) : that.idGroup != null) return false;
        if (idTourist != null ? !idTourist.equals(that.idTourist) : that.idTourist != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup != null ? idGroup.hashCode() : 0;
        result = 31 * result + (idTourist != null ? idTourist.hashCode() : 0);
        return result;
    }
}
