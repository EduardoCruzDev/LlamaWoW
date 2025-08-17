package org.llamawow.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.llamawow.dto.EquippedItem;

//*********************************//
//  Web Hecha por EduardoCruzDev   //
//*********************************//

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="character_inventory")
public class CharacterInventoryEntity {

    @Id
    @Column(name="guid")
    private int guid;

    @Column(name="slot")
    private int slot;

    @Column(name="item")
    private int item;

    @Column(name="bag")
    private String bag;

    public CharacterInventoryEntity(EquippedItem equippedItem){
        this.slot = equippedItem.getSlot();
        this.item=equippedItem.getItemId();
        this.bag=equippedItem.getItemName();

    }

}