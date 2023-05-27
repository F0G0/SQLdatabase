package org.ngu.Entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class GroupListEntityPK implements Serializable {
    private BigInteger idGroup;
    private BigInteger idTourist;

    public GroupListEntityPK(BigInteger idGroup, BigInteger idTourist) {
        this.idGroup = idGroup;
        this.idTourist = idTourist;
    }
}
