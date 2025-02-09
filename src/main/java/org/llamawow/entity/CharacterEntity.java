package org.llamawow.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name="characters")
@Data
public class CharacterEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guid;

    @Column(name = "account")
    private int account;

    @Column(name = "name")
    private String name;

    @Column(name = "race")
    private byte race;

    @Column(name = "class")
    private byte playerClass;

    @Column(name = "gender")
    private byte gender;

    @Column(name = "level")
    private byte level;

    @Column(name = "xp")
    private int xp;

    @Column(name = "money")
    private int money;

    @Column(name = "playerBytes")
    private int playerBytes;

    @Column(name = "playerBytes2")
    private int playerBytes2;

    @Column(name = "playerFlags")
    private int playerFlags;

    @Column(name = "position_x")
    private float positionX;

    @Column(name = "position_y")
    private float positionY;

    @Column(name = "position_z")
    private float positionZ;

    @Column(name = "map")
    private short map;

    @Column(name = "instance_id")
    private int instanceId;

    @Column(name = "instance_mode_mask")
    private byte instanceModeMask;

    @Column(name = "orientation")
    private float orientation;

    @Column(name = "taximask")
    private String taxiMask;

    @Column(name = "online")
    private byte online;

    @Column(name = "cinematic")
    private byte cinematic;

    @Column(name = "totaltime")
    private int totalTime;

    @Column(name = "leveltime")
    private int levelTime;

    @Column(name = "logout_time")
    private int logoutTime;

    @Column(name = "is_logout_resting")
    private byte isLogoutResting;

    @Column(name = "rest_bonus")
    private float restBonus;

    @Column(name = "resettalents_cost")
    private int resetTalentsCost;

    @Column(name = "resettalents_time")
    private int resetTalentsTime;

    @Column(name = "trans_x")
    private float transX;

    @Column(name = "trans_y")
    private float transY;

    @Column(name = "trans_z")
    private float transZ;

    @Column(name = "trans_o")
    private float transO;

    @Column(name = "transguid")
    private int transGuid;

    @Column(name = "extra_flags")
    private short extraFlags;

    @Column(name = "stable_slots")
    private byte stableSlots;

    @Column(name = "at_login")
    private short atLogin;

    @Column(name = "zone")
    private short zone;

    @Column(name = "death_expire_time")
    private int deathExpireTime;

    @Column(name = "taxi_path")
    private String taxiPath;

    @Column(name = "arenaPoints")
    private int arenaPoints;

    @Column(name = "totalHonorPoints")
    private int totalHonorPoints;

    @Column(name = "todayHonorPoints")
    private int todayHonorPoints;

    @Column(name = "yesterdayHonorPoints")
    private int yesterdayHonorPoints;

    @Column(name = "totalKills")
    private int totalKills;

    @Column(name = "todayKills")
    private short todayKills;

    @Column(name = "yesterdayKills")
    private short yesterdayKills;

    @Column(name = "chosenTitle")
    private int chosenTitle;

    @Column(name = "knownCurrencies")
    private long knownCurrencies;

    @Column(name = "watchedFaction")
    private int watchedFaction;

    @Column(name = "drunk")
    private byte drunk;

    @Column(name = "health")
    private int health;

    @Column(name = "power1")
    private int power1;

    @Column(name = "power2")
    private int power2;

    @Column(name = "power3")
    private int power3;

    @Column(name = "power4")
    private int power4;

    @Column(name = "power5")
    private int power5;

    @Column(name = "power6")
    private int power6;

    @Column(name = "power7")
    private int power7;

    @Column(name = "latency")
    private int latency;

    @Column(name = "speccount")
    private byte specCount;

    @Column(name = "activesp")
    private byte activeSp;

    @Column(name = "exploredZones")
    private String exploredZones;

    @Column(name = "equipmentCache")
    private String equipmentCache;

    @Column(name = "ammoId")
    private int ammoId;

    @Column(name = "knownTitles")
    private String knownTitles;

    @Column(name = "actionBars")
    private byte actionBars;

    @Column(name = "grantableLevels")
    private byte grantableLevels;

    @Column(name = "deleteInfos_Account")
    private int deleteInfosAccount;

    @Column(name = "deleteInfos_Name")
    private String deleteInfosName;

    @Column(name = "deleteDate")
    private int deleteDate;



}
