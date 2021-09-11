package com.budymann.container.tokenizeserviceapiweb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    public String attributeName;
    public String attributeValue;
}
