package com.hupa.exp.servermng.entity.role;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;

import java.util.List;
@JSONType(serialzeFeatures=SerializerFeature.WriteNonStringValueAsString)
public class RoleOutputDto extends BaseOutputDto {
    //@JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
    private String roleName;
    private String description;
    //@JSONField(name="enable",serializeUsing = ToStringSerializer.class,
            //serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private boolean enable;
    //@JSONField(serializeUsing = ToStringSerializer.class)
    private Long ctime;
    private List<Integer> menulist;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public List<Integer> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<Integer> menulist) {
        this.menulist = menulist;
    }


}
