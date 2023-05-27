package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "Info_hike", schema = "20203_KISELEV")
public class InfoHikeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Basic
    @Column(name = "id_hike")
    private BigInteger idHike;
    @Basic
    @Column(name = "time")
    private Timestamp time;
    @Basic
    @Column(name = "is_stop")
    private String isStop;
    @Basic
    @Column(name = "camp_name")
    private String campName;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getIdHike() {
        return idHike;
    }

    public void setIdHike(BigInteger idHike) {
        this.idHike = idHike;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getIsStop() {
        return isStop;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoHikeEntity that = (InfoHikeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idHike != null ? !idHike.equals(that.idHike) : that.idHike != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (isStop != null ? !isStop.equals(that.isStop) : that.isStop != null) return false;
        if (campName != null ? !campName.equals(that.campName) : that.campName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idHike != null ? idHike.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (isStop != null ? isStop.hashCode() : 0);
        result = 31 * result + (campName != null ? campName.hashCode() : 0);
        return result;
    }
}
