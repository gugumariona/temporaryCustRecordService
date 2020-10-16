package com.qubedlab.crair.util.wrapper;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@JsonDeserialize
@Data
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPersonalDetailsWrapper{


    private String customerGlobalID;

    private String licenseIDNumber;
    private String firstName;

     private String licenseExpirationDate;
    private String dateOfBirth;

     private String socialSecurityNumberFraud2;

   private String socialSecurityNumberFraud3;

    private String socialSecurityNumberFraud1;
    private String socialSecurityNumber;

    private String nonResidentIndicator;
 private String namePrefix;

    private String nameSuffix;

    private String givenName;
 private String familyName;

   private String middleInitial;

    private String middleName;

    private String lastName;
     private LocalDate dateCreated;

   private String status;

   private String statusDescription;

   private String licenseState;

    private String privacyIndicator;

    private String privacyType;

    private String educLevel;

}
