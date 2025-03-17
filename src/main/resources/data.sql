-- Insertion des livres
INSERT INTO books (book_id, title, author, genre, isbn, copie_available)
VALUES
    ('123e4567-e89b-12d3-a456-556642440000', 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', '9780316769488', 10),
    ('234e5678-f90c-23d4-b567-667753551111', '1984', 'George Orwell', 'Dystopian', '9780451524935', 5),
    ('345e6789-g01d-34e5-c678-778864662222', 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', '9780061120084', 8),
    ('456e7890-h12e-45f6-d789-889975773333', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Classics', '9780743273565', 4);

INSERT INTO fines (fine_id, amount, reason, status) VALUES
          ('123e4567-e89b-12d3-a456-556642440000', 100.50, 'Late return', 'Pending'),
          ('234e5678-f90c-23d4-b567-667753551111', 250.00, 'Parking violation', 'Paid'),
          ('345e6789-g01d-34e5-c678-778864662222', 75.25, 'Overdue payment', 'Pending');

INSERT INTO librarians (librarian_id, first_name, last_name, email, salary) VALUES
                              ('123e4567-e89b-12d3-a456-556642440000', 'Alice', 'Johnson', 'alice.johnson@example.com', 45000.00),
                              ('234e5678-f90c-23d4-b567-667753551111', 'Bob', 'Smith', 'bob.smith@example.com', 50000.00),
                              ('345e6789-g01d-34e5-c678-778864662222', 'Charlie', 'Brown', 'charlie.brown@example.com', 48000.00);


-- Insert data into loans table
INSERT INTO loans (loan_id, issue_date, due_date, status)
VALUES
    ('LN1001', '2025-03-01 10:00:00', '2026-03-01 10:00:00', 'active'),
    ('LN1002', '2025-04-15 09:30:00', '2026-04-15 09:30:00', 'active'),
    ('LN1003', '2025-05-20 14:00:00', '2026-05-20 14:00:00', 'returned'),
    ('LN1004', '2025-06-01 12:00:00', '2026-06-01 12:00:00', 'active'),
    ('LN1005', '2025-07-10 16:00:00', '2026-07-10 16:00:00', 'active'),
    ('LN1006', '2025-08-25 08:30:00', '2026-08-25 08:30:00', 'returned');


-- Insertion de membres avec les informations d'adresse directement dans la table members
INSERT INTO members (member_id, first_name, last_name, email, membership_type, street, city, province, country)
VALUES
    ('e1b0748e-56d3-44e8-a4b2-543768c4669f', 'John', 'Doe', 'john.doe@example.com', 'standard', '123 Maple Street', 'Toronto', 'Ontario', 'Canada'),
    ('2b9c7b3f-df5a-4e0c-b827-82cf7b4d389d', 'Jane', 'Doe', 'jane.doe@example.com', 'premium', '456 Oak Avenue', 'Vancouver', 'British Columbia', 'Canada');



-- Insérer des données dans la table libraries
INSERT INTO libraries (library_id, name, address, max_capacity) VALUES
                                                                    ('123e4567-e89b-12d3-a456-556642440000', 'Central Library', '123 Main St, Cityville', 500),
                                                                    ('234e5678-f90c-23d4-b567-667753551111', 'Westside Library', '456 West St, Townsville', 300),
                                                                    ('345e6789-g01d-34e5-c678-778864662222', 'East End Library', '789 East St, Villecity', 200);