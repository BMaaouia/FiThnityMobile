/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Objects;


/**
 *
 * @author lenovo
 */
public class Employee {

    public static Employee getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   private int  id_Employee;
    private String firstname_employee;
    private String lastname_employee;
    private String email_employee;
    private String address_employee;
    private int id_vehicule;

    public Employee() {
    }

    public Employee(int id_employee, String firstname_employee, String lastname_employee, String email_employee, String address_employee,int id_vehicule) {
        this.id_Employee = id_employee;
        this.firstname_employee = firstname_employee;
        this.lastname_employee = lastname_employee;
        this.email_employee = email_employee;
        this.address_employee = address_employee;
        this.id_vehicule = id_vehicule;
    }

    public Employee(String firstname_employee, String lastname_employee, String email_employee, String address_employee,int id_vehicule) {
        this.firstname_employee = firstname_employee;
        this.lastname_employee = lastname_employee;
        this.email_employee = email_employee;
        this.address_employee = address_employee;
        this.id_vehicule = id_vehicule;

    }

    public int getId_employee() {
        return id_Employee;
    }

    public void setId_employee(int id_employee) {
        this.id_Employee = id_employee;
    }
     public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getFirstname_employee() {
        return firstname_employee;
    }

    public void setFirstname_employee(String firstname_employee) {
        this.firstname_employee = firstname_employee;
    }

    public String getLastname_employee() {
        return lastname_employee;
    }

    public void setLastname_employee(String lastname_employee) {
        this.lastname_employee = lastname_employee;
    }

    public String getEmail_employee() {
        return email_employee;
    }

    public void setEmail_employee(String email_employee) {
        this.email_employee = email_employee;
    }

    public String getAddress_employee() {
        return address_employee;
    }

    public void setAddress_employee(String address_employee) {
        this.address_employee =address_employee;
    }

    @Override
    public String toString() {
        return " id_employee = " + id_Employee + " || firstname_employee= " + firstname_employee + " || lastname_employee= " + lastname_employee + " || email_employee= " + email_employee + " || address_employee= " + address_employee ;
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id_Employee;
        hash = 41 * hash + Objects.hashCode(this.firstname_employee);
        hash = 41 * hash + Objects.hashCode(this.lastname_employee);
        hash = 41 * hash + Objects.hashCode(this.email_employee);
        hash = 41 * hash + Objects.hashCode(this.address_employee);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (this.id_Employee != other.id_Employee) {
            return false;
        }
        if (!Objects.equals(this.firstname_employee, other.firstname_employee)) {
            return false;
        }
        if (!Objects.equals(this.lastname_employee, other.lastname_employee)) {
            return false;
        }
        if (!Objects.equals(this.email_employee, other.email_employee)) {
            return false;
        }
        if (!Objects.equals(this.address_employee, other.address_employee)) {
            return false;
        }
        return true;
    }

    
   
    
    
    
}
