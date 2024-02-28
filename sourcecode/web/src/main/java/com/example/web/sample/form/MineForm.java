package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import nablarch.core.validation.ee.NumberRange;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MineForm implements Serializable {
    private String Position;

}
