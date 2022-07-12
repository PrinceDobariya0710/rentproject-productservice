package com.rent.project.productservice.services.userservice;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public Object getAddressOfUser(Long userDetailsId,String token){
        String addressURL = "http://localhost:8082/user-profile-service/get/addressdata/"+userDetailsId;

        HttpHeaders addressHeader = new HttpHeaders();
        addressHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        addressHeader.setBearerAuth(token.substring(7));
        HttpEntity<String> addressEntity = new HttpEntity<String>(addressHeader);
        Map AddressData = restTemplate.exchange(addressURL, HttpMethod.GET,addressEntity, Map.class).getBody();
        JSONObject jsonAddress = new JSONObject(AddressData);
        jsonAddress.get("data");

        String contactURL = "http://localhost:8082/user-profile-service/get/contactdata/"+userDetailsId;
        HttpHeaders contactDataHeader = new HttpHeaders();
        contactDataHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        contactDataHeader.setBearerAuth(token.substring(7));
        HttpEntity<String> contactEntity = new HttpEntity<String>(contactDataHeader);
        Map contactData = restTemplate.exchange(contactURL, HttpMethod.GET,contactEntity, Map.class).getBody();
        JSONObject jsonContact = new JSONObject(contactData);
        jsonContact.get("data");

        Map<String,Map> details = new HashMap<>();


        details.put("address",AddressData);
        details.put("contact",contactData);

        return details;

    }
}
