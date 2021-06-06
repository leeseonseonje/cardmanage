package cardmanage.cardmanage.domain;


import cardmanage.cardmanage.dto.CardDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Card {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private String companyName;

    private String position;

    private String email;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Card(String name, String location, String companyName, String position, String email,
                String phoneNumber, Member member) {
        this.name = name;
        this.location = location;
        this.companyName = companyName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.member = member;
    }

    public void updateCard(CardDto cardDto) {
        this.name = cardDto.getName();
        this.location = cardDto.getLocation();
        this.companyName = cardDto.getCompanyName();
        this.position = cardDto.getPosition();
        this.email = cardDto.getEmail();
        this.phoneNumber = cardDto.getPhoneNumber();
    }
}
