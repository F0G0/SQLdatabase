package org.ngu.Entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class GroupInfoEntityPK implements Serializable {
    private BigInteger idGroup;
    private BigInteger idCoach;


    public GroupInfoEntityPK(BigInteger idGroup, BigInteger idCoach) {
        this.idGroup = idGroup;
        this.idCoach = idCoach;
    }
}
