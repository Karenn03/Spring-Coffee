package com.app.CoffeeTech;

import com.app.CoffeeTech.Entity.UserEntity;
import com.app.CoffeeTech.Entity.PrivilegeEntity;
import com.app.CoffeeTech.Entity.RoleEntity;
import com.app.CoffeeTech.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringCoffeeTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoffeeTechApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			// Create privileges
			PrivilegeEntity employeePrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Employee RUD")
					.build();
			PrivilegeEntity readPrivileges = PrivilegeEntity.builder()
					.nombrePrivilegio("Privilege R")
					.build();
			PrivilegeEntity productCrudPrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Product CRUD")
					.build();
			PrivilegeEntity productTypeCrudPrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Product Type CRUD")
					.build();
			PrivilegeEntity promotionCrudPrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Promotion CRUD")
					.build();
			PrivilegeEntity reviewPrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Review R")
					.build();
			PrivilegeEntity rolePrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Role RU")
					.build();
			PrivilegeEntity tableCrudPrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Table CRUD")
					.build();
			PrivilegeEntity salePrivilege = PrivilegeEntity.builder()
					.nombrePrivilegio("Sale R")
					.build();
			// Create roles
			RoleEntity gerenteRole = RoleEntity.builder()
					.nombreRol(RoleEntity.NombreRol.Gerente)
					.privilegios(List.of(
							employeePrivilege,
							readPrivileges,
							productCrudPrivilege,
							productTypeCrudPrivilege,
							promotionCrudPrivilege,
							reviewPrivilege,
							rolePrivilege,
							tableCrudPrivilege,
							salePrivilege
					))
					.build();
			// Create users
			UserEntity userKaren = UserEntity.builder()
					.documento(1013)
					.nombres("Karen Sophia")
					.apellidos("Molina Díaz")
					.correoElectronico("marcesophi1003@gmail.com")
					.contraseña("123")
					.telefono("3141234567")
					.direccion("Carrera 90")
					.isEnabled(true)
					.isAccountNonLocked(true)
					.isAccountNonExpired(true)
					.isCredentialsNonExpired(true)
					.roles(List.of(gerenteRole))
					.build();
			userRepository.saveAll(List.of(userKaren));
		};
	}

	// 1:34:06

}
