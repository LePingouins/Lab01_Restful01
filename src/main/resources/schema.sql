CREATE TABLE IF NOT EXISTS books (
                                     id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     book_id VARCHAR(255) NOT NULL UNIQUE, -- Assurez-vous que book_id est un VARCHAR et UNIQUE
                                     title VARCHAR(255) NOT NULL,
                                     author VARCHAR(255) NOT NULL,
                                     genre VARCHAR(100) NOT NULL,
                                     isbn VARCHAR(13) NOT NULL,
                                     copie_available INT NOT NULL
);





DROP TABLE IF EXISTS fines;

CREATE TABLE fines (
                       id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       fine_id VARCHAR(255) NOT NULL,
                       amount DOUBLE NOT NULL,
                       reason VARCHAR(255),
                       status VARCHAR(100)
);


DROP TABLE IF EXISTS librarians;

CREATE TABLE librarians (
                            id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            librarian_id VARCHAR(255) NOT NULL,
                            first_name VARCHAR(255) NOT NULL,
                            last_name VARCHAR(255) NOT NULL,
                            email VARCHAR(255) UNIQUE NOT NULL,
                            salary DOUBLE NOT NULL
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


DROP TABLE IF EXISTS libraries;

-- Créer la table pour les bibliothèques
CREATE TABLE libraries (
                           id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           library_id VARCHAR(255) NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           address VARCHAR(255),
                           max_capacity INTEGER NOT NULL
);


