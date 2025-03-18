CREATE TABLE IF NOT EXISTS libraries (
                                         id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Auto increment ID for internal use
                                         library_id VARCHAR(36) NOT NULL UNIQUE,          -- library_id is a unique identifier
                                         name VARCHAR(255) NOT NULL,
                                         address VARCHAR(255) NOT NULL,
                                         city VARCHAR(100) NOT NULL,
                                         state VARCHAR(100) NOT NULL,
                                         postal_code VARCHAR(20) NOT NULL
);



-- Création de la table books avec la clé étrangère vers libraries
CREATE TABLE IF NOT EXISTS books (
                                     id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     book_id VARCHAR(36) NOT NULL UNIQUE,  -- Assurez-vous que book_id est un VARCHAR et UNIQUE
                                     title VARCHAR(255) NOT NULL,
                                     author VARCHAR(255) NOT NULL,
                                     genre VARCHAR(100) NOT NULL,
                                     isbn VARCHAR(13) NOT NULL,
                                     copy_available INT NOT NULL,
                                     library_id VARCHAR(36),  -- Colonne pour lier les livres aux bibliothèques
                                     FOREIGN KEY (library_id) REFERENCES libraries(library_id)  -- Clé étrangère pointant vers library_id
);







-- Drop the loans table if it exists


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





DROP TABLE IF EXISTS librarians;

CREATE TABLE librarians (
                            id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            librarian_id VARCHAR(255) NOT NULL UNIQUE,
                            first_name VARCHAR(255) NOT NULL,
                            last_name VARCHAR(255) NOT NULL,
                            email VARCHAR(255) UNIQUE NOT NULL,
                            salary DOUBLE NOT NULL
);

