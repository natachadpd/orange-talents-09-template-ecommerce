package br.com.zupacademy.natacha.mercadolivre.controller.form;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ImagemForm {
    


    @NotNull
    @Valid
    private List<MultipartFile> imagens = new ArrayList<>();


    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
