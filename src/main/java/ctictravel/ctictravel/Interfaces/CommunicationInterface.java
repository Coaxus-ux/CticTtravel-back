package ctictravel.ctictravel.Interfaces;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.*;

public class CommunicationInterface {
    @JsonBackReference
    private Boolean isSuccessful;
    private String message;
    private Map<String, Object> data;

    private CommunicationInterface() {
    }

    public static class Builder {
        private Boolean isSuccessful;
        private String message;
        private Map<String, Object> data;

        public Builder setSuccessful(Boolean isSuccessful) {
            this.isSuccessful = isSuccessful;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setData(Map<String, Object> data) {
            this.data = data;
            return this;
        }

        public CommunicationInterface build() {
            CommunicationInterface communication = new CommunicationInterface();
            communication.isSuccessful = this.isSuccessful;
            communication.message = this.message;
            communication.data = this.data;
            return communication;
        }
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
