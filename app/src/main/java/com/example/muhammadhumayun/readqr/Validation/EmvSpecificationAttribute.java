package com.example.muhammadhumayun.readqr.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

/// <summary>
/// Defines metadata for the EMV(R) Co QR Code Specification.
/// </summary>
public @interface EmvSpecificationAttribute {


    /// <summary>
    /// The length is coded as a two-digit numeric value, with a value ranging from "01" to "99".
    /// </summary>
    int maxLength();

    /// <summary>
    /// The ID is coded as a two-digit numeric value, with a value ranging from "00" to "99".
    /// </summary>
    int id();

    /// <summary>
    /// Gets or sets a value indicating whether the property represents a rood node.
    /// </summary>
    /// <value>
    ///   <c>true</c> if this instance is parent; otherwise, <c>false</c>.
    /// </value>
    boolean isParent();


    /*
    Usage
    @Status(priority = STATUS.Priority.MEDIUM, author = “Amit Shekhar”, completion = 0)
public void methodOne() {
 //no code
}
     */

    /*
     Status statusAnnotation = (Status)method.getAnnotation(Status.class);
    if(statusAnnotation != null) {
     System.out.println(" Method Name : " + method.getName());
     System.out.println(" Author : " + statusAnnotation.author());
     System.out.println(" Priority : " + statusAnnotation.priority());
     System.out.println(" Completion Status : " + statusAnnotation.completion());
    }
     */
}

