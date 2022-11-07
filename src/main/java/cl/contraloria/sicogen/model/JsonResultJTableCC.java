package cl.contraloria.sicogen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonResultJTableCC {

    @JsonProperty("Result")
    private String result;
    @JsonProperty("Records")
    private List<PlanCuentasContables> records;
    @JsonProperty("TotalRecordCount")
    private Integer totalRecordCount;
    @JsonProperty("Options")
    private List<OptionsJtable> options;
    @JsonProperty("Message")
    private String message;

    public JsonResultJTableCC(String result, List<PlanCuentasContables> records, Integer totalRecordCount) {
        this.result = result;
        this.records = records;
        this.totalRecordCount = totalRecordCount;
    }

    public JsonResultJTableCC() {
    }

    public String getResult() {
        return result;
    }

    public List<PlanCuentasContables> getRecords() {
        return records;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setRecords(List<PlanCuentasContables> records) {
        this.records = records;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public List<OptionsJtable> getOptions() {
        return options;
    }

    public void setOptions(List<OptionsJtable> options) {
        this.options = options;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
