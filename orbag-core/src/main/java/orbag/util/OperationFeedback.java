package orbag.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Define a standard way to expose feedback of an operation.
 *
 * <p> Feedback of the operation is offered by {@link OperationFeedback#getExecutionStatus()} an can be:
 *
 * <ul>
 *     <li>{@link OperationStatus#VALIDATION_FAILED} if validation of the operation produced some errors ({@link OperationFeedback#getValidationErrors()} not empty) </li>
 *     <li>{@link OperationStatus#FAILED} if the operation ended in error (exception stored in {@link OperationFeedback#getException()}) </li>
 *     <li>{@link OperationStatus#SUCCEEDED} if the previous conditions are not met </li>
 * </ul>
 *
 * <p> Operation provide a result
 *
 * <p> {@link OperationFeedback#getExecutionStatus()}
 *
 * @param <T> type of result
 */
public class OperationFeedback<T> {

    T result;

    Exception exception;

    List<ValidationError> validationErrors = new ArrayList<ValidationError>();

    /**
     * return validation error validation errors
     * @return
     */
    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public void addValidationError(String error) {
        addValidationError(error,null);
    }

    public void addValidationError(String error, String fieldName) {
        ValidationError validationError = new ValidationError();
        validationError.setError(error);
        validationError.setField(fieldName);
        validationErrors.add(validationError);
    }

    public boolean isOperationValid() {
        return validationErrors ==null || validationErrors.isEmpty();
    }

    /**
     * Return the status of the operation (succedeed when there are no validation errors or exceptions)
     * @return
     */
    public OperationStatus getExecutionStatus() {
        if (isOperationValid()) {
            return exception ==null?OperationStatus.SUCCEEDED : OperationStatus.FAILED;
        } else {
            return OperationStatus.VALIDATION_FAILED;
        }
    }


    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
