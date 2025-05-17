package org.llamawow.service.impl;

import org.llamawow.dto.EquippedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EquippedItem> getEquippedItemsByCharacterId(int characterId) {
        System.out.println("Consultando equipo de personaje con ID: " + characterId);
        String sql = """
           SELECT ci.slot, i.itemEntry, wt.name
           FROM characters.character_inventory ci
           JOIN characters.item_instance i ON ci.item = i.guid
           JOIN world.item_template wt ON i.itemEntry = wt.entry
           WHERE ci.guid = ? AND ci.bag = 0 AND ci.slot BETWEEN 0 AND 18
           ORDER BY ci.slot
        """;

        try {
        return jdbcTemplate.query(sql, new Object[]{characterId}, (rs, rowNum) ->
                new EquippedItem(
                        rs.getInt("slot"),
                        rs.getInt("itemEntry"),
                        rs.getString("name")
                )

        );

        } catch (Exception e) {
            System.err.println("Error al consultar ítems equipados: " + e.getMessage());
            e.printStackTrace();  // también puedes usar Logger
            return Collections.emptyList();
        }
    }
}