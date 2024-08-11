package com.trodix.nats.demo.natsdemo.nats.impl;

public class KeycloakUserEventData {
    private String id;
    private long time;
    private String realmId;
    private AuthDetails authDetails;
    private String resourceType;
    private String operationType;
    private String resourcePath;
    private String representation;
    private String error;
    private String resourceTypeAsString;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public AuthDetails getAuthDetails() {
        return authDetails;
    }

    public void setAuthDetails(AuthDetails authDetails) {
        this.authDetails = authDetails;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getResourceTypeAsString() {
        return resourceTypeAsString;
    }

    public void setResourceTypeAsString(String resourceTypeAsString) {
        this.resourceTypeAsString = resourceTypeAsString;
    }

    // Inner class for AuthDetails
    public static class AuthDetails {
        private String realmId;
        private String clientId;
        private String userId;
        private String ipAddress;

        @Override
        public String toString() {
            return "AuthDetails{" +
                    "realmId='" + realmId + '\'' +
                    ", clientId='" + clientId + '\'' +
                    ", userId='" + userId + '\'' +
                    ", ipAddress='" + ipAddress + '\'' +
                    '}';
        }

        public String getRealmId() {
            return realmId;
        }

        public void setRealmId(String realmId) {
            this.realmId = realmId;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }
    }

    @Override
    public String toString() {
        return "KeycloakUserEventData{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", realmId='" + realmId + '\'' +
                ", authDetails=" + authDetails +
                ", resourceType='" + resourceType + '\'' +
                ", operationType='" + operationType + '\'' +
                ", resourcePath='" + resourcePath + '\'' +
                ", representation='" + representation + '\'' +
                ", error='" + error + '\'' +
                ", resourceTypeAsString='" + resourceTypeAsString + '\'' +
                '}';
    }
}
