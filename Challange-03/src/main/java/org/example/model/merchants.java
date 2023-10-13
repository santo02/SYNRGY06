package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class merchants {
    private Long id;
    private String merchant_name;
    private String merchant_location;
    private boolean isOpen;
}
