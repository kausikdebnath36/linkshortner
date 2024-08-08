package com.Assingment.linkshortner.util;



import lombok.Data;

@Data
public class ResponseObjBol {
    private boolean success;
    public ResponseObjBol(boolean success) {
        this.success = success;
    }


}
