package com.budymann.container.tokenizeserviceapiweb;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {
    public String attributeName;
    public String attributeValue;
    public String tokenizedValue;
}
