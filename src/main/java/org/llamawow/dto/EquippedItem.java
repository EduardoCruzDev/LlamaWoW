package org.llamawow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EquippedItem {
    private int slot;
    private int itemId;
    private String itemName;
}
