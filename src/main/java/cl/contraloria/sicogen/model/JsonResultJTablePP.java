package cl.contraloria.sicogen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonResultJTablePP {

    @JsonProperty("Result")
    private String result;
    @JsonProperty("Records")
    private List<ProgramaPresupuestarioDTO> records;
    @JsonProperty("TotalRecordCount")
    private Integer totalRecordCount;
    @JsonProperty("Options")
    private List<OptionsJtable> options;
    @JsonProperty("Message")
    private String message;

    public JsonResultJTablePP(String result, List<ProgramaPresupuestarioDTO> records, Integer totalRecordCount) {
        this.result = result;
        this.records = records;
        this.totalRecordCount = totalRecordCount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ProgramaPresupuestarioDTO> getRecords() {
        return records;
    }

    public void setRecords(List<ProgramaPresupuestarioDTO> records) {
        this.records = records;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
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
