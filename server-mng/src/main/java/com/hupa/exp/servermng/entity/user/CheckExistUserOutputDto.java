package com.hupa.exp.servermng.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class CheckExistUserOutputDto extends BaseOutputDto
{
    @JsonProperty("has_user")
   private boolean hasUser;

    public boolean isHasUser() {
        return hasUser;
    }

    public void setHasUser(boolean hasUser) {
        this.hasUser = hasUser;
    }
}
