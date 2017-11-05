
package com.f1soft.employeerecordsystem;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author nibesh
 */

@ManagedBean(name = "employee")
@RequestScoped
public class Employee implements Serializable{
    
    
    private int employeeId;
    
    @NotNull(message = "Name cannot be null")
    private String name;
    
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid")
    private String email;
    
    private String post;
    private String address;
    
    @Pattern(regexp = "^([0-9-]*)$", message = "Numbers only")
    @Size(min=10, max=10, message = "Mobile number must be of 10 digit" )
    private String mobileNumber;
    
    private static int IdCount = 4;
    private static boolean updateFlag = false;
    private static int updateObjIndex = -1;
    private HtmlDataTable dataTableEmployee;
    private static List<Employee> employeeList= new ArrayList<Employee>(){{
        add(new Employee(1, "Nibesh", "sdev.nibs@gamil.com", "Java Developer", "Bhaktapur", "9813265462"));
        add(new Employee(2, "Suraj", "suraj@gamil.com", "Java Developer", "Kathmandu", "9813789456"));
        add(new Employee(3, "Rajendra", "rajendra@gamil.com", "Sr. Java Developer", "Bhaktapur", "9841785236"));
    }};
    
    
    public Employee() {
    }

    public Employee(int employeeId, String name, String email, String post, String address, String mobileNumber) {
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
            e.employeeId = IdCount;
            employeeList.add(e);
            clearFields();
            IdCount++;
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
//        Employee e = (Employee) dataTableEmployee.getRowData();
//        employeeList.remove(e);
       
        boolean removeIf = employeeList.removeIf((Employee employee) -> (employee.getEmployeeId()== employeeId));
        this.updateFlag = false;
        this.updateObjIndex = -1;
    }
    
    public String cancel(){
        clearFields();
        return "index.xhtml";
    }
    
    public void clearFields(){
        this.employeeId = 0;
        this.name = null;
        this.email = null;
        this.post = null;
        this.address = null;
        this.mobileNumber = null;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
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
