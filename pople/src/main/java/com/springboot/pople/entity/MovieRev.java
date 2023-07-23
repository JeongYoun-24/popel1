package com.springboot.pople.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MovieRev")
public class MovieRev {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long revno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Column(nullable = false,length = 500)
    private String revTitle;
    @Column(nullable = false,length = 1000)
    private String revContent;
    private LocalDateTime revDate;

    public void change(String rev_title,String rev_content){
        this.revTitle = revTitle;
        this.revContent = revContent;

    }


}
