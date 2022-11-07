package cl.contraloria.sicogen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonResultJTableCuentasParticulares {
    @JsonProperty("Result")
    private String result;
    @JsonProperty("Records")
    private List<CuentaParticularPresupDTO> records;
    @JsonProperty("TotalRecordCount")
    private Integer totalRecordCount;
    @JsonProperty("Options")
    private List<OptionsJtable> options;
    @JsonProperty("Message")
    private String message;

    public void setResult(String result) {
        this.result = result;
    }

    public void setRecords(List<CuentaParticularPresupDTO> records) {
        this.records = records;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public void setOptions(List<OptionsJtable> options) {
        this.options = options;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public List<CuentaParticularPresupDTO> getRecords() {
        return records;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public List<OptionsJtable> getOptions() {
        return options;
    }

    public String getMessage() {
        return message;
    }
}
