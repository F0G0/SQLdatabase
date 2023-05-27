package org.ngu.Entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
@Data
public class GroupsEntityPK implements Serializable {
    private BigInteger idSection;
    private BigInteger idGroup;

    public GroupsEntityPK(BigInteger idSection, BigInteger idGroup) {
        this.idSection = idSection;
        this.idGroup = idGroup;
    }
}
