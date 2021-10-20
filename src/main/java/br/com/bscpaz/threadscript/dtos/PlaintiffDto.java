package br.com.bscpaz.threadscript.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bscpaz.threadscript.entities.Plaintiff;

public class PlaintiffDto {

    private Plaintiff plaintiff = null;
    private boolean isSuccess = false;

    public PlaintiffDto(Plaintiff plaintiff) {
        this.plaintiff = plaintiff;
    }
    
    public Plaintiff getPlaintiff() {
        return plaintiff;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public static List<PlaintiffDto> convert(List<Plaintiff> plaintiffs) {
        return plaintiffs.stream().map(PlaintiffDto::new).collect(Collectors.toList());
    }
}
