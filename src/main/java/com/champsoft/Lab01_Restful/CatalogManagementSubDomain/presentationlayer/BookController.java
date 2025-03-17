package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.businesslogiclayer.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/books")
public class BookController {

private final BookServiceImpl bookServiceImpl;

public BookController(BookServiceImpl bookServiceImpl){

    this.bookServiceImpl = bookServiceImpl;
}

@GetMapping
public ResponseEntity<List<BookResponseModel>> getBooks(){

    return ResponseEntity.ok().body(bookServiceImpl.getAllBooks());

}

@GetMapping("/{book_id}")
public ResponseEntity<BookResponseModel> getBookbyId(@PathVariable String book_id){

    return ResponseEntity.ok().body(this.bookServiceImpl.getBookbyId(book_id));
}

    @PostMapping()
    public ResponseEntity<BookResponseModel> addBook(@RequestBody BookRequestModel newBookData){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.bookServiceImpl.addBook(newBookData));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseModel> updateBook(@PathVariable String bookId, @RequestBody BookRequestModel updatedBookData) {

        return ResponseEntity.ok().body(bookServiceImpl.updateBook(bookId, updatedBookData));
    }


    @DeleteMapping("/{book_id}")
    public ResponseEntity<String> deleteBookbyId(@PathVariable String book_id){
        String message = this.bookServiceImpl.deleteBookbyId(book_id);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
