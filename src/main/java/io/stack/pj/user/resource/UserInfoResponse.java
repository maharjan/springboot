package io.stack.pj.user.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class UserInfoResponse implements Serializable{

    private Date createdDate;
    private String username;
    private List<String> roles;

}