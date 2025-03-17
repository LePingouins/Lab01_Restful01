CREATE TABLE IF NOT EXISTS books (
                                     id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     book_id VARCHAR(255) NOT NULL UNIQUE, -- Assurez-vous que book_id est un VARCHAR et UNIQUE
                                     title VARCHAR(255) NOT NULL,
                                     author VARCHAR(255) NOT NULL,
                                     genre VARCHAR(100) NOT NULL,
                                     isbn VARCHAR(13) NOT NULL,
                                     copy_available INT NOT NULL
);




DROP TABLE IF EXISTS fines;

CREATE TABLE fines (
                       id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       fine_id VARCHAR(255) NOT NULL,
                       amount DOUBLE NOT NULL,
                       reason VARCHAR(255),
                       status VARCHAR(100)
);




-- Drop the loans table if it exists
DROP TABLE IF EXISTS loans;

-- Create the loans table
CREATE TABLE IF NOT EXISTS loans (
                                     id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- ID unique pour le prêt
                                     loan_id VARCHAR(50) UNIQUE NOT NULL,  -- Identifiant unique pour le prêt
                                     issue_date TIMESTAMP NOT NULL,        -- Date de début du prêt
                                     due_date TIMESTAMP NOT NULL,          -- Date de retour du prêt
                                     status VARCHAR(50) NOT NULL       -- Statut du prêt (ex: actif, retourné)

);

CREATE TABLE IF NOT EXISTS members (
                                       id INT AUTO_INCREMENT PRIMARY KEY,    -- ID du membre, auto-incrémenté
                                       member_id VARCHAR(255) NOT NULL,       -- Identifiant du membre (UUID)
                                       first_name VARCHAR(255) NOT NULL,      -- Prénom du membre
                                       last_name VARCHAR(255) NOT NULL,       -- Nom du membre
                                       email VARCHAR(255) NOT NULL UNIQUE,    -- Email du membre, unique
                                       membership_type VARCHAR(255) NOT NULL, -- Type d'adhésion du membre (ex: standard, premium)

    -- Attributs d'adresse directement dans la table members
                                       street VARCHAR(255),           -- Rue
                                       city VARCHAR(255),                     -- Ville
                                       province VARCHAR(255),                 -- Province
                                       country VARCHAR(255)                -- Pays

);


-- Créer la table libraries avec un index unique sur library_id
DROP TABLE IF EXISTS libraries;

CREATE TABLE libraries (
                           id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           library_id VARCHAR(255) NOT NULL UNIQUE, -- Assurez-vous que library_id est un VARCHAR
                           name VARCHAR(255) NOT NULL,
                           address VARCHAR(255),
                           max_capacity INTEGER NOT NULL
);

-- Insérer des bibliothèques avec des identifiants uniques
INSERT INTO libraries (library_id, name, address, max_capacity) VALUES
                                                                    ('123e4567-e89b-12d3-a456-556642440000', 'Central Library', '123 Main St, Cityville', 500),
                                                                    ('234e5678-f90c-23d4-b567-667753551111', 'Westside Library', '456 West St, Townsville', 300),
                                                                    ('345e6789-g01d-34e5-c678-778864662222', 'East End Library', '789 East St, Villecity', 200);

DROP TABLE IF EXISTS librarians;

CREATE TABLE librarians (
                            id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            librarian_id VARCHAR(255) NOT NULL,
                            first_name VARCHAR(255) NOT NULL,
                            last_name VARCHAR(255) NOT NULL,
                            email VARCHAR(255) UNIQUE NOT NULL,
                            salary DOUBLE NOT NULL,
                            library_id VARCHAR(255) NOT NULL, -- Foreign key to library
                            FOREIGN KEY (library_id) REFERENCES libraries(library_id) -- Référence vers la table libraries
);

-- Insérer des bibliothécaires avec la relation vers les bibliothèques
INSERT INTO librarians (librarian_id, first_name, last_name, email, salary, library_id) VALUES
                                                                                            ('123e4567-e89b-12d3-a456-556642440000', 'Alice', 'Johnson', 'alice.johnson@example.com', 45000.00, '123e4567-e89b-12d3-a456-556642440000'),
                                                                                            ('234e5678-f90c-23d4-b567-667753551111', 'Bob', 'Smith', 'bob.smith@example.com', 50000.00, '234e5678-f90c-23d4-b567-667753551111'),
                                                                                            ('345e6789-g01d-34e5-c678-778864662222', 'Charlie', 'Brown', 'charlie.brown@example.com', 48000.00, '345e6789-g01d-34e5-c678-778864662222');
