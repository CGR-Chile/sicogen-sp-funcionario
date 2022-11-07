package cl.contraloria.sicogen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonResultJTableAdd {

    @JsonProperty("Result")
    private String result;

    @JsonProperty("Record")
    private JsonJTableExpenseBean record;

    @JsonProperty("Message")
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JsonJTableExpenseBean getRecord() {
        return record;
    }

    public void setRecord(JsonJTableExpenseBean record) {
        this.record = record;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
