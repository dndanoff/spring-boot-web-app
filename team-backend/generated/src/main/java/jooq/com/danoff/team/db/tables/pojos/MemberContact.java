/*
 * This file is generated by jOOQ.
 */
package com.danoff.team.db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MemberContact implements Serializable {

    private static final long serialVersionUID = -110025339;

    private Long   id;
    private Long   memberId;
    private Long   contactTypeId;
    private String value;

    public MemberContact() {}

    public MemberContact(MemberContact value) {
        this.id = value.id;
        this.memberId = value.memberId;
        this.contactTypeId = value.contactTypeId;
        this.value = value.value;
    }

    public MemberContact(
        Long   id,
        Long   memberId,
        Long   contactTypeId,
        String value
    ) {
        this.id = id;
        this.memberId = memberId;
        this.contactTypeId = contactTypeId;
        this.value = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getContactTypeId() {
        return this.contactTypeId;
    }

    public void setContactTypeId(Long contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MemberContact (");

        sb.append(id);
        sb.append(", ").append(memberId);
        sb.append(", ").append(contactTypeId);
        sb.append(", ").append(value);

        sb.append(")");
        return sb.toString();
    }
}
