package com.hupa.exp.servermng.entity.operationlog;

import com.hupa.exp.common.entity.dto.output.BasePageOutputDto;

public class OperationLogListOutputDto extends BasePageOutputDto {
    public static class OperationLogInfo
    {
        private String id;
        private String accountId;
        private String userName;
        private String operationTime;
        private String operationType;
        private String operationModule;
        private String before;
        private String after;
        private String ctime;
        private String mtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getOperationTime() {
            return operationTime;
        }

        public void setOperationTime(String operationTime) {
            this.operationTime = operationTime;
        }

        public String getOperationType() {
            return operationType;
        }

        public void setOperationType(String operationType) {
            this.operationType = operationType;
        }

        public String getOperationModule() {
            return operationModule;
        }

        public void setOperationModule(String operationModule) {
            this.operationModule = operationModule;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }
    }
}
