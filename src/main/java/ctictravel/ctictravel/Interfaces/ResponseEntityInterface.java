package ctictravel.ctictravel.Interfaces;

import java.util.List;
import java.util.Map;

public class ResponseEntityInterface {
    private Boolean isSuccessful;
    private String message;
    private String JWT;
    private Map<String, Object> data;

    private ResponseEntityInterface() {
    }

    public static class Builder {
        private Boolean isSuccessful;
        private String message;
        private String JWT;
        private Map<String, Object> data;

        public Builder setSuccessful(Boolean isSuccessful) {
            this.isSuccessful = isSuccessful;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setJWT(String JWT) {
            this.JWT = JWT;
            return this;
        }

        public Builder setData(Map<String, Object>data) {
            this.data = data;
            return this;
        }

        public ResponseEntityInterface build() {
            ResponseEntityInterface response = new ResponseEntityInterface();
            response.isSuccessful = this.isSuccessful;
            response.message = this.message;
            response.JWT = this.JWT;
            response.data = this.data;
            return response;
        }
    }

    // Getters
    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public String getJWT() {
        return JWT;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
