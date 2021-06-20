package com.unla.app.helpers;

import java.util.Objects;

public class ResponseHelper {
    private int status;
    private String statusText;

    public ResponseHelper() {
    }

    public ResponseHelper(int status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public ResponseHelper status(int status) {
        setStatus(status);
        return this;
    }

    public ResponseHelper statusText(String statusText) {
        setStatusText(statusText);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ResponseHelper)) {
            return false;
        }
        ResponseHelper rESTErrorModel = (ResponseHelper) o;
        return status == rESTErrorModel.status && Objects.equals(statusText, rESTErrorModel.statusText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, statusText);
    }

    @Override
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            ", statusText='" + getStatusText() + "'" +
            "}";
    }

}
