package org.superbiz.imap;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lead")
public class Lead {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private Date date;

    @Column
    private String phone;

    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /*
        Lead Source: Lead form on Tomitribe.com contact page
        Form ID: 5
        Company: BTS
        Email: tsexton@wrberkley.com
        Message: What is the dollar amount charged per core for Tomcat Support?
        From: Tomitribe <ops@tomitribe.com>
        Last name: Sexton
        Date: Mon, Apr 1, 2019 at 8:47 PM
        Subject: [Salesforce Web to Lead Submission] Lead form on Tomitribe.com
        How did you hear about Tomitribe?: Web
        Phone: 515-564-2595
        http: //www.tomitribe.com
        First name: Terri
        To: <ops@tomitribe.com>
        Form Editor:
        https: //tomitribe4.wpengine.com/wp-admin/options-general.php?page=salesforce-wordpress-to-lead&tab=form&id=5
     */
}
