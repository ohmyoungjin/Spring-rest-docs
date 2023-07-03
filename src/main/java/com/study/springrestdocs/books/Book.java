package com.study.springrestdocs.books;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class Book {

    private Long id;

    private String title;

    private String author;

    private Date publishedAt;
}
