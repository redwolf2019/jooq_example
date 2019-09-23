package com.redwolf.example.jooq.controller;

import com.redwolf.example.jooq.generator.tables.records.DeviceRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.redwolf.example.jooq.generator.tables.Device.DEVICE;

@RestController
@RequestMapping("/v1")
public class IndexController {


    @Autowired
    private transient DSLContext sharding;

    @GetMapping
    void get(){
        DeviceRecord record = new DeviceRecord();
        record.setCode(UUID.randomUUID().toString());
        record.setName(UUID.randomUUID().toString());
        sharding.insertInto(DEVICE).set(record).execute();
    }
}
