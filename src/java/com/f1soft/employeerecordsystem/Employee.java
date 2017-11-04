
package com.f1soft.employeerecordsystem;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nibesh
 */

@ManagedBean(name = "employee")
@SessionScoped
public class Employee {
    
    private int employeeId;
    private String name;
    private String email;
    private String post;
    private String address;
    private long mobileNumber;
    
    private boolean updateFlag = false;
    private int updateObjIndex = -1;
    private List<Employee> employeeList= new ArrayList<>();
    private HtmlDataTable dataTableEmployee;
    
    public Employee() {
         employeeList.add(new Employee(1, "Nibesh", "sdev.nibs@gmail.com", "Jr. Java Developer", "Bhaktapur", 9813265462l));
        employeeList.add(new Employee(2, "Suraj", "teamwarrior11@gmail.com", "Jr. Java Developer", "Baneshwor", 9813987456l));
    }

    public Employee(int employeeId, String name, String email, String post, String address, long mobileNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.post = post;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }
    
    public void save(ActionEvent event){
        Employee e = new Employee(employeeId, name, email, address, post, mobileNumber);
        if(!updateFlag){
            employeeList.add(e);
            clearFields();
        }else{
            employeeList.remove(updateObjIndex);
            employeeList.add(e);
            clearFields();
        }       
    }
    
    public void update(ActionEvent event) throws IOException{
        int index = dataTableEmployee.getRowIndex();
        Employee emp = (Employee) dataTableEmployee.getRowData();
        this.employeeId = emp.employeeId;
        this.name = emp.name;
        this.email = emp.email;
        this.post = emp.post;
        this.address = emp.address;
        this.mobileNumber = emp.mobileNumber;
        this.updateFlag = true;
        this.updateObjIndex = index;
    }
    
    public void delete() throws IOException{
        int index = dataTableEmployee.getRowIndex();
        Employee e = (Employee) dataTableEmployee.getRowData();
        employeeList.remove(e);
    }
    
    public void cancel(ActionEvent event){
        clearFields();
    }
    
    public void clearFields(){
        this.employeeId = 0;
        this.name = null;
        this.email = null;
        this.post = null;
        this.address = null;
        this.mobileNumber = 0;
        this.updateFlag = false;
        this.updateObjIndex = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public int getUpdateObjIndex() {
        return updateObjIndex;
    }

    public void setUpdateObjIndex(int updateObjIndex) {
        this.updateObjIndex = updateObjIndex;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public HtmlDataTable getDataTableEmployee() {
        return dataTableEmployee;
    }

    public void setDataTableEmployee(HtmlDataTable dataTableEmployee) {
        this.dataTableEmployee = dataTableEmployee;
    }
  
}
