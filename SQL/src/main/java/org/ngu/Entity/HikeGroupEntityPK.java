package org.ngu.Entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
@Data
public class HikeGroupEntityPK implements Serializable {
    private BigInteger idHike;
    private BigInteger idTourist;

    public HikeGroupEntityPK(BigInteger idHike, BigInteger idTourist) {
        this.idHike = idHike;
        this.idTourist = idTourist;
    }
}
