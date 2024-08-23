package com.example.demotestxuong.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "staff")
@Entity
public class Staff {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "status")
    private Byte status;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "last_modified_date")
    private Long lastModifiedDate;

    @Column(name = "account_fe")
    @NotBlank(message = "Email FE không được để trống")
    @Email(message = "Email FE không hợp lệ")
    @Pattern(regexp = "^[\\w.-]+@fe\\.edu\\.vn$", message = "Email FE phải kết thúc với đuôi @fe.edu.vn và không chứa khoảng trắng hoặc ký tự tiếng Việt")
    @Length(max = 100, message = "Độ dài email FE phải nhỏ hơn 100 ký tự")
    private String accountFe;

    @Column(name = "account_fpt")
    @NotBlank(message = "Email FPT không được để trống")
    @Email(message = "Email FPT không hợp lệ")
    @Pattern(regexp = "^[\\w.-]+@fpt\\.edu\\.vn$", message = "Email FPT phải kết thúc với đuôi @fpt.edu.vn và không chứa khoảng trắng hoặc ký tự tiếng Việt")
    @Length(max = 100, message = "Độ dài email FPT phải nhỏ hơn 100 ký tự")
    private String accountFpt;

    @Column(name = "name")
    @NotBlank(message = "Họ tên không được để trống")
    @Length(max = 100, message = "Độ dài họ tên phải nhỏ hơn 100 ký tự")
    @Pattern(regexp = "^[a-zA-ZÀ-ỹ\\s]+$", message = "Họ tên chỉ được chứa các ký tự chữ và dấu cách")
    private String name;

    @Column(name = "staff_code")
    @NotBlank(message = "Mã nhân viên không được để trống")
    @Length(max = 15, message = "Độ dài mã nhân viên phải nhỏ hơn 15 ký tự")
    private String staffCode;
}
