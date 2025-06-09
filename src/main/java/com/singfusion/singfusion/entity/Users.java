package com.singfusion.singfusion.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@NoArgsConstructor
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String numero_telephone;
	private Byte status;
	private File piece_identite;
	private Boolean isPiece_correct = false;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	@Column(name = "token", length = 1024)
	private String token;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime tokenCreationDate;
	private byte[] photo_profile_data;
	private String photo_profile_name;
	private String photo_profile_type;
	private String type_profil;
	private String titre_poste;
	private Date datedebut;
	private Date datefin;
	private Boolean isEtapes1Done= false;
	private Boolean isEtapes2Done = false;
	private Boolean isEtapes3Done = false;
	private Boolean isEtapes4done = false;
	private Boolean isEtapes5Done = false;

	public Users(Long id, String firstName, String lastName, String email, String username, String password, String numero_telephone, Byte status, File piece_identite, Boolean isPiece_correct, Role role, String token, LocalDateTime tokenCreationDate, byte[] photo_profile_data, String photo_profile_name, String photo_profile_type, String type_profil, String titre_poste, Date datedebut, Date datefin, Boolean isEtapes1Done, Boolean isEtapes2Done, Boolean isEtapes3Done, Boolean isEtapes4done, Boolean isEtapes5Done) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.numero_telephone = numero_telephone;
		this.status = status;
		this.piece_identite = piece_identite;
		this.isPiece_correct = isPiece_correct;
		this.role = role;
		this.token = token;
		this.tokenCreationDate = tokenCreationDate;
		this.photo_profile_data = photo_profile_data;
		this.photo_profile_name = photo_profile_name;
		this.photo_profile_type = photo_profile_type;
		this.type_profil = type_profil;
		this.titre_poste = titre_poste;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.isEtapes1Done = isEtapes1Done;
		this.isEtapes2Done = isEtapes2Done;
		this.isEtapes3Done = isEtapes3Done;
		this.isEtapes4done = isEtapes4done;
		this.isEtapes5Done = isEtapes5Done;
	}

	public Users(String firstName, String lastName, String email, String username, String password, String numero_telephone, Byte status, File piece_identite, Boolean isPiece_correct, Role role, String token, LocalDateTime tokenCreationDate, byte[] photo_profile_data, String photo_profile_name, String photo_profile_type, String type_profil, String titre_poste, Date datedebut, Date datefin, Boolean isEtapes1Done, Boolean isEtapes2Done, Boolean isEtapes3Done, Boolean isEtapes4done, Boolean isEtapes5Done) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.numero_telephone = numero_telephone;
		this.status = status;
		this.piece_identite = piece_identite;
		this.isPiece_correct = isPiece_correct;
		this.role = role;
		this.token = token;
		this.tokenCreationDate = tokenCreationDate;
		this.photo_profile_data = photo_profile_data;
		this.photo_profile_name = photo_profile_name;
		this.photo_profile_type = photo_profile_type;
		this.type_profil = type_profil;
		this.titre_poste = titre_poste;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.isEtapes1Done = isEtapes1Done;
		this.isEtapes2Done = isEtapes2Done;
		this.isEtapes3Done = isEtapes3Done;
		this.isEtapes4done = isEtapes4done;
		this.isEtapes5Done = isEtapes5Done;
	}

	public Users(String firstName, String lastName, String email, String username, String password, String numero_telephone, Byte status, File piece_identite, Boolean isPiece_correct, String token, LocalDateTime tokenCreationDate, byte[] photo_profile_data, String photo_profile_name, String photo_profile_type, String type_profil, String titre_poste, Date datedebut, Date datefin, Boolean isEtapes1Done, Boolean isEtapes2Done, Boolean isEtapes3Done, Boolean isEtapes4done, Boolean isEtapes5Done) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.numero_telephone = numero_telephone;
		this.status = status;
		this.piece_identite = piece_identite;
		this.isPiece_correct = isPiece_correct;
		this.token = token;
		this.tokenCreationDate = tokenCreationDate;
		this.photo_profile_data = photo_profile_data;
		this.photo_profile_name = photo_profile_name;
		this.photo_profile_type = photo_profile_type;
		this.type_profil = type_profil;
		this.titre_poste = titre_poste;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.isEtapes1Done = isEtapes1Done;
		this.isEtapes2Done = isEtapes2Done;
		this.isEtapes3Done = isEtapes3Done;
		this.isEtapes4done = isEtapes4done;
		this.isEtapes5Done = isEtapes5Done;
	}
}
