package cl.contraloria.sicogen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonResultJTable {

    @JsonProperty("Result")
    private String result;
    @JsonProperty("Records")
    private List<JsonJTableExpenseBean> records;
    @JsonProperty("TotalRecordCount")
    private Integer totalRecordCount;
    @JsonProperty("Options")
    private List<OptionsJtable> options;
    @JsonProperty("Message")
    private String message;

    public JsonResultJTable(String result, List<JsonJTableExpenseBean> records, Integer totalRecordCount) {
        this.result = result;
        this.records = records;
        this.totalRecordCount = totalRecordCount;
    }

    public JsonResultJTable() {
    }

    public String getResult() {
        return result;
    }

    public List<JsonJTableExpenseBean> getRecords() {
        return records;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setRecords(List<JsonJTableExpenseBean> records) {
        records = records;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        totalRecordCount = totalRecordCount;
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
