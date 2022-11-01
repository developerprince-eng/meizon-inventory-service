/*
package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.exceptions.InventoryServiceException;
import com.ethbek.mezion.inventory.service.models.avro.RoleMap;
import com.ethbek.mezion.inventory.service.models.avro.UserDto;
import com.ethbek.mezion.inventory.service.models.dto.Action;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "#{'${topic.user}'}")
    public void saveAndUpdateUser(ConsumerRecord<String, GenericRecord> record) {
        log.info("Receiving message from User Event Stream");
        log.info(" USER OBJECT :: {}",record.value().toString());
        Gson gson = new Gson();
        UserDto user = gson.fromJson( record.value().toString(), UserDto.class);

        RoleMap roleMapEventDto = user.getRole().get(0);

        if(user.getAction().equals(Action.CREATE.name()) || user.getAction().equals(Action.UPDATE.name())){
            userService.creatUser( com.ethbek.mezion.inventory.service.models.dto.UserDto.builder()
                    .email(user.getEmailAddress())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .locationId(roleMapEventDto.getBranchId())
                    .organisationId(roleMapEventDto.getOrganisationalId())
                    .userId(user.getEmployeeId())
                    .build());
        }else if (user.getAction().equals(Action.DELETE.name())){
            userService.deleteByEmail(user.getEmailAddress());
        }
        else {
            throw new InventoryServiceException( "Unkown Action" );
        }


    }
}
*/
