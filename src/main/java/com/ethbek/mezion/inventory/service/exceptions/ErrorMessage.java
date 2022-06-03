package com.ethbek.mezion.inventory.service.exceptions;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
 private String message;
 private int errorCode;
 private String detail;
}
