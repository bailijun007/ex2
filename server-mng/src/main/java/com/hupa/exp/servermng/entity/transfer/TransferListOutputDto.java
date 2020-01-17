package com.hupa.exp.servermng.entity.transfer;

import com.hupa.exp.base.entity.bo.BaseAccountTransferBo;
import com.hupa.exp.common.entity.dto.output.BaseOutputDto;
import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;
import org.springframework.data.mongodb.core.mapping.Field;

public class TransferListOutputDto extends BasePageOutputDto<TransferInfoOutputDto> {

  /*  private class TransferInfoOutputDto extends BaseAccountTransferBo {
        @Field("_id")
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }*/
}
