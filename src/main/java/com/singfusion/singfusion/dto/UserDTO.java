package com.singfusion.singfusion.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.singfusion.singfusion.config.Status;
import com.singfusion.singfusion.dto.UserDTO;
import com.singfusion.singfusion.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String numero_telephone;
	private Byte status;
	private File piece_identite;
	private Boolean isPiece_correct = false;
	private Long roleId;
	private String token;
	private LocalDateTime tokenCreationDate;
	private byte[] photo_profile_data;
	private String photo_profile_name;
	private String photo_profile_type;
	private String type_profil;
	private String titre_poste;
	private Date datedebut;
	private Date datefin;

}