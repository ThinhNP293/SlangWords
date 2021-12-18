package vn.edu.hcmus.sv19127561.project;

/**
 * vn.edu.hcmus.sv19127561.project
 * Created by Thinh
 * Date 12/18/2021 - 10:55 PM
 * Description: ...
 */
public class SlangWords {
    private String slang;
    private String definition;

    public SlangWords(String slang) {

    }

    public SlangWords(String slang, String definition) {
        this.slang = slang;
        this.definition = definition;
    }

    public void setSlang(String slang) {
        this.slang = slang;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSlang() {
        return slang;
    }

    public String getDefinition() {
        return definition;
    }
}
