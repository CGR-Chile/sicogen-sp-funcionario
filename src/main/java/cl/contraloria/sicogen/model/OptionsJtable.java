package cl.contraloria.sicogen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionsJtable {
    @JsonProperty("DisplayText")
    String DisplayText;
    @JsonProperty("Value")
    String Value;

    public String getDisplayText() {
        return DisplayText;
    }

    public void setDisplayText(String displayText) {
        DisplayText = displayText;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
