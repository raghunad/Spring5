package com.raghu.spring5webapp.bootstrap;

import com.raghu.spring5webapp.model.Author;
import com.raghu.spring5webapp.model.Book;
import com.raghu.spring5webapp.model.Publisher;
import com.raghu.spring5webapp.repositories.AuthorRepository;
import com.raghu.spring5webapp.repositories.BookRepository;
import com.raghu.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        authorRepository.deleteAll();
        bookRepository.deleteAll();
        publisherRepository.deleteAll();

        Publisher pub1 = new Publisher();
        pub1.setName("Wrox");
        pub1.setAddress("New York");
        publisherRepository.save(pub1);

        //Raghu
        Author raghu = new Author("Raghu","Nadimpalli");
        Book bk1 = new Book("Domain Driven Design","123",pub1);
        raghu.getBooks().add(bk1);
        bk1.getAuthors().add(raghu);

        authorRepository.save(raghu);
        bookRepository.save(bk1);

        //Varma
        Author varma = new Author("Varma","Nadimpalli");
        Book bk2 = new Book("J2EE Development","456",pub1);
        varma.getBooks().add(bk2);

        authorRepository.save(varma);
        bookRepository.save(bk2);
    }
}