package cardmanage.cardmanage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "위치를 입력해 주세요.")
    private String location;

    @NotBlank(message = "회사이름을 입력해 주세요.")
    private String companyName;

    @NotBlank(message = "직종을 입력해 주세요.")
    private String position;

    @Email(message = "이메일을 다시 입력해 주세요.")
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;

    @NotBlank(message = "휴대폰 번호를 입력해 주세요.")
    private String phoneNumber;
}
