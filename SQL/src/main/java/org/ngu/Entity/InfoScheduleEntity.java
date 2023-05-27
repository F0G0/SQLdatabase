package org.ngu.Entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "Info_schedule", schema = "20203_KISELEV")
public class InfoScheduleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private BigInteger id;
    @Basic
    @Column(name = "id_schedule")
    private BigInteger idSchedule;
    @Basic
    @Column(name = "Place")
    private String place;
    @Basic
    @Column(name = "time")
    private Timestamp time;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(BigInteger idSchedule) {
        this.idSchedule = idSchedule;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfoScheduleEntity that = (InfoScheduleEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (idSchedule != null ? !idSchedule.equals(that.idSchedule) : that.idSchedule != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idSchedule != null ? idSchedule.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
