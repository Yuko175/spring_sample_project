package com.example.web.sample.form;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import nablarch.core.validation.ee.NumberRange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MineForm implements Serializable{
    private String Position;
    private String[][] mineArray;
    private boolean[][] isPushedArray;
}
