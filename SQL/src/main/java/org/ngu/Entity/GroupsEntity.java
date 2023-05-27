package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "Groups", schema = "20203_KISELEV")
@IdClass(org.ngu.Entity.GroupInfoEntityPK.class)
public class GroupsEntity {
    @Id
    @Basic
    @Column(name = "id_section")
    private BigInteger idSection;
    @Id
    @Basic
    @Column(name = "id_group")
    private BigInteger idGroup;

    public BigInteger getIdSection() {
        return idSection;
    }

    public void setIdSection(BigInteger idSection) {
        this.idSection = idSection;
    }

    public BigInteger getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(BigInteger idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (idSection != null ? !idSection.equals(that.idSection) : that.idSection != null) return false;
        if (idGroup != null ? !idGroup.equals(that.idGroup) : that.idGroup != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSection != null ? idSection.hashCode() : 0;
        result = 31 * result + (idGroup != null ? idGroup.hashCode() : 0);
        return result;
    }
}
