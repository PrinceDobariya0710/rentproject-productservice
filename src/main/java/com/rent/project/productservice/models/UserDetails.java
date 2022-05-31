package com.rent.project.productservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

    @AllArgsConstructor
    @NoArgsConstructor
    @Entity//(name = "user_details")
    @Data
    @Table
    public class UserDetails implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="user_details_id")
        private Long userDetailsId;

        @Column(nullable = false) //name = "UserId",
        private Long userId;

        @Column(nullable = false, length = 20) // name = "FirstName",
        private String firstName;

        @Column(nullable = false, length = 20) // name = "LastName",
        private String lastName;

        @Column(nullable = false) // name = "Dob",
        private Date DOB;

        @Column(nullable = true, length = 20) // name = "OrgName",
        private String orgName;

        @Column(nullable = true, length = 50) // name = "Website",
        private String website;

        public UserDetails(Long userId, String firstName, String lastName, Date DOB, String orgName, String website) {
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.DOB = DOB;
            this.orgName = orgName;
            this.website = website;
        }
}
