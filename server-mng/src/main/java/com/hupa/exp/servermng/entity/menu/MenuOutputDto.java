package com.hupa.exp.servermng.entity.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

public class MenuOutputDto extends BaseOutputDto {


    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
