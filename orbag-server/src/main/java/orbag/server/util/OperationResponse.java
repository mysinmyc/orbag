package orbag.server.util;

import orbag.action.ActionFeedback;
import orbag.util.OperationFeedback;
import orbag.util.OperationStatus;
import orbag.util.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class OperationResponse {

    OperationStatus executionStatus;

    String errorMessage;

    List<ValidationError> validationErrors = new ArrayList<ValidationError>();

    boolean requestValid;

    public OperationResponse() {
    }

    public OperationResponse(OperationFeedback<?> feedback) {
        setRequestValid(feedback.isOperationValid());
        setValidationErrors(feedback.getValidationErrors());
        setExecutionStatus(feedback.getExecutionStatus());
        setErrorMessage( feedback.getException() == null ? null : feedback.getException().getMessage());
    }





    public boolean getRequestValid() {
        return requestValid;
    }

    public void setRequestValid(boolean requestValid) {
        this.requestValid = requestValid;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public OperationStatus getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(OperationStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

