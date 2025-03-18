-- Insertion des livres
INSERT INTO books (book_id, title, author, genre, isbn, copy_available)
VALUES
    ('123e4567-e89b-12d3-a456-556642440000', 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', '9780316769488', 10),
    ('234e5678-f90c-23d4-b567-667753551111', '1984', 'George Orwell', 'Dystopian', '9780451524935', 5),
    ('345e6789-g01d-34e5-c678-778864662222', 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', '9780061120084', 8),
    ('456e7890-h12e-45f6-d789-889975773333', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Classics', '9780743273565', 4);


INSERT INTO libraries (library_id, name, address, city, state, postal_code)
VALUES
    ('789e0123-i34f-56g7-h890-998877665544', 'Central Library', '123 Main St', 'Springfield', 'IL', '62701'),
    ('890e1234-j45h-67i8-j901-109988776655', 'Westside Branch', '456 Elm St', 'Springfield', 'IL', '62702'),
    ('901e2345-k56i-78j9-k012-210099887766', 'Eastside Branch', '789 Oak St', 'Springfield', 'IL', '62703'),
    ('012e3456-l67j-89k0-l123-321001112233', 'Northgate Library', '101 Maple St', 'Springfield', 'IL', '62704');


-- Insérer des bibliothécaires avec la relation vers les bibliothèques
INSERT INTO librarians (librarian_id, first_name, last_name, email, salary) VALUES
                                                                                            ('123e4567-e89b-12d3-a456-556642440000', 'Alice', 'Johnson', 'alice.johnson@example.com', 45000.00),
                                                                                            ('234e5678-f90c-23d4-b567-667753551111', 'Bob', 'Smith', 'bob.smith@example.com', 50000.00),
                                                                                            ('345e6789-g01d-34e5-c678-778864662222', 'Charlie', 'Brown', 'charlie.brown@example.com', 48000.00);


-- Insertion de membres avec les informations d'adresse directement dans la table members
INSERT INTO members (member_id, first_name, last_name, email, membership_type, street, city, province, country)
VALUES
    ('e1b0748e-56d3-44e8-a4b2-543768c4669f', 'John', 'Doe', 'john.doe@example.com', 'standard', '123 Maple Street', 'Toronto', 'Ontario', 'Canada'),
    ('2b9c7b3f-df5a-4e0c-b827-82cf7b4d389d', 'Jane', 'Doe', 'jane.doe@example.com', 'premium', '456 Oak Avenue', 'Vancouver', 'British Columbia', 'Canada');


