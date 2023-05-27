package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "Group_info", schema = "20203_KISELEV")
@IdClass(org.ngu.Entity.GroupInfoEntityPK.class)
public class GroupInfoEntity {
    @Id
    @Basic
    @Column(name = "id_group")
    private BigInteger idGroup;
    @Basic
    @Column(name = "date_start")
    private Date dateStart;
    @Basic
    @Column(name = "date_end")
    private Date dateEnd;

    @Id
    @Basic
    @Column(name = "id_coach")
    private BigInteger idCoach;

    public BigInteger getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(BigInteger idGroup) {
        this.idGroup = idGroup;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public BigInteger getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(BigInteger idCoach) {
        this.idCoach = idCoach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupInfoEntity that = (GroupInfoEntity) o;

        if (idGroup != null ? !idGroup.equals(that.idGroup) : that.idGroup != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (idCoach != null ? !idCoach.equals(that.idCoach) : that.idCoach != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGroup != null ? idGroup.hashCode() : 0;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (idCoach != null ? idCoach.hashCode() : 0);
        return result;
    }
}
