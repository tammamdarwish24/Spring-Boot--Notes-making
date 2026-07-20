package com.tammam.secure_notes.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
@Data
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
	private Integer roleId;
	@Column(name="role_name",length=20)
	@ToString.Exclude
    @Enumerated(EnumType.STRING)
   // @Column(length = 20, name = "role_name")
	private AppRole roleName;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JsonBackReference
    @ToString.Exclude
	private Set<User> users = new HashSet<User>();
    
    public Role(AppRole roleName) {
        this.roleName = roleName;
    }

}
